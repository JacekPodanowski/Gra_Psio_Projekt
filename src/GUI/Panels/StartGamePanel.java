package GUI.Panels;

import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import GUI.SaveLoadStrategy.LoadStrategy;
import GUI.View.MainWindow;
import GUI.View.SaveLoadWindow;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartGamePanel extends JPanel implements Subject {
    private Game game;
    private boolean czyNowaGra = false;
    private boolean czyWczytanaGra = false;
    private ArrayList<Observer> observers = new ArrayList<>();

    public StartGamePanel(Game game){
        this.game = game;

        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        //Layout
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //Dodaje komponenty
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
                game = new Game();
                notifyObservers();
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
                    czyWczytanaGra = true;
                } else {
                    //CO MA ZROBIC? nie wiem jak to będzie ziom
                }
            }});
        return loadGameButton;
    }

    public boolean isCzyNowaGra() {
        return czyNowaGra;
    }

    public void setCzyNowaGra(boolean czyNowaGra) {
        this.czyNowaGra = czyNowaGra;
    }

    public boolean isCzyWczytanaGra() {
        return czyWczytanaGra;
    }

    public void setCzyWczytanaGra(boolean czyWczytanaGra) {
        this.czyWczytanaGra = czyWczytanaGra;
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
            observers.get(i).update(this);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
