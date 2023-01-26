package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Item.Item;
import BackEnd.Game.RoomEvent;
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
//        this.setBorder(blackLine);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel z=new JLabel("BRAK MIEJSCA W EKWIPUNKU" );
        z.setFont(new Font("ButtonFont", Font.BOLD, 20));
        z.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(z);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setPreferredSize(new Dimension(800,240));
        panel.setMaximumSize(new Dimension(800,240));
        panel.setMinimumSize(new Dimension(800,240));
        panel.setBorder(blackLine);

        JPanel equipment = new JPanel();
        equipment.setLayout(new BoxLayout(equipment, BoxLayout.PAGE_AXIS));

        Item item = game.getMap().getPlayerLocation(game.getPlayer()).getItem();
        String itemName = item.shortName();
        String itemStats = item.stats();

        JLabel ooo= new JLabel("Znaleziony przedmiot to : ");
        ooo.setFont(new Font("Verdana",Font.BOLD,15));
        equipment.add(ooo);

        equipment.add(Label("--> "+itemName));
        equipment.add(Label(itemStats));

        equipment.add(Box.createRigidArea(new Dimension(0, 120)));

        panel.add(equipment);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(inventoryPieces(item));
        this.add(panel);


    }

    public JLabel Label(String a){
        JLabel currentWeapon = new JLabel(a);
        currentWeapon.setFont(new Font("Verdana",Font.PLAIN,15));
        return currentWeapon;
    }

    public JPanel inventoryPieces(Item item){
        JPanel inventoryPieces = new JPanel();
        inventoryPieces.setMinimumSize(new Dimension(700, 200));
        inventoryPieces.setMaximumSize(new Dimension(700, 200));
        inventoryPieces.setPreferredSize(new Dimension(700, 200));
        inventoryPieces.setLayout(new BoxLayout(inventoryPieces, BoxLayout.PAGE_AXIS));
        JLabel ooo= new JLabel("Wymień jakiś przedmiot :");
        ooo.setFont(new Font("Verdana",Font.BOLD,15));
        inventoryPieces.add(ooo);

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
        inventoryPieces.add(goBack());
        //inventoryPieces.add(Box.createRigidArea(new Dimension(0, 120)));
        return inventoryPieces;
    }

    private JButton goBack(){
//        JPanel panelButton = new JPanel();
//        panelButton.setPreferredSize(new Dimension(105,240));
//        panelButton.setMaximumSize(new Dimension(105,240));
//        panelButton.setMinimumSize(new Dimension(105,240));
//        panelButton.setOpaque(false);

        JButton button = new JButton("   Zostaw   ");
        button.setFont(new Font("", Font.BOLD, 15));
        button.setBackground(new Color(136, 93, 44));
        button.setPreferredSize(new Dimension(125,20));
        //button.setAlignmentX(Component.BOTTOM_ALIGNMENT);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.EMPTYROOM);
                notifyObservers();
            }
        });
//        panelButton.add(Box.createRigidArea(new Dimension(80,170)));
//        panelButton.add(button);
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
