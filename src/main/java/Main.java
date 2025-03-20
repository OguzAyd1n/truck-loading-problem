import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test verileri
        List<Item> items = new ArrayList<>();
        items.add(new Item(50, 50, 50, 100));
        items.add(new Item(30, 30, 30, 50));
        items.add(new Item(20, 20, 20, 30));
        items.add(new Item(40, 40, 40, 80));
        
        Truck truck = new Truck(200, 200, 200, 1000);
        
        // Genetik algoritma ile çözüm
        GeneticAlgorithm ga = new GeneticAlgorithm(items, truck, 100);
        ga.evolve(100);
        
        // En iyi çözümü al
        List<Item> bestSolution = ga.getBestSolution();
        
        // Görselleştirme
        Visualization visualization = new Visualization(bestSolution, truck);
        visualization.main(args);
        
        // Sonuçları yazdır
        System.out.println("En iyi çözüm bulundu!");
        System.out.println("Toplam yüklenen eşya sayısı: " + bestSolution.size());
        System.out.println("Kullanılan hacim: " + calculateUsedVolume(bestSolution));
        System.out.println("Kullanılan ağırlık: " + calculateUsedWeight(bestSolution));
    }
    
    private static double calculateUsedVolume(List<Item> items) {
        return items.stream()
                .mapToDouble(Item::getVolume)
                .sum();
    }
    
    private static double calculateUsedWeight(List<Item> items) {
        return items.stream()
                .mapToDouble(Item::getWeight)
                .sum();
    }
} 