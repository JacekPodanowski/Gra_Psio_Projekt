package GUI.Panels;

import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LostGamePanel extends JPanel implements Subject {

    private ArrayList<Observer> observers = new ArrayList<>();
    private Game game;

    public LostGamePanel(Game game){
        this.game = game;
        this.setMinimumSize(new Dimension(900, 700));
        this.setMaximumSize(new Dimension(900, 700));
        this.setPreferredSize(new Dimension(900, 700));
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        //JLabel endText1 = new JLabel("NIE ZYJESZ");
        JLabel endText1 = new JLabel(new ImageIcon("images/YouDied.png"));
        endText1.setAlignmentX(Component.CENTER_ALIGNMENT);

        endText1.setFont(new Font("ButtonFont", Font.BOLD, 70));
        this.add(Box.createRigidArea(new Dimension(900,150)));
        this.add(endText1);
        this.add(Box.createRigidArea(new Dimension(900, 250)));
        this.add(newGameButton());

    }

    public JButton newGameButton(){
        JButton newGameButton = new JButton("Zmartwychwstanie");
        newGameButton.setFont(new Font("ButtonFont", Font.BOLD, 15));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setBackground(new Color(136, 93, 44));
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.finishGame();
                notifyObservers();
            }
        });

        return newGameButton;}


    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(game);
        }
    }
}
