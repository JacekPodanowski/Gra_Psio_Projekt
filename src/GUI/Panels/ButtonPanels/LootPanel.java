package GUI.Panels.ButtonPanels;

import BackEnd.Game.Event.Loot;
import BackEnd.Game.Event.RoomEvent;
import BackEnd.Game.Game;
import GUI.View.MainWindow;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LootPanel extends JPanel implements Subject {

    private Game game;
    private JButton bierzLootButton;
    private JButton nieBierzLootButton;
    private JLabel itemName;
    private JLabel staty;
    private JPanel butonpanel;
    private ArrayList<Observer> observers = new ArrayList<>();


    public LootPanel(Game game){
        this.game = game;
        this.add(initiateComponents());
    }
    public JPanel initiateComponents(){
        JPanel lootPanel = new JPanel();
        lootPanel.setMinimumSize(new Dimension(900, 500));
        lootPanel.setMaximumSize(new Dimension(900, 500));
        lootPanel.setPreferredSize(new Dimension(900, 500));
        lootPanel.setLayout(new FlowLayout());

        JLabel w = new JLabel("Natknałeś sie na bezpański przedmiot :" );
        w.setFont(new Font("ButtonFont", Font.BOLD, 15));
        itemName = setTitle();
        staty = staty();
        lootPanel.add(w);
        lootPanel.add(Box.createRigidArea(new Dimension(1000, 15)));
        lootPanel.add(itemName);
        lootPanel.add(Box.createRigidArea(new Dimension(1000, 1)));
        lootPanel.add(staty);
        lootPanel.add(Box.createRigidArea(new Dimension(1000, 25)));

        JLabel z=new JLabel("DECYDUJ" );
        z.setFont(new Font("ButtonFont", Font.BOLD, 20));
        lootPanel.add(z);
        lootPanel.add(Box.createRigidArea(new Dimension(1000, 1)));

        butonpanel=createbuttonpanel();
        //Dodaje komponenty
        lootPanel.add(butonpanel);
        return lootPanel;
    }

    public JPanel createbuttonpanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setSize(700,50);
        bierzLootButton = bierzLootButton(buttonPanel);
        buttonPanel.add(bierzLootButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        nieBierzLootButton = nieBierzLootButton(buttonPanel);
        buttonPanel.add(nieBierzLootButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        return buttonPanel;
    }
    public JLabel setTitle(){
        JLabel entranceText = new JLabel( game.getMap().getPlayerLocation(game.getPlayer()).getLootTab().get(0).shortName());
        entranceText.setFont(new Font("ButtonFont", Font.BOLD, 30));
        return entranceText;
    }

    public JLabel staty(){
        JLabel entranceText = new JLabel( game.getMap().getPlayerLocation(game.getPlayer()).getLootTab().get(0).stats());
        entranceText.setFont(new Font("Veradana",Font.BOLD,25));
        return entranceText;
    }

    private JButton bierzLootButton(Component parent){
        JButton bierzLootButton = new JButton();
        bierzLootButton.setMinimumSize(new Dimension(350, 50));
        bierzLootButton.setPreferredSize(new Dimension(350, 50));
        bierzLootButton.setMaximumSize(new Dimension(350, 50));
        bierzLootButton.setLocation(MainWindow.centerLocation(parent,bierzLootButton));
        bierzLootButton.setText("Weź przedmiot");
        bierzLootButton.setFont(new Font("Veradana",Font.BOLD,25));
        
        bierzLootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLootButtonDisabled();
                game.setUserWantToAddItem(true);
                if(!game.getPlayer().isEqFull())
                    game.getPlayer().pickUpItem(game.getMap().getPlayerLocation(game.getPlayer()).getItem());
                else {
//                    game.getMap().getPlayerLocation(game.getPlayer()).getItem();
                        game.getMap().getPlayerLocation(game.getPlayer()).setEvent1(RoomEvent.FULLEQ);
                        notifyObservers();
                    }
                notifyObservers();
            }});
        return bierzLootButton;
    }

    private JButton nieBierzLootButton(Component parent){
        JButton nieBierzLootButton = new JButton();

        nieBierzLootButton.setMinimumSize(new Dimension(350, 50));
        nieBierzLootButton.setPreferredSize(new Dimension(350, 50));
        nieBierzLootButton.setMaximumSize(new Dimension(350, 50));

        nieBierzLootButton.setLocation(MainWindow.centerLocation(parent,nieBierzLootButton));
        nieBierzLootButton.setText("Zostaw przedmiot");
        nieBierzLootButton.setFont(new Font("Veradana",Font.BOLD,25));
        nieBierzLootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLootButtonDisabled();
                game.setUserWantToAddItem(false);
                game.getMap().getPlayerLocation(game.getPlayer()).getItem();
                notifyObservers();
            }});
        return nieBierzLootButton;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void registerObserver(Observer observer) {observers.add(observer);}

    @Override
    public void removeObserver(Observer observer) {observers.remove(observer);}
    
    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(this.getGame());
    }

    public Game getGame() {
        return game;
    }

    public void setLootButtonDisabled(){
        bierzLootButton.setEnabled(false);
        nieBierzLootButton.setEnabled(false);
    }

    public void setLootButtonActive(){
        bierzLootButton.setEnabled(true);
        nieBierzLootButton.setEnabled(true);
    }
    public void setItemName(JLabel itemName) {
        this.itemName = itemName;
    }
    public JPanel getButonpanel() {
        return butonpanel;
    }
    public void setStaty(JLabel staty) {
        this.staty = staty;
    }

}
