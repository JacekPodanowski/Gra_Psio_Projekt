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

        JLabel tytul = new JLabel("Twoja statystyka");
        tytul.setPreferredSize(new Dimension(700,30));
        tytul.setAlignmentX(Component.CENTER_ALIGNMENT);
        tytul.setFont(new Font("", Font.BOLD, 25));
        this.add(tytul);

        JPanel panel = new JPanel();
        panel.setBorder(blackLine);
        panel.setLayout(new FlowLayout());
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(750,200));
        panel.setMaximumSize(new Dimension(750,200));
        panel.setMinimumSize(new Dimension(750,200));

        Font font = new Font("", Font.BOLD, 16);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(equipment1(font));
        panel.add(Box.createRigidArea(new Dimension(50,100)));
        panel.add(equipment2(font));

        //this.add(Box.createRigidArea(new Dimension(900,10)));
        this.add(panel);
        this.add(Box.createRigidArea(new Dimension(900,10)));
        this.add(goBack());
    }

    private JPanel equipment1(Font font){
        JPanel panel = new JPanel();
        //panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(300,180));
        panel.setMaximumSize(new Dimension(300,180));
        panel.setMinimumSize(new Dimension(300,180));

        JLabel textBron = new JLabel("Twoja aktualna broń: ");
        textBron.setFont(font);
        panel.add(textBron);
        panel.add(currentWeapon(font));
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        JLabel textArmor =new JLabel("Twoja aktualna zbroja: ");
        textArmor.setFont(font);
        panel.add(textArmor);
        panel.add(currentArmor(font));

        return panel;
    }

    private JPanel equipment2(Font font){
        JPanel panel = new JPanel();
        //panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(300,180));
        panel.setMaximumSize(new Dimension(300,180));
        panel.setMinimumSize(new Dimension(300,180));

        panel.add(currentHealth(font));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(currentStrngth(font));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(currentInteligence(font));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(currentAgility(font));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        return panel;
    }

    public JLabel currentWeapon(Font font){
        JLabel currentWeapon = new JLabel(game.getPlayer().getWeapon().toString());
        currentWeapon.setFont(font);
        return currentWeapon;
    }
    public JLabel currentArmor(Font font){
        JLabel currentArmor = new JLabel(game.getPlayer().getArmor().toString());
        currentArmor.setFont(font);
        return currentArmor;
    }
    public JLabel currentHealth(Font font){
        JLabel currentStats = new JLabel("Życie : "+game.getPlayer().getHealth());
        currentStats.setFont(font);
        return currentStats;
    }
    public JLabel currentStrngth(Font font){
        JLabel currentStats = new JLabel("Siła : "+game.getPlayer().getStrength());
        currentStats.setFont(font);
        return currentStats;
    }
    public JLabel currentInteligence(Font font){
        JLabel currentStats = new JLabel("Inteligencja : "+game.getPlayer().getIntelligence());
        currentStats.setFont(font);
        return currentStats;
    }
    public JLabel currentAgility(Font font){
        JLabel currentStats = new JLabel("Zwinność : "+game.getPlayer().getAgility());
        currentStats.setFont(font);
        return currentStats;
    }

    public JButton goBack(){
        JButton button = new JButton("Powrót");
        button.setFont(new Font("", Font.BOLD, 18));
        button.setBackground(new Color(136, 93, 44));
        button.setPreferredSize(new Dimension(120,40));
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
