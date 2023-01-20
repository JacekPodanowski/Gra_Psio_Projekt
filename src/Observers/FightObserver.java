package Observers;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
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

            fightPanel.getConsolePanel().setMessage("Atakujesz i zadajesz " + player.getAbilities()[fightPanel.getAbilityChoice()].use(player, enemy) + " obrażeń.");
            fightPanel.getConsolePanel().newLine();
            fightPanel.getConsolePanel().setMessage("Twoje życie: " + player.getHealth());
            fightPanel.getConsolePanel().newLine();
            fightPanel.getConsolePanel().setMessage("Życie przeciwnika: " + enemy.getHealth());
            fightPanel.getConsolePanel().newLine();
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
                bottomPanel.revalidate();
                bottomPanel.repaint();
            }
        }
    }
    public void enemyAttack(int enemyChoice){
        switch(enemyChoice){
            case 0:
                fightPanel.getConsolePanel().setMessage("Przeciwnik atakuje i zadaje " + enemy.getAbilities()[fightPanel.getAbilityChoice()].use(player, enemy) + " obrażeń.");
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Twoje życie: " + player.getHealth());
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Życie przeciwnika: " + enemy.getHealth());
                fightPanel.getConsolePanel().newLine();
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }
    public void playerAttack(){

    }
}
