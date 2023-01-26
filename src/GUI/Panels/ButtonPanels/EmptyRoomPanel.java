package GUI.Panels.ButtonPanels;

import BackEnd.Game.RoomEvent;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmptyRoomPanel extends JPanel implements Subject {
    private Game game;
    private JButton restButton;
    private ArrayList<Observer> observers = new ArrayList<>();

    public EmptyRoomPanel(Game game){
        this.game = game;
        Dimension d = new Dimension(900, 300);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
        JLabel text = new JLabel("Co by zrobić w pustym pokoju?");
        text.setFont(new Font("", Font.BOLD, 25));

        JPanel panelButtons = new JPanel();
        panelButtons.setOpaque(false);
        panelButtons.setLayout(new FlowLayout());

        restButton = restButton();
        panelButtons.add(restButton);
        panelButtons.add(useItemButton());
        panelButtons.add(statsDisplayButton());
        setBackground(new Color(199, 196, 181));
//        Border blackLine = BorderFactory.createLineBorder(Color.black);
//        this.setBorder(blackLine);

        this.add(Box.createRigidArea(new Dimension(900,70)));
        this.add(text);
        this.add(Box.createRigidArea(new Dimension(900,60)));
        this.add(panelButtons);
    }
    public JButton restButton(){
        JButton restButton = setBasicButton("Odpocznij");
        restButton.setBackground(new Color(136, 93, 44));
        if(game.getMap().getTabOfRoom()[game.getPlayer().getLocation_X()][game.getPlayer().getLocation_Y()].isRested()){
            restButton.setEnabled(false);
        }
        restButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.REST);
                notifyObservers();
            }
        });
        return restButton;
    }
    public JButton useItemButton(){
        JButton useItemButton = setBasicButton("Użyj przedmiotu");
        useItemButton.setBackground(new Color(136, 93, 44));
        useItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.INVENTORY);
                notifyObservers();
            }
        });
        return useItemButton;
    }

    public JButton statsDisplayButton(){
        JButton useItemButton = setBasicButton("Wyświetl statystyki");
        useItemButton.setBackground(new Color(136, 93, 44));
        useItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.STATS);
                notifyObservers();
            }
        });
        return useItemButton;
    }

    private JButton setBasicButton(String title){
        JButton button = new JButton(title);
        button.setBackground(new Color(136, 93, 44));
        button.setFont(new Font("czcionka", Font.BOLD|Font.ITALIC, 16));
        Dimension d = new Dimension(200, 50);
        button.setMinimumSize(d);
        button.setMaximumSize(d);
        button.setPreferredSize(d);
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
        for(int i = 0 ; i < observers.size(); i++)
            observers.get(i).update(game);
    }

    public JButton getRestButton(){
        return restButton;}


}
