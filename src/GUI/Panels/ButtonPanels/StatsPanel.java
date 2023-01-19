package GUI.Panels.ButtonPanels;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatsPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public StatsPanel(Game game){
        this.game = game;
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(goBack());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
        JPanel equipment = new JPanel();
        equipment.setLayout(new BoxLayout(equipment, BoxLayout.PAGE_AXIS));
        equipment.add(new JLabel("Twoja aktualna broń: "));
        equipment.add(currentWeapon());
        equipment.add(Box.createRigidArea(new Dimension(0, 10)));
        equipment.add(new JLabel("Twoja aktualna zbroja: "));
        equipment.add(currentArmor());
        equipment.add(Box.createRigidArea(new Dimension(0, 10)));

        equipment.add(currentHealth());
        equipment.add(Box.createRigidArea(new Dimension(0, 10)));
        equipment.add(currentStrngth());
        equipment.add(Box.createRigidArea(new Dimension(0, 10)));
        equipment.add(currentInteligence());
        equipment.add(Box.createRigidArea(new Dimension(0, 10)));
        equipment.add(currentAgility());
        equipment.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(equipment);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(panel);
    }

    public JLabel currentWeapon(){
        JLabel currentWeapon = new JLabel(game.getPlayer().getWeapon().toString());
        return currentWeapon;
    }
    public JLabel currentArmor(){
        JLabel currentArmor = new JLabel(game.getPlayer().getArmor().toString());
        return currentArmor;
    }
    public JLabel currentHealth(){
        JLabel currentStats = new JLabel("Życie : "+game.getPlayer().getHealth());
        return currentStats;
    }
    public JLabel currentStrngth(){
        JLabel currentStats = new JLabel("Siła : "+game.getPlayer().getStrength());
        return currentStats;
    }
    public JLabel currentInteligence(){
        JLabel currentStats = new JLabel("Inteligencja : "+game.getPlayer().getIntelligence());
        return currentStats;
    }
    public JLabel currentAgility(){
        JLabel currentStats = new JLabel("Zwinność : "+game.getPlayer().getAgility());
        return currentStats;
    }

    public JButton goBack(){
        JButton button = new JButton("Powrót");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.EMPTYROOM);
                notifyObservers();
            }
        });
        return button;
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
            observers.get(i).update(game);
    }
}
