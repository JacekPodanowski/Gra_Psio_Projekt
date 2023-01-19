package GUI.Panels;

import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import GUI.Panels.ButtonPanels.ProfessionChoosePanel;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private TopPanel topPanel;
    private BottomPanel bottomPanel;
    private WindowStates state;
    private Game game;
    private StartGamePanel startGamePanel;
    private ProfessionChoosePanel professionChoosePanel;
    public MainPanel(Game game, WindowStates state){
        this.state = state;
        this.game = game;
        this.setPreferredSize(new Dimension(900, 700));
        this.setLayout(new GridLayout(2, 1));
        switch(state) {
            case STARTMENU:
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                this.add(Box.createRigidArea(new Dimension(0, 50)));
                this.add(createTitleLabel("Witaj w grze: \"Ucieczka z kostki\"", 30));
                this.add(Box.createRigidArea(new Dimension(0, 50)));
                startGamePanel = new StartGamePanel(game);
                this.add(startGamePanel);
//                Thread t1 = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while(true){
//                            if(startGamePanel.isCzyNowaGra()){
//                                state = WindowStates.GAMESTART;
//                                break;
//                            }
//                            if(startGamePanel.isCzyWczytanaGra()){
//                                state = WindowStates.GAME;
//                                break;
//                            }
//                        }
//                    }
//                });
//                t1.start();
                break;
            case GAMESTART:
                professionChoosePanel = new ProfessionChoosePanel(game);
                this.add(professionChoosePanel);
                break;
            case GAME:
                topPanel = new TopPanel(game);
                bottomPanel = new BottomPanel(game);
                this.add(topPanel);
                this.add(bottomPanel);
                break;
            case ENDGAME:
                break;
        }
    }
    public JLabel createTitleLabel(String text, int size){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(900, 100));
        label.setFont(new Font("LabelFont", Font.BOLD|Font.ITALIC, size));
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setBackground(Color.BLACK);
        return label;
    }


    public TopPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(TopPanel topPanel) {
        this.topPanel = topPanel;
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(BottomPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public ProfessionChoosePanel getProfessionChoosePanel() {
        return professionChoosePanel;
    }

    public void setProfessionChoosePanel(ProfessionChoosePanel professionChoosePanel) {
        this.professionChoosePanel = professionChoosePanel;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public WindowStates getState() {
        return state;
    }

    public void setState(WindowStates state) {
        this.state = state;
    }

    public StartGamePanel getStartGamePanel() {
        return startGamePanel;
    }

    public void setStartGamePanel(StartGamePanel startGamePanel) {
        this.startGamePanel = startGamePanel;
    }
}
