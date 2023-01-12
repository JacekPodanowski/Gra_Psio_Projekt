package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Profession.Archer;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfessionChoosePanel extends JPanel {
    private JButton warrior;
    private JButton mage;
    private JButton archer;


    public ProfessionChoosePanel(Game game){
        this.setLayout(new GridLayout(1, 3));
        initiateWarrior(game);
        initiateMage(game);
        initiateArcher(game);
        this.add(warrior);
        this.add(mage);
        this.add(archer);
    }
    private void initiateWarrior(Game game){
        warrior = new JButton();
        warrior.setText("Wojownik");
        warrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Warrior());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
            }
        });
    }
    private void initiateMage(Game game){
        mage = new JButton();
        mage.setText("Mag");
        mage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Mage());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
            }
        });
    }
    private void initiateArcher(Game game){
        archer = new JButton();
        archer.setText("Łucznik");
        archer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Archer());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
            }
        });
    }
}
