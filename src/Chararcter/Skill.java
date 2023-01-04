package Chararcter;

import java.util.Random;

public class Skill {


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

    public double Use(Player player, Enemy enemy){

        double dmg;
        int resist = 0;
        double reqDmgMultiplier = 1;

        switch (player.getWeapon().getType()) {
            case 'S':
                resist = enemy.getArmor().getStrengthProtection();
                reqDmgMultiplier = this.reqDmgMultiplier(player, player.getStrength());
                if (this.bonus != 0)
                    this.turnSetter(player);
                break;

            case 'I':
                resist = enemy.getArmor().getMagicProtection();
                if (this.bonus == 0) {
                    reqDmgMultiplier = this.reqDmgMultiplier(player, player.getIntelligence());
                    this.turnSetter(player);
                } else {
                    player.setHealth(player.getHealth() + this.bonus * 10);
                    this.turnSetter(player);
                }
                break;

            case 'A':
                resist = enemy.getArmor().getAgilityProtection();
                reqDmgMultiplier = this.reqDmgMultiplier(player, player.getIntelligence());
                if (this.bonus != 0)
                    this.turnSetter(player);
                break;
        }
            //==================================REZISTY==============================================

        int Basicdmg = player.getWeapon().getBasicDMG();
        dmg = Basicdmg * reqDmgMultiplier * damageMultiplier;
        dmg = dmg * (1 - (double)(resist * 5) / 100);


        Random r = new Random();
        if(r.nextInt(100) > accuracy) {
            System.out.println("Nie trafiasz");
            dmg = 0;
        }
        return dmg;
    }
    public double reqDmgMultiplier(Player player, int attribute){
        double reqDmgMultiplier = 1;
        int req = player.getWeapon().getRequirement();
        if (attribute < req) {
            reqDmgMultiplier = 1 - (req - attribute) / 10.0;
        } else
            reqDmgMultiplier = 1 + (req - attribute) / 10.0;
        return reqDmgMultiplier;
    }
    public void turnSetter(Player player){
        if(player.isPlayerTurn())
            player.setPlayerTurn(false);
        else
            player.setPlayerTurn(true);
    }

    @Override
    public String toString() {
        return name;
    }
}


