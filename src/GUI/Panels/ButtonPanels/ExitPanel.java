package GUI.Panels.ButtonPanels;

import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExitPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public ExitPanel(Game game){
        this.game = game;
        setLayout(new FlowLayout());
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));
        setBackground(new Color(199, 196, 181));
//        Border blackLine = BorderFactory.createLineBorder(Color.black);
//        this.setBorder(blackLine);

        JLabel text = new JLabel("Czy to jest wyjście?");
        text.setFont(new Font("Verdana",Font.BOLD,25));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton endGameButton = new JButton("Wyjście");
        endGameButton.setFont(new Font("", Font.BOLD, 15));
        //endGameButton.setSize(new Dimension(60, 40));
        endGameButton.setPreferredSize(new Dimension(150,50));
        endGameButton.setBackground(new Color(136, 93, 44));
        endGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers();
            }
        });
        add(Box.createRigidArea(new Dimension(900,50)));
        add(text);
        add(Box.createRigidArea(new Dimension(900,40)));
        this.add(endGameButton);
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
