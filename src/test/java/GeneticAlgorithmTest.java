import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithmTest {
    
    @Test
    public void testPopulationInitialization() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 20, 30, 50));
        items.add(new Item(20, 30, 40, 70));
        items.add(new Item(30, 40, 50, 90));
        
        Truck truck = new Truck(100, 200, 300, 1000);
        GeneticAlgorithm ga = new GeneticAlgorithm(items, truck, 100);
        
        assertEquals(100, ga.getPopulation().size());
    }
    
    @Test
    public void testFitnessCalculation() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 20, 30, 50));
        items.add(new Item(20, 30, 40, 70));
        
        Truck truck = new Truck(100, 200, 300, 1000);
        GeneticAlgorithm ga = new GeneticAlgorithm(items, truck, 100);
        
        double fitness = ga.calculateFitness(ga.getPopulation().get(0));
        assertTrue(fitness >= 0);
    }
    
    @Test
    public void testEvolution() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 20, 30, 50));
        items.add(new Item(20, 30, 40, 70));
        items.add(new Item(30, 40, 50, 90));
        
        Truck truck = new Truck(100, 200, 300, 1000);
        GeneticAlgorithm ga = new GeneticAlgorithm(items, truck, 100);
        
        double initialBestFitness = ga.getBestFitness();
        ga.evolve(10);
        assertTrue(ga.getBestFitness() >= initialBestFitness);
    }
} 