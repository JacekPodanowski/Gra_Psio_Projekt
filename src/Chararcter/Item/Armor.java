package Chararcter.Item;

public class Armor extends Item{

    protected boolean isUsed=false;
    protected int StrengthProtection;
    protected int MagicProtection;
    protected int AgilityProtection;

    public Armor(String name, int value, String quality, int strengthProtection, int magicProtection, int agilityProtection) {
        super(name, value, quality);
        StrengthProtection = strengthProtection;
        MagicProtection = magicProtection;
        AgilityProtection = agilityProtection;
    }

//    public Armor(String name, int value, int strengthProtection, int magicProtection, int agilityProtection) {
//        super(name, value);
//        this.value = (int) (value* qualityTab.get(quality));
//        if(qualityTab.get(quality)>1){
//            this.StrengthProtection = (int) (strengthProtection+ qualityTab.get(quality)*2);
//            this.MagicProtection = (int) (magicProtection+ qualityTab.get(quality)*2);
//            this.AgilityProtection = (int) (agilityProtection+ qualityTab.get(quality)*2);
//        }else if(qualityTab.get(quality)==1) {
//              this.StrengthProtection = strengthProtection;
//              this.MagicProtection = magicProtection;
//              this.AgilityProtection = agilityProtection;
//        }
//        else {
//                this.StrengthProtection = (int) (strengthProtection+ qualityTab.get(quality));
//                this.MagicProtection = (int) (magicProtection+ qualityTab.get(quality));
//                this.AgilityProtection = (int) (agilityProtection+ qualityTab.get(quality));
//        }
//
//    }

    public String toString() {
        return name+" "+"Czy użyta"+isUsed+" "+value+" "+"Strength resist"+StrengthProtection+" "+
                "Magic resist"+MagicProtection+" "+"Agility resist"+AgilityProtection;
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
