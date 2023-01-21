package GUI.Panels;

import BackEnd.Game.*;
import BackEnd.Game.Event.*;
import BackEnd.Game.Event.Event;
import GUI.Panels.ButtonPanels.*;
import Observable.Subject;
import Observers.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class BottomPanel extends JPanel implements Subject {
    private EmptyRoomPanel emptyRoomPanel;
    private EntrancePanel entrancePanel;
    private FightPanel fightPanel;
    private LootPanel lootPanel;
    private ProfessionChoosePanel professionChoosePanel;
    private ExitPanel exitPanel;
    private Game game;
    private LvlUpPanel lvlUpPanel;
    private InventoryPanel inventoryPanel;
    private RestPanel restPanel;
    private StatsPanel statsPanel;
    private ArrayList<Observer> observers = new ArrayList<>();
    public BottomPanel(Game game){
        this.game = game;
        this.setPreferredSize(new Dimension(900, 300));
        this.setMinimumSize(new Dimension(900, 300));
        this.setPreferredSize(new Dimension(900, 300));
        this.setLayout(new FlowLayout());
        Border blackLine = BorderFactory.createLineBorder(Color.black);

        //this.setBorder(blackLine);
        if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Entrance) {
        this.setBorder(blackLine);
        if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.ENTRANCE) {
            entrancePanel = new EntrancePanel(game);
            this.add(entrancePanel);
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.EMPTYROOM) {
            emptyRoomPanel = new EmptyRoomPanel(game);
            emptyRoomPanel.registerObserver(new EmptyRoomObserver(this));
            this.add(emptyRoomPanel);
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.EXIT) {
            exitPanel = new ExitPanel(game);
            exitPanel.registerObserver(new ExitObserver(this));
            this.add(exitPanel);
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.FIGHT) {
            fightPanel = new FightPanel(game);
            new FightObserver(this);
            this.add(fightPanel);
            for (int i = 0; i < game.getPlayer().getAbilities().length; i++);
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.LOOT) {
            lootPanel = new LootPanel(game);
            lootPanel.registerObserver(new LootObserver(this));
            this.add(lootPanel);
        }
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

    public FightPanel getFightPanel() {
        return fightPanel;
    }

    public LvlUpPanel getLvlUpPanel() {
        return lvlUpPanel;
    }

    public void setLvlUpPanel(LvlUpPanel lvlUpPanel) {
        this.lvlUpPanel = lvlUpPanel;
    }

    public LootPanel getLootPanel() {
        return lootPanel;
    }

    public void setLootPanel(LootPanel lootPanel) {
        this.lootPanel = lootPanel;
    }

    public EmptyRoomPanel getEmptyRoomPanel() {
        return emptyRoomPanel;
    }

    public void setEmptyRoomPanel(EmptyRoomPanel emptyRoomPanel) {
        this.emptyRoomPanel = emptyRoomPanel;
    }

    public InventoryPanel getInventoryPanel() {
        return inventoryPanel;
    }

    public void setInventoryPanel(InventoryPanel inventoryPanel) {
        this.inventoryPanel = inventoryPanel;
    }

    public RestPanel getRestPanel() {
        return restPanel;
    }

    public void setRestPanel(RestPanel restPanel) {
        this.restPanel = restPanel;
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public void setStatsPanel(StatsPanel statsPanel) {
        this.statsPanel = statsPanel;
    }

    public ExitPanel getExitPanel() {
        return exitPanel;
    }

    public void setExitPanel(ExitPanel exitPanel) {
        this.exitPanel = exitPanel;
    }
}
