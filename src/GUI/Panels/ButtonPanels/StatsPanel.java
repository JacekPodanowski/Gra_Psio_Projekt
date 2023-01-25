package GUI.Panels.ButtonPanels;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatsPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public StatsPanel(Game game){
        this.game = game;
        this.setMinimumSize(new Dimension(900, 300));
        this.setMaximumSize(new Dimension(900, 300));
        this.setPreferredSize(new Dimension(900, 300));
        setBackground(new Color(199, 196, 181));
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        JPanel panel = new JPanel();
        panel.setBorder(blackLine);
        panel.setLayout(new FlowLayout());
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(750,150));
        panel.setMaximumSize(new Dimension(750,150));
        panel.setMinimumSize(new Dimension(750,150));

        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(equipment1());
        panel.add(Box.createRigidArea(new Dimension(50,100)));
        panel.add(equipment2());

        this.add(Box.createRigidArea(new Dimension(900,50)));
        this.add(panel);
        this.add(Box.createRigidArea(new Dimension(900,10)));
        this.add(goBack());
    }

    private JPanel equipment1(){
        JPanel panel = new JPanel();
        //panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(300,180));
        panel.setMaximumSize(new Dimension(300,180));
        panel.setMinimumSize(new Dimension(300,180));

        panel.add(new JLabel("Twoja aktualna broń: "));
        panel.add(currentWeapon());
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(new JLabel("Twoja aktualna zbroja: "));
        panel.add(currentArmor());

        return panel;
    }

    private JPanel equipment2(){
        JPanel panel = new JPanel();
        //panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(300,180));
        panel.setMaximumSize(new Dimension(300,180));
        panel.setMinimumSize(new Dimension(300,180));

        panel.add(currentHealth());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(currentStrngth());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(currentInteligence());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(currentAgility());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        return panel;
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
        button.setFont(new Font("", Font.BOLD, 15));
        button.setBackground(new Color(136, 93, 44));
        button.setPreferredSize(new Dimension(100,30));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
