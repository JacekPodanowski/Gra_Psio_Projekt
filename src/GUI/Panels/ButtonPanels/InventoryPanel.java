package GUI.Panels.ButtonPanels;

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

public class InventoryPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public InventoryPanel(Game game){
        this.game = game;
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));
        setBackground(new Color(199, 196, 181));
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);

        this.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.setPreferredSize(new Dimension(800,240));
        panel.setMaximumSize(new Dimension(800,240));
        panel.setMinimumSize(new Dimension(800,240));
        panel.setBorder(blackLine);

        JPanel equipment = new JPanel();
        equipment.setLayout(new BoxLayout(equipment, BoxLayout.PAGE_AXIS));

        JLabel ooo= new JLabel("Twoja aktualna broń: ");
        ooo.setFont(new Font("Verdana",Font.BOLD,20));
        equipment.add(ooo);

        equipment.add(currentWeapon());
        equipment.add(currentWeaponStats());

        equipment.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel aaa= new JLabel("Twoja aktualna zbroja: ");
        aaa.setFont(new Font("Verdana",Font.BOLD,20));
        equipment.add(aaa);

        equipment.add(currentArmor());
        equipment.add(currentArmorStats());

        equipment.add(Box.createRigidArea(new Dimension(0, 120)));

        panel.add(equipment);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(inventoryPieces());
        this.add(panel);


        //this.add(goBack());
    }

    public JLabel currentWeapon(){
        JLabel currentWeapon = new JLabel("--> "+game.getPlayer().getWeapon().shortName());
        currentWeapon.setFont(new Font("Verdana",Font.PLAIN,17));
        return currentWeapon;
    }
    public JLabel currentWeaponStats(){
        JLabel currentWeapon = new JLabel(game.getPlayer().getWeapon().stats());
        currentWeapon.setFont(new Font("Verdana",Font.ITALIC,12));
        return currentWeapon;
    }
    public JLabel currentArmor(){
        JLabel currentArmor = new JLabel("-->"+game.getPlayer().getArmor().shortName());
        currentArmor.setFont(new Font("Verdana",Font.PLAIN,17));
        return currentArmor;
    }
    public JLabel currentArmorStats(){
        JLabel currentArmor = new JLabel(game.getPlayer().getArmor().stats());
        currentArmor.setFont(new Font("Verdana",Font.ITALIC,12));
        return currentArmor;
    }
    public JPanel inventoryPieces(){
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
                button.setBackground(new Color(136, 93, 44));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().useItem(z);
                        notifyObservers();
                    }
                });
                button.setSize(80, 20);
                inventoryPieces.add(button);
            }
            inventoryPieces.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        inventoryPieces.add(goBack());
        inventoryPieces.add(Box.createRigidArea(new Dimension(0, 120)));
        return inventoryPieces;
    }

    private JButton goBack(){
//        JPanel panelButton = new JPanel();
//        panelButton.setPreferredSize(new Dimension(105,240));
//        panelButton.setMaximumSize(new Dimension(105,240));
//        panelButton.setMinimumSize(new Dimension(105,240));
//        panelButton.setOpaque(false);

        JButton button = new JButton("   Powrót   ");
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
