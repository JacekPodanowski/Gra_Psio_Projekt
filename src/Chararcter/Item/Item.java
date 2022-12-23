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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public abstract void useItem();
}
