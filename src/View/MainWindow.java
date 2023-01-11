package View;


import Game.Game;
import Game.Map;
import Game.RoomType;
import Map.Window.Interface.*;
import Observable.Subject;
import Observers.Observer;
import SaveLoadStrategy.ISaveLoadStrategy;
import SaveLoadStrategy.LoadStrategy;
import SaveLoadStrategy.SaveStrategy;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JDialog implements Observer, Subject {
    JButton[][] rooms;
    JButton saveButton;
    JButton loadButton;
    JButton startGameButton;
    IMapWindowStrategy strategy;
    ISaveLoadStrategy saveLoadStrategy;
    ArrayList<Observer> observers = new ArrayList<Observer>();
    boolean ifNewGame = false;
    Game game;

    public MainWindow(Game game) {
        this.game = game;
        this.setSize(100, 100);

        JPanel mainPanel = new JPanel();
        GridLayout mainGridLayout =  new GridLayout(1,2);
        mainPanel.setLayout(mainGridLayout);

        mainPanel.add(createOptionsPanel());
        mainPanel.add(createGamePanel());

        setContentPane(mainPanel);
    }

    private JPanel createOptionsPanel()
    {
        JPanel optionsPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2,1);
        optionsPanel.setLayout(gridLayout);
        saveButton = new JButton("Zapisz") ;
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLoadWindow saveWindow = new SaveLoadWindow(game, new LoadStrategy());
                saveWindow.setModal(true);
                saveWindow.setAlwaysOnTop(true);
                saveWindow.setVisible(true);
                if(saveWindow.isFinishedSucceslyffly()) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
        optionsPanel.add(saveButton);

        loadButton = new JButton("Wczytaj");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLoadWindow loadWindow = new SaveLoadWindow(game, new LoadStrategy());
                loadWindow.setModal(true);
                loadWindow.setAlwaysOnTop(true);
                loadWindow.setVisible(true);
                if(loadWindow.isFinishedSucceslyffly()) {
                    game = loadWindow.getGame();
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
        optionsPanel.add(loadButton);
        startGameButton = new JButton("Zacznij Grę");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
                ifNewGame = true;
            }
        });
        return optionsPanel;
    }

    private JPanel createGamePanel()
    {
        JPanel gamePanel = new JPanel();
        if(game == null) {
            return gamePanel;
        }

        Map map = game.getMap();


        GridLayout gridLayout = new GridLayout(map.getTabOfRoom().length, map.getTabOfRoom().length);
        gamePanel.setLayout(gridLayout);

        for (int i = 0; i < map.getTabOfRoom().length; i++) {
            for (int j = 0; j < map.getTabOfRoom().length; j++) {
                switch (game.getMap().getRoomTypes()[i][j]) {
                    case empty:
                        strategy = new ButtonEmpty();
                        strategy.createButton(rooms[i][j]);
                        break;
                    case visited:
                        strategy = new ButtonVisited();
                        strategy.createButton(rooms[i][j]);
                        break;
                    case withPlayer:
                        strategy = new ButtonWithPlayer();
                        strategy.createButton(rooms[i][j]);
                        break;
                    case hidden:
                        strategy = new ButtonHidden();
                        strategy.createButton(rooms[i][j]);
                        break;
                }
                if(map.getTabOfRoom()[i][j].isAvailable()) {
                    rooms[j][j].setEnabled(true);
                }
                else
                    rooms[i][j].setEnabled(false);
                gamePanel.add(rooms[i][j]);
            }
        }
        return gamePanel;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean ifNewGame() {
        return ifNewGame;
    }
}
