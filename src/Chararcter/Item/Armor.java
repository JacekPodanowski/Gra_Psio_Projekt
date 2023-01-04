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

    @Override
    public void useItem() {
        //isUsed=true; moze to do playera bo jak wyrzucic akutalny przedmiot stąd

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
