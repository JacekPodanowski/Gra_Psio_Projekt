package Chararcter.Item;

public abstract class Item {

    protected String name;
    protected String value;
    protected String quality;


    public void takeToInventory(){}

    public abstract void useTheItem();
}
