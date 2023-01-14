package GUI.Panels;

import BackEnd.Game.*;
import BackEnd.Game.Event.*;
import BackEnd.Game.Event.Event;
import GUI.Panels.ButtonPanels.*;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class BottomPanel extends JPanel implements Subject {
    private EmptyRoomPanel emptyRoomPanel;
    private EntrancePanel entrancePanel;
    private FightPanel fightPanel;
    private LootPanel lootPanel;
    private ExitPanel exitPanel;
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();

    public BottomPanel(Game game){
        this.game = game;
        this.setPreferredSize(new Dimension(900, 300));
        this.setMinimumSize(new Dimension(900, 300));
        this.setPreferredSize(new Dimension(900, 300));
        if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Entrance) {
            entrancePanel = new EntrancePanel(game);
            this.add(entrancePanel);
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof EmptyRoom) {
            emptyRoomPanel = new EmptyRoomPanel();
            this.add(emptyRoomPanel);
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Exit) {
            exitPanel = new ExitPanel();
            this.add(exitPanel);
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Fight) {

            fightPanel = new FightPanel(game);
            this.add(fightPanel);
            for (int i = 0; i < game.getPlayer().getAbilities().length; i++);

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Loot) {
            lootPanel = new LootPanel();
            this.add(lootPanel);
        }
        
        fightPanel = new FightPanel(game);//tu ma byc Game game
        this.add(fightPanel);
    }



    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(this.getGame());
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
