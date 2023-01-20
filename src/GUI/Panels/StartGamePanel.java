package GUI.Panels;

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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class StartGamePanel extends JPanel implements Subject {
    private Game game;
    private boolean czyNowaGra = false;
    private boolean czyWczytanaGra = false;
    private ArrayList<Observer> observers = new ArrayList<>();

    private ImageIcon pictureNameGame;
    private JLabel imageLabel;
    private Panel menuPanel;

    public StartGamePanel(Game game){
        this.game = game;

        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        //Layout
        this.setLayout(new GridLayout(2,1));

        pictureNameGame = new ImageIcon("images/NameOfGame.png");



        imageLabel = new JLabel(pictureNameGame);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(Color.BLACK);





        menuPanel = new Panel();
        menuPanel.setBackground(Color.BLACK);

        menuPanel.add(newGameButton(this));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(loadGameButton(this));

        this.add(imageLabel);
        this.add(menuPanel);
    }


    private JButton newGameButton(Component parent){
        JButton newGameButton = new JButton();
        newGameButton.setMinimumSize(new Dimension(700, 50));
        newGameButton.setPreferredSize(new Dimension(700, 50));
        newGameButton.setMaximumSize(new Dimension(700, 50));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //loadGameButton.setLocation(MainWindow.centerLocation(parent, loadGameButton));
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
                    czyWczytanaGra = true;
                    loadWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                } else {
                    loadWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }});
        return loadGameButton;
    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
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
            observers.get(i).update(this.getGame());
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
