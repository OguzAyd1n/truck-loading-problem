public class Item {
    private final int width;
    private final int height;
    private final int depth;
    private final char unloadingStop;

    public Item(int width, int height, int depth, char unloadingStop) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.unloadingStop = unloadingStop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public char getUnloadingStop() {
        return unloadingStop;
    }

    @Override
    public String toString() {
        return "Item{" +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", unloadingStop=" + unloadingStop +
                '}';
    }
}
