package GUI.Panels.ButtonPanels;

import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestPanel extends JPanel implements Subject {

    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    private JButton button;

    public RestPanel(Game game){
        this.game = game;

        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));
        setBackground(new Color(199, 196, 181));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(900, 70)));


        JPanel panel = generatePanel();
        this.add(panel);

        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);

    }

    public JPanel generatePanel(){
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
      panel.setOpaque(false);



      switch (game.generateRestEvent()){
        case 0 :
            panel.add(textLabel("Wstajesz czując się nieco lepeij"));
            break;
        case 1 :
            panel.add(textLabel("Wstajesz czując się znacznie lepeij"));
            break;
        case 2 :
            panel.add(textLabel("Budzą cie 3 szybkie lecz bolesne ciosy kijem ale pokój wydaje się być pusty"));
            panel.add(textLabel("Zycie zmiejszone o 15 punktów"));
            break;
        case 3 :
            panel.add(textLabel("Wstajesz i ruszasz dalej"));
            break;
        case 4 :
            panel.add(textLabel("Budzą się wypoczęty lecz nigdzie nie możesz znaleść swojej torby"));
            break;
        case 5 :
            panel.add(textLabel("Budzisz się kompletnie nagi a po twoim sprzęcie ani śladu, ciekawe jako to sie stało ?"));
            break;
        }
        panel.add(Box.createRigidArea(new Dimension(900, 50)));

        JButton button = new JButton("Ruszaj dalej");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("", Font.BOLD, 15));
        button.setBackground(new Color(136, 93, 44));
        button.setPreferredSize(new Dimension(150,40));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.EMPTYROOM);
                notifyObservers();
            }
        });
        panel.add(button);
    return panel;}

    private JLabel textLabel(String str){
        JLabel text = new JLabel(str);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setFont(new Font("", Font.BOLD, 15));
        return text;
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
