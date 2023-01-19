package BackEnd.Chararcter.Item;

public class Armor extends Item{

    protected boolean isUsed=false;
    protected int StrengthProtection;
    protected int MagicProtection;
    protected int AgilityProtection;//0-100

    protected int lvl;

    public Armor(){
        super("Nic", 0, "");
        StrengthProtection = 0;
        MagicProtection = 0;
        AgilityProtection = 0;
        lvl = 1;
    }

    public Armor(String name, int value, int strengthProtection, int magicProtection, int agilityProtection,int lvl) {
        super(name, value);
        this.lvl = lvl;
        this.value = (int) (value* qualityTab.get(quality));
        this.StrengthProtection = (int) (strengthProtection* qualityTab.get(quality));
        this.MagicProtection = (int) (magicProtection* qualityTab.get(quality));
        this.AgilityProtection = (int) (agilityProtection *qualityTab.get(quality));
    }

    public Armor(String name, int value,String quality, int strengthProtection, int magicProtection, int agilityProtection,int lvl) {
        super(name, value,quality);
        this.lvl = lvl;
        this.value = (int) (value* qualityTab.get(quality));
        this.StrengthProtection = (int) (strengthProtection* qualityTab.get(quality));
        this.MagicProtection = (int) (magicProtection* qualityTab.get(quality));
        this.AgilityProtection = (int) (agilityProtection *qualityTab.get(quality));
    }

    public String toString() {
        return super.toString()+" Ochrony: "+"Siła: "+StrengthProtection+ " Magia: "+MagicProtection+" Zręcznosc: "+AgilityProtection;
    }

    public String stats() {
        return " Ochrony: "+"Siła: "+StrengthProtection+ " Magia: "+MagicProtection+" Zręcznosc: "+AgilityProtection;
    }

    public int getStrengthProtection() {
        return StrengthProtection;
    }

    public int getMagicProtection() {
        return MagicProtection;
    }

    public int getAgilityProtection() {
        return AgilityProtection;
    }

    public int getLvl() {return lvl;}
}
