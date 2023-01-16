package Observers;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Event.Fight;
import BackEnd.Game.Game;
import GUI.Panels.BottomPanel;
import GUI.Panels.ButtonPanels.FightPanel;

public class FightObserver implements Observer{
    private FightPanel fightPanel;
    private BottomPanel bottomPanel;
    private Player player;
    private Enemy enemy;
    public FightObserver(BottomPanel bottomPanel){
        player = bottomPanel.getGame().getPlayer();
        fightPanel = bottomPanel.getFightPanel();
        fightPanel.registerObserver(this);
    }
    @Override
    public void update(Game game) {
        player = game.getPlayer();
        enemy = ((Fight) game.getMap().getPlayerLocation(game.getPlayer()).getEvent()).getEnemy();
        refresh();
    }
    public void refresh(){
        if(player.getHealth() > 0 && enemy.getHealth() > 0){
            if(player.isPlayerTurn()) {
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Przeciwnik atakuje i zadaje " + enemy.getAbilities()[fightPanel.getAbilityChoice()].use(player, enemy) + " obrażeń.");
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Twoje życie: " + player.getHealth());
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Życie przeciwnika: " + enemy.getHealth());

                fightPanel.getjButton5().setEnabled(true);
                fightPanel.getjButton6().setEnabled(true);
                fightPanel.getjButton7().setEnabled(true);
                fightPanel.getjButton8().setEnabled(true);
            }
            else {
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Atakujesz i zadajesz " + player.getAbilities()[fightPanel.getAbilityChoice()].use(player, enemy) + " obrażeń.");
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Twoje życie: " + player.getHealth());
                fightPanel.getConsolePanel().newLine();
                fightPanel.getConsolePanel().setMessage("Życie przeciwnika: " + enemy.getHealth());

                fightPanel.getjButton5().setEnabled(false);
                fightPanel.getjButton6().setEnabled(false);
                fightPanel.getjButton7().setEnabled(false);
                fightPanel.getjButton8().setEnabled(false);
            }
        }
        else {
            if(player.getHealth() > 0){
                //win
            }
            else {
                //lose
            }
        }
    }
}
