package GUI.Panels;

import BackEnd.Game.Game;
import GUI.SaveLoadStrategy.LoadStrategy;
import GUI.View.SaveLoadWindow;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class StartGamePanel extends JPanel implements Subject {
    private Game game;
    private boolean czyNowaGra = false;
    private boolean czyWczytanaGra = false;
    private ArrayList<Observer> observers = new ArrayList<>();

    private ImageIcon pictureNameGame;
    private ImageIcon pictureMenuSymbol;
    private JLabel titleImageLabel;

    private JLabel menuSymbolLabel;

    public StartGamePanel(Game game){
        this.game = game;

        //Ustawiam wielkości
        this.setMinimumSize(new Dimension(900, 500));
        this.setMaximumSize(new Dimension(900, 500));
        this.setPreferredSize(new Dimension(900, 500));

        //Layout
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        pictureNameGame = new ImageIcon("images/NameOfGame2.png");
        pictureMenuSymbol = new ImageIcon("images/MenuSymbol.png");

        titleImageLabel = new JLabel(pictureNameGame);
        titleImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuSymbolLabel = new JLabel(pictureMenuSymbol);
        menuSymbolLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setBackground(Color.BLACK);

        this.add(titleImageLabel);
        this.add(Box.createRigidArea(new Dimension(0,50)));
        this.add(newGameButton(this));
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(loadGameButton(this));
        this.add(Box.createRigidArea(new Dimension(0,70)));
        this.add(menuSymbolLabel);
        //this.add(new MyMenuSymbol());
    }


    private JButton newGameButton(Component parent){
        JButton newGameButton = new JButton();
        newGameButton.setMinimumSize(new Dimension(700, 50));
        newGameButton.setPreferredSize(new Dimension(700, 50));
        newGameButton.setMaximumSize(new Dimension(700, 50));

        newGameButton.setOpaque(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton.add(new MyTextNewGameButton());
        newGameButton.setFont(new Font("ButtonFont", Font.BOLD, 30));
        newGameButton.setFocusable(false);

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

        loadGameButton.add(new MyTextLoadButton());
        loadGameButton.setFont(new Font("ButtonFont", Font.BOLD, 30));


        loadGameButton.setFocusable(false);
        loadGameButton.setOpaque(false);
        //loadGameButton.setContentAreaFilled(false);
        loadGameButton.setBorderPainted(false);
        loadGameButton.setBackground(Color.BLACK);
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

    static class MyTextNewGameButton extends JComponent{
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(Color.getHSBColor(0,1,0.5f));
            g2.drawString("Rozpocznij nową grę", 170,30 );
        }
    }
    static class MyTextLoadButton extends JComponent{
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(Color.getHSBColor(0,1,0.5f));
            g2.drawString("Wczytaj grę", 240,30 );
        }
    }

//    static class MyMenuSymbol extends JComponent{
//        @Override
//        protected void paintComponent(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g;
////            g2.setPaint(Color.getHSBColor(0,1,0.5f));
//            Image menuSymbol = new ImageIcon("images/MenuSymbol.png").getImage();
//            g2.drawImage(menuSymbol, 375,0,null);
//
//        }
//    }

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