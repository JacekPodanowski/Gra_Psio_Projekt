package GUI.Panels;

import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import GUI.SaveLoadStrategy.LoadStrategy;
import GUI.View.MainWindow;
import GUI.View.SaveLoadWindow;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGamePanel extends JPanel {
    private Game game;
    private Observer observer;

    public StartGamePanel(Game game, Observer observer){
        this.game = game;
        this.observer = observer;
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(newGameButton(this));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(loadGameButton(this));
    }


    private JButton newGameButton(Component parent){
        JButton newGameButton = new JButton();
        newGameButton.setMinimumSize(new Dimension(700, 50));
        newGameButton.setPreferredSize(new Dimension(700, 50));
        newGameButton.setMaximumSize(new Dimension(700, 50));
        newGameButton.setLocation(MainWindow.centerLocation(parent, newGameButton));
        newGameButton.setText("Rozpocznij nową grę");
        newGameButton.setFont(new Font("ButtonFont", Font.BOLD, 30));
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game(observer);
            }});
        return newGameButton;
    }
    private JButton loadGameButton(Component parent){
        JButton loadGameButton = new JButton();
        loadGameButton.setMinimumSize(new Dimension(700, 50));
        loadGameButton.setPreferredSize(new Dimension(700, 50));
        loadGameButton.setMaximumSize(new Dimension(700, 50));
        loadGameButton.setLocation(MainWindow.centerLocation(parent, loadGameButton));
        loadGameButton.setText("Wczytaj grę");
        loadGameButton.setFont(new Font("ButtonFont", Font.BOLD, 30));
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLoadWindow loadWindow = new SaveLoadWindow(game, new LoadStrategy());
                loadWindow.setModal(true);
                loadWindow.setAlwaysOnTop(true);
                loadWindow.setVisible(true);
                if (loadWindow.isFinishedSucceslyffly()) {
                    game = loadWindow.getGame();
                    loadWindow.setVisible(false);
                    loadWindow.setEnabled(false);
                } else {
                    //CO MA ZROBIC? nie wiem jak to będzie ziom
                }
            }});
        return loadGameButton;
    }
}
