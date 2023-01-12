package GUI.Panels;

import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapPanel extends JPanel implements Subject {
    private JButton[][] rooms;
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();


    public MapPanel(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(450, 400));
        rooms = new JButton[this.game.getMap().getTabOfRoom().length][this.game.getMap().getTabOfRoom()[0].length];
        this.setLayout(new GridLayout(this.game.getMap().getTabOfRoom().length, this.game.getMap().getTabOfRoom().length));
        for (int i = 0; i < this.game.getMap().getTabOfRoom().length; i++) {
            for (int j = 0; j < this.game.getMap().getTabOfRoom()[0].length; j++) {
                rooms[i][j] = createButton(i, j);
                switch (this.game.getMap().getRoomTypes()[i][j]) {
                    case withPlayer:
                        rooms[i][j].setText("Znajdujesz się tutaj");
                        break;
                    case hidden, empty:
                        rooms[i][j].setVisible(false);
                        break;
                }
                this.add(rooms[i][j]);
                rooms[i][j].setEnabled(this.game.getMap().getTabOfRoom()[i][j].isAvailable());
                if(this.game.getMap().getTabOfRoom()[i][j].isAvailable())
                    rooms[i][j].setText("?");
                this.add(rooms[i][j]);
            }
        }
    }
    public JButton createButton(int i, int j) {
        JButton room = new JButton();
//        try {
//            room.setIcon(new ImageIcon(ImageIO.read(new File("empty.png"))));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().setPlayerLocation(game.getPlayer(), game.getMap().getTabOfRoom()[i][j]);
                game.notifyObservers();
            }
        });
        return room;
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
        for (int i = 0; i < observers.size(); i++) {
            //observers.get(i).update();
        }
    }
}
