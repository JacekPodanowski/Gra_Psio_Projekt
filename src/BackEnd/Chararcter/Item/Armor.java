package BackEnd.Chararcter.Item;

public class Armor extends Item{

    protected boolean isUsed=false;
    protected int StrengthProtection;
    protected int MagicProtection;
    protected int AgilityProtection;//0-100

    public Armor(){
        super("Nic", 0, "");
        StrengthProtection = 0;
        MagicProtection = 0;
        AgilityProtection = 0;
    }

//    public Armor(String name, int value, String quality, int strengthProtection, int magicProtection, int agilityProtection) {
//        super(name, value, quality);
//        StrengthProtection = strengthProtection;
//        MagicProtection = magicProtection;
//        AgilityProtection = agilityProtection;
//    }

    public Armor(String name, int value,String quality, int strengthProtection, int magicProtection, int agilityProtection) {
        super(name, value,quality);
        this.value = (int) (value* qualityTab.get(quality));
        this.StrengthProtection = (int) (strengthProtection* qualityTab.get(quality));
        this.MagicProtection = (int) (magicProtection* qualityTab.get(quality));
        this.AgilityProtection = (int) (agilityProtection *qualityTab.get(quality));
    }

    public void showStats() {
        System.out.println(super.toString()+" Wartość : "+value+" Strength resist "+StrengthProtection+ " Magic resist: "+MagicProtection+" Agility resist : "+AgilityProtection);
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
}