package GUI.Panels;

import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        this.add(Box.createRigidArea(new Dimension(900, 100)));
        this.add(newGameButton());

    }

    public JButton newGameButton(){
        JButton newGameButton = new JButton("Wróć do głównego menu");
        newGameButton.setFont(new Font("ButtonFont", Font.BOLD, 20));
        newGameButton.setForeground(Color.getHSBColor(0,1,0.5f));
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton.setMinimumSize(new Dimension(500, 60));
        newGameButton.setMaximumSize(new Dimension(500, 60));
        newGameButton.setPreferredSize(new Dimension(500, 60));

        newGameButton.setFocusable(false);
        newGameButton.setBorderPainted(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.finishGame();
                notifyObservers();
            }
        });
        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newGameButton.setBackground(Color.getHSBColor(30,1,0.1f));
                newGameButton.repaint();
                newGameButton.revalidate();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                newGameButton.setBackground(Color.BLACK);
                newGameButton.repaint();
                newGameButton.revalidate();
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
