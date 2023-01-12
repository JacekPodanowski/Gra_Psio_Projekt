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


    public MapPanel() {
        this.setPreferredSize(new Dimension(450, 400));
        rooms = new JButton[game.getMap().getTabOfRoom().length][game.getMap().getTabOfRoom()[0].length];
        this.setLayout(new GridLayout(game.getMap().getTabOfRoom().length, game.getMap().getTabOfRoom().length));
        for (int i = 0; i < game.getMap().getTabOfRoom().length; i++) {
            for (int j = 0; j < game.getMap().getTabOfRoom()[0].length; j++) {
                switch (game.getMap().getRoomTypes()[i][j]) {
                    case empty:
                        rooms[i][j] = createButton(i, j);
                        break;
                    case visited:
                        rooms[i][j] = createButton(i, j);
                        break;
                    case withPlayer:
                        rooms[i][j] = createButton(i, j);
                        break;
                    case hidden:
                        rooms[i][j] = createButton(i, j);
                        break;
                }
                this.add(rooms[i][j]);
                rooms[i][j].setEnabled(game.getMap().getTabOfRoom()[i][j].isAvailable());
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
