import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.stat.inference.TTest;

public class Main {
    public static void main(String[] args) {
        int numberOfRuns = 50;
        long totalRuntime = 0;
        List<List<Item>> bestSolutions = new ArrayList<>();
        List<Item> overallBestSolution = null;
        int overallBestSolutionIndex = -1;  // Başlangıç değeri
        double bestCorrelation = -1.0;  // Başlangıç değeri
        double bestPValue = Double.MAX_VALUE;  // Başlangıç değeri
        int bestCorrelationIndex1 = -1;  // Başlangıç değeri
        int bestCorrelationIndex2 = -1;  // Başlangıç değeri

        int maxVolume = 65 * 1000000; // 65m3 in cm3

        for (int i = 0; i < numberOfRuns; i++) {
            List<Item> items = generateRandomItems();
            GeneticAlgorithm ga = new GeneticAlgorithm(items);
            long startTime = System.nanoTime();
            List<Item> bestSolution = ga.run();
            long endTime = System.nanoTime();
            totalRuntime += (endTime - startTime);
            bestSolutions.add(bestSolution);

            // Öğeleri boşaltma duraklarına göre sıralama
            bestSolution.sort(Comparator.comparing(Item::getUnloadingStop).reversed());

            Truck truck = new Truck();
            for (Item item : bestSolution) {
                boolean added = truck.addItem(item);
                if (!added) {
                    System.out.println("Item could not be added due to capacity limits: " + item);
                }
            }
        }

        double averageRuntime = totalRuntime / (double) numberOfRuns;
        System.out.println("Average runtime over " + numberOfRuns + " runs: " + averageRuntime + " nanoseconds");

        SpearmansCorrelation spearmansCorrelation = new SpearmansCorrelation();
        TTest tTest = new TTest();
        for (int i = 0; i < bestSolutions.size() - 1; i++) {
            for (int j = i + 1; j < bestSolutions.size(); j++) {
                double[] ranks1 = getRanks(bestSolutions.get(i));
                double[] ranks2 = getRanks(bestSolutions.get(j));
                double correlation = spearmansCorrelation.correlation(ranks1, ranks2);
                double pValue = tTest.tTest(ranks1, ranks2);

                if (correlation > bestCorrelation) {  // Daha iyi korelasyon bulunduğunda güncelle
                    bestCorrelation = correlation;
                    bestCorrelationIndex1 = i + 1;
                    bestCorrelationIndex2 = j + 1;
                    overallBestSolution = bestSolutions.get(i);
                    overallBestSolutionIndex = i + 1;
                }

                if (pValue < bestPValue) {  // Daha düşük p-value bulunduğunda güncelle
                    bestPValue = pValue;
                }

                System.out.println("Correlation between solution " + (i + 1) + " and solution " + (j + 1) + ": " + correlation);
                System.out.println("t-test p-value between solution " + (i + 1) + " and solution " + (j + 1) + ": " + pValue);
            }
        }

        for (int i = 0; i < bestSolutions.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            for (Item item : bestSolutions.get(i)) {
                System.out.println(item);
            }
        }

        System.out.println("\n=====================");
        System.out.println("Overall Best Solution (highest correlation): Solution " + overallBestSolutionIndex);
        System.out.println("=====================");
        if (overallBestSolution != null) {
            overallBestSolution.forEach(System.out::println);
        } else {
            System.out.println("No best solution found.");
        }

        if (overallBestSolution != null) {
            int overallBestVolume = new GeneticAlgorithm(new ArrayList<>()).calculateFitness(overallBestSolution);
            double utilizationPercentage = (overallBestVolume / (double) maxVolume) * 100;
            System.out.println("Overall Best Solution Utilization: " + utilizationPercentage + "%");
        } else {
            System.out.println("No best solution found.");
        }

        System.out.println("\n=====================");
        System.out.println("Best Spearman Rank Correlation: " + bestCorrelation + " (between Solution " + bestCorrelationIndex1 + " and Solution " + bestCorrelationIndex2 + ")");
        System.out.println("Best t-test p-value: " + bestPValue);
        System.out.println("=====================");
    }

    private static List<Item> generateRandomItems() {
        List<Item> items = new ArrayList<>();
        Random random = new Random();
        char[] stops = {'A', 'B', 'C', 'D', 'E'};

        for (int i = 0; i < 100; i++) {
            int width = random.nextInt(60) + 1;
            int height = random.nextInt(60) + 1;
            int depth = random.nextInt(90) + 1;
            char stop = stops[random.nextInt(stops.length)];

            items.add(new Item(width, height, depth, stop));
        }

        return items;
    }

    private static double[] getRanks(List<Item> solution) {
        double[] ranks = new double[solution.size()];
        for (int i = 0; i < solution.size(); i++) {
            ranks[i] = solution.get(i).getWidth() + solution.get(i).getHeight() + solution.get(i).getDepth();
        }
        return ranks;
    }
}
