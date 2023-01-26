package GUI.Panels;

import BackEnd.Chararcter.Profession.Archer;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import BackEnd.Game.RoomEvent;
import Observable.Subject;
import Observers.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Subject {
    private ArrayList<Observer> observers = new ArrayList<>();
    private Game game;
    public GamePanel(Game game){
        this.game = game;
        this.setPreferredSize(new Dimension(450, 400));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);
        Border blackLine = BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK);
        this.setBorder(blackLine);


        if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.ENTRANCE) {
            this.add(new JLabel(new ImageIcon("images/Entrance.png")));

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.EMPTYROOM ||
        game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.INVENTORY ||
        game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.STATS ||
        game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.REST) {
            this.add(new JLabel(new ImageIcon("images/RoomEmpty.png")));


        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.EXIT) {
            this.add(new JLabel(new ImageIcon("images/Exit.png")));


        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.FIGHT) {
            if (game.getPlayer().getProfession() instanceof Mage)
                this.add(new JLabel(new ImageIcon("images/RoomFightMage.png")));
            else if (game.getPlayer().getProfession() instanceof Archer) {
                this.add(new JLabel(new ImageIcon("images/RoomFightArcher.png")));
            } else if (game.getPlayer().getProfession() instanceof Warrior) {
                this.add(new JLabel(new ImageIcon("images/RoomFightWarrior.png")));
            }


            for (int i = 0; i < game.getPlayer().getAbilities().length; i++) ;
        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.LOOT ||
            game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() == RoomEvent.FULLEQ) {
            this.add(new JLabel(new ImageIcon("images/RoomLoot.png")));

        }



        //return gamePanel;

    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
