package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class RestPanel extends JPanel implements Subject {

    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    private JButton button;

    public RestPanel(Game game){
        this.game = game;
        this.add(generateLabel());

        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        this.button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers();
            }
        });
        panel1.add(button);
        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        this.add(Box.createRigidArea(new Dimension(0, 100)));

        JPanel panel2 = generateLabel();
        panel2.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        this.add(panel1);
        this.add(panel2);

        Border blackLine = BorderFactory.createLineBorder(Color.black);
        panel1.setBorder(blackLine);

    }

    public JPanel generateLabel(){
      JPanel panel = new JPanel();

      switch (game.generateRestEvent()){
        case 0 :
            panel.add(new JLabel("Wstajesz czując się nieco lepeij"));
            break;
        case 1 :
            panel.add(new JLabel("Wstajesz czując się znacznie lepeij"));
            break;
        case 2 :
            panel.add(new JLabel("Budzą cie 3 szybkie lecz bolesne ciosy kijem ale pokój wydaje się być pusty"));
            panel.add(new JLabel("|Zycie zmiejszone o 15 punktów|"));
            break;
        case 3 :
            panel.add(new JLabel("Wstajesz i ruszasz dalej"));
            break;
        case 4 :
            panel.add(new JLabel("Budzą się wypoczęty lecz nigdzie nie możesz znaleść swojej torby"));
            break;
        case 5 :
            panel.add(new JLabel("Budzisz się kompletnie nagi a po twoim sprzęcie ani śladu, ciekawe jako to sie stało ?"));
            break;
    }
    return panel;}


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
