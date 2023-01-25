package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Item.Item;
import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FullEqPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public FullEqPanel(Game game){
        this.game = game;
        this.setMinimumSize(new Dimension(900, 300));
        this.setMaximumSize(new Dimension(900, 300));
        this.setPreferredSize(new Dimension(900, 300));
        setBackground(new Color(199, 196, 181));
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50, 0)));

        JPanel foundItem = new JPanel();
        JLabel z=new JLabel("BRAK MIEJSCA W EKWIPUNKU" );
        z.setFont(new Font("ButtonFont", Font.BOLD, 20));
        this.add(z);
        foundItem.add(Box.createRigidArea(new Dimension(0, 120)));

        foundItem.add(new JLabel("Znaleziony przedmiot to: "));
        foundItem.add(Box.createRigidArea(new Dimension(0, 120)));

        Item item = game.getMap().getPlayerLocation(game.getPlayer()).getItem();
        JLabel itemStats = new JLabel(item.toString());
        foundItem.add(itemStats);
        foundItem.add(Box.createRigidArea(new Dimension(0, 120)));
        panel.add(foundItem);

        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(inventoryPieces(item));
        this.add(panel);
        this.add(goBack());
    }

    public JLabel currentWeapon(){
        JLabel currentWeapon = new JLabel(game.getPlayer().getWeapon().toString());
        return currentWeapon;
    }
    public JLabel currentArmor(){
        JLabel currentArmor = new JLabel(game.getPlayer().getArmor().toString());
        return currentArmor;
    }
    public JPanel inventoryPieces(Item item){
        JPanel inventoryPieces = new JPanel();
        inventoryPieces.setLayout(new BoxLayout(inventoryPieces, BoxLayout.PAGE_AXIS));
        inventoryPieces.add(new JLabel("Ekwipunek:"));
        for(int i = 0; i < game.getPlayer().getInventory().length; i++) {
            if (game.getPlayer().getInventory()[i] == null) {
                JButton button = new JButton(i + 1 + ". -------------------");
                button.setEnabled(false);
                button.setSize(80, 20);
                inventoryPieces.add(button);
            }
            else {
                final int z = i;
                JButton button = new JButton(i + 1 + ". " + game.getPlayer().getInventory()[i].toString());
                button.setEnabled(true);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().getInventory()[z]=item;
                        notifyObservers();
                    }
                });
                button.setSize(80, 20);
                inventoryPieces.add(button);
            }
            inventoryPieces.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        inventoryPieces.add(Box.createRigidArea(new Dimension(0, 120)));
        return inventoryPieces;
    }

    public JButton goBack(){
        JButton button = new JButton("Zostaw");
        button.setBackground(new Color(136, 93, 44));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.EMPTYROOM);
                notifyObservers();
            }
        });
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
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(game);
    }
}
