package GUI.Panels;

import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EndGamePanel extends JPanel implements Subject {


    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();

    public EndGamePanel(Game game){
        this.game = game;
        this.setMinimumSize(new Dimension(900, 700));
        this.setMaximumSize(new Dimension(900, 700));
        this.setPreferredSize(new Dimension(900, 700));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.BLACK);

        JLabel endText1 = new JLabel("Uratowałeś swoje życie i wyszedłeś z Kostki");
        endText1.setForeground(new Color(236, 168, 60));
        endText1.setFont(new Font("ButtonFont", Font.BOLD, 30));
        endText1.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(900, 250)));
        this.add(endText1);

        JLabel endText2 = new JLabel("Zdecyduj dalej co chcesz zrobic ze swoim życiem");
        endText2.setForeground(new Color(236, 168, 60));
        endText2.setFont(new Font("ButtonFont", Font.BOLD, 25));
        endText2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(900,50)));
        this.add(endText2);
        this.add(Box.createRigidArea(new Dimension(900, 200)));
        this.add(newGameButton());
    }

    public JButton newGameButton(){
        JButton newGameButton = new JButton("Wracam do Kostki");
        newGameButton.setBackground(new Color(136, 93, 44));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setFont(new Font("ButtonFont", Font.BOLD, 15));
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
