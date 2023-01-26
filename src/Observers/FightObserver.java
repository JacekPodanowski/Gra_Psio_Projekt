package Observers;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.RoomEvent;
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
            fightPanel.getjButton5().setEnabled(false);
            fightPanel.getjButton6().setEnabled(false);
            fightPanel.getjButton7().setEnabled(false);
            fightPanel.getjButton8().setEnabled(false);
            if(player.getAgility() < enemy.getAgility())
                player.setPlayerTurn(false);
            if(player.isPlayerTurn()) {
                playerAttack(fightPanel.getAbilityChoice());
                if(!(enemy.getHealth() > 0))
                    refresh();
                if(!player.isPlayerTurn())
                    enemyAttack();
                if(!(player.getHealth() > 0))
                    refresh();
            }
            else {
                enemyAttack();
                if(!(player.getHealth() > 0))
                    refresh();
                playerAttack(fightPanel.getAbilityChoice());
                if(!(enemy.getHealth() > 0))
                    refresh();
            }
            fightPanel.getjButton5().setEnabled(true);
            fightPanel.getjButton6().setEnabled(true);
            fightPanel.getjButton7().setEnabled(true);
            if (!player.getUsedSpecial())
                fightPanel.getjButton8().setEnabled(true);
        }
        else {
            if(player.getHealth() > 0){
                bottomPanel.removeAll();
                bottomPanel.setLvlUpPanel(new LvlUpPanel(game));
                bottomPanel.add(bottomPanel.getLvlUpPanel());
                bottomPanel.getLvlUpPanel().registerObserver(new LvlUpObserver(bottomPanel));
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.LOOT);
                bottomPanel.notifyObservers();
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
    public void enemyAttack(){
        Random generate = new Random();
        int enemyChoice = generate.nextInt(0, 3);
        enemy.attack(player, enemyChoice);
        player.setPlayerTurn(true);
        fightPanel.getConsolePanel().newLine();
        fightPanel.getConsolePanel().newLine();
        fightPanel.getConsolePanel().setMessage(
                "Przeciwnik używa umiejętności " + '\"' + enemy.getAbilities()[enemyChoice].toString() + '\"' + " i zadaje ");
        if(player.isAttackAvoided())
            fightPanel.getConsolePanel().setMessage("0 obrażeń, ponieważ uniknąłeś ciosu.");
        else
            fightPanel.getConsolePanel().setMessage(enemy.getAbilities()[enemyChoice].calcDmg(enemy, player) + ".");
        fightPanel.getConsolePanel().newLine();
        fightPanel.getConsolePanel().setMessage("Twoje życie: " + player.getHealth());
        fightPanel.getConsolePanel().newLine();
        fightPanel.getConsolePanel().setMessage("Życie przeciwnika: " + enemy.getHealth());

        player.setAttackAvoided(false);
    }
    public void playerAttack(int playerChoice) {
        game.getPlayer().attack(game.getMap().getPlayerLocation(game.getPlayer()).getEnemy(), playerChoice);
        if(!(fightPanel.getAbilityChoice() == 3 && player.getProfession() instanceof Warrior))
            player.setPlayerTurn(false);
        fightPanel.getConsolePanel().newLine();
        fightPanel.getConsolePanel().newLine();
        switch (playerChoice) {
            case 1, 2, 0:
                fightPanel.getConsolePanel().setMessage(
                        "Używasz umiejętności " + '\"' + player.getAbilities()[playerChoice].toString() + '\"' + " i zadajesz ");
                if (enemy.isAttackAvoided())
                    fightPanel.getConsolePanel().setMessage("0 obrażeń, ponieważ przeciwnik uniknął ciosu.");
                else
                    fightPanel.getConsolePanel().setMessage(player.getAbilities()[playerChoice].calcDmg(player, enemy) + " obrażeń.");
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Twoje życie: " + player.getHealth());
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Życie przeciwnika: " + enemy.getHealth());
                break;
            case 3:
                if(player.getProfession() instanceof Mage){
                    fightPanel.getConsolePanel().setMessage("Używasz specjalnej umiejętności "
                            + player.getAbilities()[playerChoice].toString()
                            + " i leczysz " + player.getAbilities()[playerChoice].getBonus() * 10 + " obrażeń.");
                } else if (player.getProfession() instanceof Warrior) {
                    fightPanel.getConsolePanel().setMessage(
                            "Używasz specjalnej umiejętności " + '\"' +  player.getAbilities()[playerChoice].toString()
                                    + '\"' + ", ogłuszasz przeciwnika i zadajesz ");
                } else {
                    fightPanel.getConsolePanel().setMessage(
                            "Używasz specjalnej umiejętności " + '\"' + player.getAbilities()[playerChoice].toString()
                                    + '\"' + ", wystrzelasz potężną strzałę i zadajesz ");
                }
                if(!(player.getProfession() instanceof Mage))
                    if (enemy.isAttackAvoided())
                        fightPanel.getConsolePanel().setMessage("0 obrażeń, ponieważ przeciwnik uniknął ciosu.");
                    else
                        fightPanel.getConsolePanel().setMessage(player.getAbilities()[playerChoice].calcDmg(player, enemy) + " obrażeń.");
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Twoje życie: " + player.getHealth());
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Życie przeciwnika: " + enemy.getHealth());
                break;
        }

        if(fightPanel.getAbilityChoice()==3)
            player.setUsedSpecial(true);
        enemy.setAttackAvoided(false);
    }
}
