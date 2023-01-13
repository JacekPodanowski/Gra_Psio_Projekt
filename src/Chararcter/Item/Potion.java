package Chararcter.Item;

public class Potion extends Item {
    private int healing;

    public Potion(String name, int value, String quality, int healing) {
        super(name, value, quality);
        this.healing = healing;
    }

    public Potion(String name, int value, int healing) {
        super(name, value);
        this.value= (int) (value*qualityTab.get(quality));
        this.healing = (int) (healing*qualityTab.get(quality));
    }

    public int getHealing() {
        return healing;
    }
}
