import java.util.ArrayList;
import java.util.List;

public class Truck {
    private static final int CAPACITY = 65 * 1000000; // 65m3 in cm3
    private final List<Item> items;

    public Truck() {
        items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        int totalVolume = items.stream().mapToInt(i -> i.getWidth() * i.getHeight() * i.getDepth()).sum();
        int itemVolume = item.getWidth() * item.getHeight() * item.getDepth();
        if (totalVolume + itemVolume <= CAPACITY) {
            items.add(item);
            return true;
        }
        return false;
    }

    // Diğer gerekli metotlar
}
