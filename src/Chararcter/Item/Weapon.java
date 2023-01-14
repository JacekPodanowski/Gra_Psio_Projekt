package Chararcter.Item;

public class Weapon extends Item{

    protected boolean isUsed=false;
    protected char type; // S = siła, I = inteligencja, A - zręczność
    protected int requirement;
    protected int basicDMG;

    protected int lvl;


    public Weapon(){
        super("Dłoń", 0, "Twoja");
        isUsed = true;
        type = 'S';
        requirement = 0;
        basicDMG = 5;
        lvl = 1;
    }

    public Weapon(String name, int value, char type, int requirement,int basicDMG,int lvl) {
        super(name, value);
        this.type = type;
        this.lvl = lvl;
        this.value = (int) (value* qualityTab.get(quality));
        if(qualityTab.get(quality)>1){
            this.requirement= (int) (requirement + qualityTab.get(quality)*requirement/5);
        }else if(qualityTab.get(quality)==1) {
            this.requirement=requirement;
        }
        else {
            this.requirement= (int) (requirement* qualityTab.get(quality));
        }

        this.basicDMG = (int) (basicDMG* qualityTab.get(quality))+1;
    }

    public Weapon(String name, int value, String quality, char type, int requirement, int basicDMG,int lvl) {
        super(name, value,quality);
        this.type = type;
        this.lvl = lvl;
        this.value = (int) (value* qualityTab.get(quality));
        if(qualityTab.get(quality)>1){
            this.requirement= (int) (requirement + qualityTab.get(quality)*requirement/5);
        }else if(qualityTab.get(quality)==1) {
            this.requirement=requirement;
        }
        else {
            this.requirement= (int) (requirement* qualityTab.get(quality));
        }

        this.basicDMG = (int) (basicDMG* qualityTab.get(quality))+1;
    }

    public String toString() {
        return super.toString()+"\tDmg-"+basicDMG+ " typ-"+type +" wymaganie-"+ requirement+" lvl-"+lvl;}

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

    public int getLvl() { return lvl;}
}
