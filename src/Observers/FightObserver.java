package Observers;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Event.Fight;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.FightPanel;
import GUI.Panels.ButtonPanels.LvlUpPanel;

import java.util.Random;

public class FightObserver implements Observer{
    private FightPanel fightPanel;
    private BottomPanel bottomPanel;
    private Player player;
    private Enemy enemy;
    private Game game;
    public FightObserver(BottomPanel bottomPanel){
        this.bottomPanel = bottomPanel;
        player = bottomPanel.getGame().getPlayer();
        fightPanel = bottomPanel.getFightPanel();
        fightPanel.registerObserver(this);
    }
    @Override
    public void update(Game game) {
        this.game = game;
        player = game.getPlayer();
        enemy = game.getMap().getPlayerLocation(game.getPlayer()).getEnemy();
        refresh();
    }
    public void refresh(){
        if(player.getHealth() > 0 && enemy.getHealth() > 0){
            if(fightPanel.getAbilityChoice()==3)
                player.setUsedSpecial(true);

            playerAttack(fightPanel.getAbilityChoice());
            enemy.setAttackAvoided(false);

            fightPanel.getjButton5().setEnabled(false);
            fightPanel.getjButton6().setEnabled(false);
            fightPanel.getjButton7().setEnabled(false);
            fightPanel.getjButton8().setEnabled(false);

            Random generate = new Random();
            int enemyChoice = generate.nextInt(0, 3);
            enemy.attack(player, enemyChoice);
            enemyAttack(enemyChoice);
            enemy.setAttackAvoided(false);

            fightPanel.getjButton5().setEnabled(true);
            fightPanel.getjButton6().setEnabled(true);
            fightPanel.getjButton7().setEnabled(true);
            if(!player.getUsedSpecial())
                fightPanel.getjButton8().setEnabled(true);
        }
        else {
            if(player.getHealth() > 0){
                bottomPanel.removeAll();
                bottomPanel.setLvlUpPanel(new LvlUpPanel(game));
                bottomPanel.add(bottomPanel.getLvlUpPanel());
                bottomPanel.getLvlUpPanel().registerObserver(new LvlUpObserver(bottomPanel));
                bottomPanel.revalidate();
                bottomPanel.repaint();
                enemy.setUsedSpecial(false);
                player.setUsedSpecial(false);
            }
            else {
                bottomPanel.removeAll();
                bottomPanel.notifyObservers();
            }
        }
    }
    public void enemyAttack(int enemyChoice){
        fightPanel.getConsolePanel().setMessage(
                "Przeciwnik u??ywa umiej??tno??ci " + '\"' + enemy.getAbilities()[enemyChoice].toString() + '\"' + " i zadaje ");
        if(player.isAttackAvoided())
            fightPanel.getConsolePanel().setMessage("0 obra??e??, poniewa?? unikn????e?? ciosu.");
        else
            fightPanel.getConsolePanel().setMessage(enemy.getAbilities()[enemyChoice].calcDmg(enemy, player) + ".");
        fightPanel.getConsolePanel().newLine();
        fightPanel.getConsolePanel().setMessage("Twoje ??ycie: " + player.getHealth());
        fightPanel.getConsolePanel().newLine();
        fightPanel.getConsolePanel().setMessage("??ycie przeciwnika: " + enemy.getHealth());
        fightPanel.getConsolePanel().newLine();
    }
    public void playerAttack(int playerChoice) {
        switch (playerChoice) {
            case 1, 2, 0:
                fightPanel.getConsolePanel().setMessage(
                        "U??ywasz umiej??tno??ci " + '\"' + player.getAbilities()[playerChoice].toString() + '\"' + " i zadajesz ");
                if (enemy.isAttackAvoided())
                    fightPanel.getConsolePanel().setMessage("0 obra??e??, poniewa?? przeciwnik unikn???? ciosu.");
                else
                    fightPanel.getConsolePanel().setMessage(enemy.getAbilities()[playerChoice].calcDmg(player, enemy) + " obra??e??.");
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Twoje ??ycie: " + player.getHealth());
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("??ycie przeciwnika: " + enemy.getHealth());
                fightPanel.getConsolePanel().newLine();
                break;
            case 3:
                if(player.getProfession() instanceof Mage){
                    fightPanel.getConsolePanel().setMessage("U??ywasz specjalnej umiej??tno??ci "
                            + player.getAbilities()[playerChoice].toString()
                            + " i leczysz " + player.getAbilities()[playerChoice].getBonus() * 10 + " obra??e??.");
                } else if (player.getProfession() instanceof Warrior) {
                    fightPanel.getConsolePanel().setMessage(
                            "U??ywasz specjalnej umiej??tno??ci " + '\"' +  player.getAbilities()[playerChoice].toString()
                                    + '\"' + ", og??uszasz przeciwnika i zadajesz ");
                } else {
                    fightPanel.getConsolePanel().setMessage(
                            "U??ywasz specjalnej umiej??tno??ci " + '\"' + player.getAbilities()[playerChoice].toString()
                                    + '\"' + ", wystrzelasz pot????n?? strza???? i zadajesz ");
                }
                if(!(player.getProfession() instanceof Mage))
                    if (enemy.isAttackAvoided())
                        fightPanel.getConsolePanel().setMessage("0 obra??e??, poniewa?? przeciwnik unikn???? ciosu.");
                    else
                        fightPanel.getConsolePanel().setMessage(enemy.getAbilities()[playerChoice].calcDmg(player, enemy) + " obra??e??.");
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Twoje ??ycie: " + player.getHealth());
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("??ycie przeciwnika: " + enemy.getHealth());
                fightPanel.getConsolePanel().newLine();
                break;
        }
    }
}
