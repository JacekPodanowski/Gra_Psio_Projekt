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
    private JPanel topPanel;
    private JPanel bottomPanel;
    private WindowStates state;
    private Game game;
    private StartGamePanel startGamePanel;
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
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                this.add(new Label("Aby rozpocząc grę, wybierz swoją profesję: ", Label.CENTER));
                this.add(Box.createRigidArea(new Dimension(0, 100)));
                this.add(new ProfessionChoosePanel(game));
                break;
            case GAME:
                this.add(new TopPanel(game));
                this.add(new BottomPanel(game));
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


    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
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
