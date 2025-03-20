public class Item {
    private int width;
    private int height;
    private int length;
    private double weight;
    private Position position;
    
    public Item(int width, int height, int length, double weight) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.position = new Position(0, 0, 0);
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getLength() {
        return length;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public double getVolume() {
        return width * height * length;
    }
    
    public void rotate() {
        int temp = width;
        width = height;
        height = temp;
    }
}

class Position {
    private double x;
    private double y;
    private double z;
    
    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getZ() {
        return z;
    }
} 