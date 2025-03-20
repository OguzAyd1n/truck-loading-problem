import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {
    
    @Test
    public void testItemCreation() {
        Item item = new Item(10, 20, 30, 50);
        assertEquals(10, item.getWidth());
        assertEquals(20, item.getHeight());
        assertEquals(30, item.getLength());
        assertEquals(50, item.getWeight());
    }
    
    @Test
    public void testItemVolume() {
        Item item = new Item(10, 20, 30, 50);
        assertEquals(6000, item.getVolume());
    }
    
    @Test
    public void testItemRotation() {
        Item item = new Item(10, 20, 30, 50);
        item.rotate();
        assertEquals(20, item.getWidth());
        assertEquals(10, item.getHeight());
        assertEquals(30, item.getLength());
    }
} 