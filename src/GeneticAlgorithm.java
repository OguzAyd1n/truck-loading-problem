import java.util.*;

public class GeneticAlgorithm {
    private static final int POPULATION_SIZE = 100;
    private static final double CROSSOVER_RATE = 0.90;
    private static final double MUTATION_RATE = 0.10;
    private static final int NUM_GENERATIONS = 100;

    private final List<Item> items;
    private List<List<Item>> population;

    public GeneticAlgorithm(List<Item> items) {
        this.items = items;
        this.population = new ArrayList<>();
        initializePopulation();
    }

    private void initializePopulation() {
        Random random = new Random();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<Item> individual = new ArrayList<>(items);
            Collections.shuffle(individual, random);
            population.add(individual);
        }
    }

    public List<Item> run() {
        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
            population = evolvePopulation();
        }
        return getBestSolution();
    }

    private List<List<Item>> evolvePopulation() {
        List<List<Item>> newPopulation = new ArrayList<>();
        Random random = new Random();

        while (newPopulation.size() < POPULATION_SIZE) {
            List<Item> parent1 = selectParent();
            List<Item> parent2 = selectParent();

            List<Item> offspring;
            if (random.nextDouble() < CROSSOVER_RATE) {
                offspring = crossover(parent1, parent2);
            } else {
                offspring = new ArrayList<>(parent1);
            }

            if (random.nextDouble() < MUTATION_RATE) {
                mutate(offspring);
            }

            newPopulation.add(offspring);
        }

        return newPopulation;
    }

    private List<Item> selectParent() {
        Random random = new Random();
        return population.get(random.nextInt(POPULATION_SIZE));
    }

    private List<Item> crossover(List<Item> parent1, List<Item> parent2) {
        Random random = new Random();
        int crossoverPoint = random.nextInt(items.size());

        List<Item> offspring = new ArrayList<>(parent1.subList(0, crossoverPoint));
        offspring.addAll(parent2.subList(crossoverPoint, items.size()));

        return offspring;
    }

    private void mutate(List<Item> individual) {
        Random random = new Random();
        int index1 = random.nextInt(items.size());
        int index2 = random.nextInt(items.size());

        Collections.swap(individual, index1, index2);
    }

    public int calculateFitness(List<Item> solution) {
        return solution.stream().mapToInt(item -> item.getWidth() * item.getHeight() * item.getDepth()).sum();
    }

    private List<Item> getBestSolution() {
        List<Item> bestSolution = population.get(0);
        int bestFitness = calculateFitness(bestSolution);

        for (List<Item> individual : population) {
            int fitness = calculateFitness(individual);
            if (fitness > bestFitness) {
                bestSolution = individual;
                bestFitness = fitness;
            }
        }
        return bestSolution;
    }
}
