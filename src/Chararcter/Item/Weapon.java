package Chararcter.Item;

public class Weapon extends Item{

    protected boolean isUsed=false;
    protected String type;
    protected int requirement;
    protected int basicDMG;
    protected String use;
    protected int accuracy;
    //protected String luck; to powinno iść do walki

    public Weapon(String name, int value, String quality, String type, int requirement, int basicDMG, int accuracy) {
        super(name, value, quality);
        this.type = type;
        this.requirement = requirement;
        this.basicDMG = basicDMG;
        this.accuracy = accuracy;
    }

    @Override
    public void useItem() {

    }

    public double calculatedmg(){
        //podstawowy atak postaci, liczy damage
        double damage = 0;
        switch(this.type) {
            case "strength":

        }
        return damage;
    }
}
