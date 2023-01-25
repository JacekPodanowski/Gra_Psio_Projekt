package BackEnd.Chararcter;

import java.io.Serializable;
import java.util.Random;

public class Skill implements Serializable {


    private String name;
    private double damageMultiplier;
    private int accuracy;
    private int bonus;

    public Skill(String name, double damageMultiplier, int accuracy, int bonus) {
        this.name = name;
        this.damageMultiplier = damageMultiplier;
        this.accuracy = accuracy;
        this.bonus = bonus;
    }

    public int calcDmg(Character player, Character character){

        double dmg;
        int resist = 0;
        double reqDmgMultiplier = 1;

        switch (player.getWeapon().getType()) {
            case 'S':
                resist = character.getArmor().getStrengthProtection();
                reqDmgMultiplier = this.reqDmgMultiplier(player, player.getStrength());
                break;

            case 'I':
                resist = character.getArmor().getMagicProtection();
                if (this.bonus == 0) {
                    reqDmgMultiplier = this.reqDmgMultiplier(player, player.getIntelligence());
                } else {
                    double health = player.getHealth();
                    player.setHealth(player.getHealth() + this.bonus * 10);
                    System.out.print(", uleczono o " + (player.getHealth() - health));
                }
                break;

            case 'A':
                resist = character.getArmor().getAgilityProtection();
                reqDmgMultiplier = this.reqDmgMultiplier(player, player.getIntelligence());
                break;
        }
        //==================================REZISTY==============================================

        int Basicdmg = player.getWeapon().getBasicDMG();
        dmg = Basicdmg * reqDmgMultiplier * damageMultiplier;
        dmg = dmg * (1 -resist/100.0);

        if(dmg < 0)
            dmg = 0;

        return (int) dmg;
    }

    public int use(Character ataker, Character target){
        int dmg = calcDmg(ataker, target);
        Random r = new Random();
        if(r.nextInt(100) > accuracy) {
            target.setAttackAvoided(true);
            dmg = 0;
        }
        return dmg;
    }
    public double reqDmgMultiplier(Character player, int attribute){
        double reqDmgMultiplier = 1;
        int req = player.getWeapon().getRequirement();
        if (attribute < req) {
            reqDmgMultiplier = 1 - ((req - attribute) *2)/100.0;
        }
        else {
            reqDmgMultiplier = 1 + (attribute - req)/200.0;
        }
        return reqDmgMultiplier;
    }
    public void turnSetter(Character character){
        character.setPlayerTurn(!character.isPlayerTurn());
    }

    @Override
    public String toString() {
        return name;
    }

    public int getBonus() {
        return bonus;
    }
}


