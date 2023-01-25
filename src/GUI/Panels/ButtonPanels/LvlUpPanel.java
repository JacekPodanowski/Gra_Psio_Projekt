package GUI.Panels.ButtonPanels;

import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LvlUpPanel extends JPanel implements Subject {
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();
    public LvlUpPanel(Game game){
        this.game = game;
        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));
        setBackground(new Color(199, 196, 181));
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        //this.setBorder(blackLine);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //this.setBackground(Color.BLACK);
        JLabel lvlup1 = new JLabel("Pokonałeś przeciwnika !");
        JLabel lvlup2 = new JLabel("Ta walka wiele cię nauczyła ");
        JLabel lvlup3 = new JLabel("Wybierz atrybut, który chcesz rozwinąć ");
        lvlup1.setFont(new Font("Verdana",Font.BOLD,30));
        lvlup2.setFont(new Font("Verdana",Font.ITALIC,17));
        lvlup3.setFont(new Font("Verdana",Font.ITALIC,17));

        JPanel panelText = new JPanel();
        JPanel panelText2 = new JPanel();
        JPanel panelText3 = new JPanel();
        panelText.setOpaque(false);
        panelText2.setOpaque(false);
        panelText3.setOpaque(false);
        panelText.setSize(500, 50);
        panelText2.setSize(500, 50);
        panelText3.setSize(500, 50);
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.LINE_AXIS));
        panelText2.setLayout(new BoxLayout(panelText2, BoxLayout.LINE_AXIS));
        panelText3.setLayout(new BoxLayout(panelText3, BoxLayout.LINE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 1)));
        this.add(panelText);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(panelText2);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(panelText3);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        panelText.add(lvlup1);
        panelText2.add(lvlup2);
        panelText3.add(lvlup3);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(500,100);
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));

        //Dodaje komponenty

        buttonsPanel.add(addStat('S'));
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonsPanel.add(addStat('I'));
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonsPanel.add(addStat('A'));

        this.add(Box.createRigidArea(new Dimension(500,60)));
        this.add(buttonsPanel);
    }

    private JButton addStat(char stat){
        JButton button = new JButton();
        switch (stat){
            case 'A':
                button.setText(" Dodaj zręczność ");
                button.setFont(new Font("", Font.BOLD, 20));
                button.setBackground(new Color(136, 93, 44));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().setAgility(game.getPlayer().getAgility()+5);
                        game.getMap().getPlayerLocation(game.getPlayer()).generateLoot();
                        notifyObservers();
                    }
                });
                break;
            case 'I':
                button.setText("Dodaj inteligencję");
                button.setFont(new Font("", Font.BOLD, 20));
                button.setBackground(new Color(136, 93, 44));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().setIntelligence(game.getPlayer().getIntelligence()+5);
                        game.getMap().getPlayerLocation(game.getPlayer()).generateLoot();
                        notifyObservers();
                    }
                });
                break;
            case 'S':
                button.setText("    Dodaj siłę    ");
                button.setFont(new Font("", Font.BOLD, 20));
                button.setBackground(new Color(136, 93, 44));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        game.getPlayer().setStrength(game.getPlayer().getStrength()+5);
                        game.getMap().getPlayerLocation(game.getPlayer()).generateLoot();
                        notifyObservers();
                    }
                });
                break;
        }
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
