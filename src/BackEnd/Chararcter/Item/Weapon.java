package BackEnd.Chararcter.Item;

public class Weapon extends Item{

    protected boolean isUsed=false;
    protected char type; // S = siła, I = inteligencja, A - zręczność
    protected int requirement;
    protected int basicDMG;


    public Weapon(){
        super("Dłoń", 0, "Twoja");
        isUsed = true;
        type = 'S';
        requirement = 0;
        basicDMG = 5;
    }

//    public Weapon(String name, int value, String quality, char type, int requirement, int basicDMG) {
//        super(name, value, quality);
//        this.type = type;
//        this.requirement = requirement;
//        this.basicDMG = basicDMG;
//    }

    public Weapon(String name, int value, String quality, char type, int requirement, int basicDMG) {
        super(name, value,quality);
        this.type = type;
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

    public void showStats() {
        System.out.println(super.toString()+" Wartość : "+value+" dmg : "+basicDMG +" wymaganie : "+ requirement);
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
