import org.junit.Test;
import static org.junit.Assert.*;

public class TruckTest {
    
    @Test
    public void testTruckCreation() {
        Truck truck = new Truck(100, 200, 300, 1000);
        assertEquals(100, truck.getWidth());
        assertEquals(200, truck.getHeight());
        assertEquals(300, truck.getLength());
        assertEquals(1000, truck.getMaxWeight());
    }
    
    @Test
    public void testTruckVolume() {
        Truck truck = new Truck(100, 200, 300, 1000);
        assertEquals(6000000, truck.getVolume());
    }
    
    @Test
    public void testCanFitItem() {
        Truck truck = new Truck(100, 200, 300, 1000);
        Item item = new Item(50, 100, 150, 500);
        assertTrue(truck.canFit(item));
        
        Item largeItem = new Item(150, 250, 350, 1500);
        assertFalse(truck.canFit(largeItem));
    }
} 