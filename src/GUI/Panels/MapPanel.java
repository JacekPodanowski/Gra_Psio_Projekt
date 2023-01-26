package GUI.Panels;

import BackEnd.Game.RoomEvent;
import BackEnd.Game.Game;
import BackEnd.Game.RoomType;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
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
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
        rooms = new JButton[this.game.getMap().getTabOfRoom().length][this.game.getMap().getTabOfRoom()[0].length];
        this.setLayout(new GridLayout(this.game.getMap().getTabOfRoom().length, this.game.getMap().getTabOfRoom().length));
        this.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < this.game.getMap().getTabOfRoom().length; i++) {
            for (int j = 0; j < this.game.getMap().getTabOfRoom()[0].length; j++) {
                rooms[i][j] = createButton(i, j);

                if(this.game.getMap().getTabOfRoom()[i][j].isAvailable()) {
                    rooms[i][j].setVisible(true);
                    rooms[i][j].setEnabled(true);
                    rooms[i][j].setBackground(new Color(180, 87,10));
                    if (!this.game.getMap().getTabOfRoom()[i][j].isVisited()) {
                        rooms[i][j].setText("?");
                        rooms[i][j].setFont(new Font("", Font.BOLD, 40));
                        rooms[i][j].setBackground(new Color(131, 60, 60));
                    }
                } else {
                    if (this.game.getMap().getTabOfRoom()[i][j].isVisited()) {
                        rooms[i][j].setVisible(true);
                        rooms[i][j].setEnabled(false);
                        rooms[i][j].setBackground(new Color(168, 118, 94));
                    } else {
                        rooms[i][j].setEnabled(false);
                        rooms[i][j].setVisible(false);
                        rooms[i][j].setBackground(new Color(168, 118, 94));
                    }
                }
                if (this.game.getMap().getRoomTypes()[i][j] == RoomType.withPlayer) {
                    ImageIcon charSymbol = new ImageIcon("images/CharSymbol.png");
                    JLabel jLabel = new JLabel(charSymbol);
                    rooms[i][j].add(jLabel);
                    rooms[i][j].setBackground(new Color(90, 171, 71));
                }
                this.add(rooms[i][j]);

                if(this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.EMPTYROOM &&
                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.DOWN &&
                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.UP &&
                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.ENTRANCE &&
                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.EXIT)
                    rooms[i][j].setEnabled(false);

//                if(this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.EMPTYROOM &&
//                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.DOWN &&
//                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.UP &&
//                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.ENTRANCE &&
//                        this.game.getMap().getPlayerLocation(game.getPlayer()).getEvent1() != RoomEvent.EXIT)
//                    rooms[i][j].setEnabled(false);

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
                game.getMap().getPlayerLocation(game.getPlayer()).setVisited(true);
                game.setLocationChanged(true);
                notifyObservers();
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
            observers.get(i).update(this.game);
        }
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
