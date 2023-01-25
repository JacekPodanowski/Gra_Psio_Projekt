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

        JLabel z=new JLabel("BRAK MIEJSCA W EKWIPUNKU" );
        z.setFont(new Font("ButtonFont", Font.BOLD, 20));
        z.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(z);

        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(900, 202));
        panel.setMaximumSize(new Dimension(900, 202));
        panel.setPreferredSize(new Dimension(900, 202));
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        JPanel panelItem = new JPanel();
        panelItem.add(Box.createRigidArea(new Dimension(50, 0)));
        panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.PAGE_AXIS));
        panelItem.setMinimumSize(new Dimension(700, 200));
        panelItem.setMaximumSize(new Dimension(700, 200));
        panelItem.setPreferredSize(new Dimension(700, 200));
        panelItem.setBorder(blackLine);

        Item item = game.getMap().getPlayerLocation(game.getPlayer()).getItem();
        String itemStats = item.toString();
        panelItem.add(new JLabel("Znaleziony przedmiot to: "+itemStats));
        panelItem.add(Box.createRigidArea(new Dimension(0, 5)));
        panelItem.add(inventoryPieces(item));

        panel.add(panelItem);
        panel.add(goBack());
        this.add(panel);
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
        inventoryPieces.setMinimumSize(new Dimension(700, 200));
        inventoryPieces.setMaximumSize(new Dimension(700, 200));
        inventoryPieces.setPreferredSize(new Dimension(700, 200));
        inventoryPieces.setLayout(new BoxLayout(inventoryPieces, BoxLayout.PAGE_AXIS));
        inventoryPieces.add(new JLabel("Wymień jakiś przedmiot :"));
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
                button.setBackground(new Color(136, 93, 44));
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
            inventoryPieces.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        //inventoryPieces.add(Box.createRigidArea(new Dimension(0, 120)));
        return inventoryPieces;
    }

    private JPanel goBack(){
        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(105,202));
        panelButton.setMaximumSize(new Dimension(105,202));
        panelButton.setMinimumSize(new Dimension(105,202));
        panelButton.setOpaque(false);

        JButton button = new JButton("Zostaw");
        button.setFont(new Font("", Font.BOLD, 15));
        button.setBackground(new Color(136, 93, 44));
        button.setPreferredSize(new Dimension(100,60));
        //button.setAlignmentX(Component.BOTTOM_ALIGNMENT);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.EMPTYROOM);
                notifyObservers();
            }
        });
        panelButton.add(Box.createRigidArea(new Dimension(80,130)));
        panelButton.add(button);
        return panelButton;
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
