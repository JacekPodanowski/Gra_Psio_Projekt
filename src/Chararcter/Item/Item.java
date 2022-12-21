package Chararcter.Item;

public abstract class Item {

    protected String name;
    protected int value;
    protected String quality;

    public Item(String name, int value, String quality) {
        this.name = name;
        this.value = value;
        this.quality = quality;
    }

    public abstract void useItem();
}
