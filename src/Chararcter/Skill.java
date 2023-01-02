package Chararcter;

import Chararcter.Item.Weapon;

public class Skill {


    private String name;
    private double damageMultiPlayer;
    private int accuracy;
    private int bonus;

    public Skill(String name, double damageMultiPlayer, int accuracy, int bonus) {
        this.name = name;
        this.damageMultiPlayer = damageMultiPlayer;
        this.accuracy = accuracy;
        this.bonus = bonus;
    }

    public double Use(Character player, Character enemy){

        double dmg;
        int resist=0;
        int req = player.getWeapon().getRequirement();

        double reqDmgmuliplyer=1;
        char type = player.getWeapon().getType();

        switch (type) {
            case 'S':
                resist=enemy.getArmor().getStrengthProtection();
                if (player.getStrength() < req) {
                    reqDmgmuliplyer = 1 - (req - player.getStrength()) / 10.0;
                } else
                    reqDmgmuliplyer = 1 + (req - player.getStrength()) / 10.0;
                break;

            case 'I':
                resist=enemy.getArmor().getMagicProtection();
                if (player.getIntelligence() < req) {
                    reqDmgmuliplyer = 1 - (req - player.getIntelligence()) / 10.0;
                } else
                    reqDmgmuliplyer = 1 + (req - player.getIntelligence()) / 10.0;
                break;

            case 'A':
                resist=enemy.getArmor().getAgilityProtection();
                if (player.getAgility() < req) {
                    reqDmgmuliplyer = 1 - (req - player.getAgility()) / 10.0;
                } else
                    reqDmgmuliplyer = 1 + (req - player.getAgility()) / 10.0;
                break;
        }
            //==================================REZISTY==============================================

        int Basicdmg = player.getWeapon().getBasicDMG();
        dmg = Basicdmg*reqDmgmuliplyer*damageMultiPlayer;
        dmg=dmg*(1-(double)(resist*5)/100);
        return dmg;
        }
    }


