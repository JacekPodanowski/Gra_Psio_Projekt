package Chararcter.Item;

public class Weapon extends Item{

    protected boolean isUsed=false;
    protected char type; // S = siła, I = inteligencja, A - zręczność
    protected int requirement;
    protected int basicDMG;
    //protected String use;


    //protected String luck; to powinno iść do walki

    public Weapon(){
        super("Ręce", 0, "pospolity");
        isUsed = true;
        type = 'S';
        requirement = 0;
        basicDMG = 5;
    }

    public Weapon(String name, int value, String quality, char type, int requirement, int basicDMG) {
        super(name, value, quality);
        this.type = type;
        this.requirement = requirement;
        this.basicDMG = basicDMG;

    }

    @Override
    public String toString() { //jak sie znajdzie bron to sie odpali'n to
        return "Weapon{" +
                "type='" + type + '\'' +
                ", requirement=" + requirement +
                ", basicDMG=" + basicDMG +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", quality='" + quality + '\'' +
                '}';
    }

    public boolean isUsed() {
        return isUsed;
    }

    public char getType() {
        return type;
    }

    public int getRequirement() {
        return requirement;
    }

    public int getBasicDMG() {
        return basicDMG;
    }
}
