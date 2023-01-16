package BackEnd.Chararcter.Item;

public class Potion extends Item {
    private int healing;

    public Potion(String name, int value, String quality, int healing) {
        super(name, value, quality);
        this.healing = healing;
    }

    public int getHealing() {
        return healing;
    }
}
