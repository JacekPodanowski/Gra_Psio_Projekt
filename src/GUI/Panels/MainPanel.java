package GUI.Panels;

import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import GUI.Panels.ButtonPanels.ProfessionChoosePanel;
import GUI.View.MainWindow;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;
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

    private EndGamePanel endGamePanel;


    public MainPanel(Game game, WindowStates state) {
        this.state = state;
        this.game = game;
        this.setPreferredSize(new Dimension(900, 700));
        switch (state) {
            case STARTMENU:
                this.setLayout(new GridLayout(1, 1));
                startGamePanel = new StartGamePanel(game);
                this.add(startGamePanel);
                //this.add(new JLabel(new ImageIcon(this.getClass().getResource("/escapethecube.png"))));
                break;
            case GAMESTART:

                this.setLayout(new GridLayout(1, 1));
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
                this.setBackground(new Color(11, 128, 26));
                Label title = new Label("Aby rozpocząc grę, wybierz swoją profesję: ", Label.CENTER);
                title.setFont(new Font("Veradana", Font.BOLD, 30));
                this.add(title);
                professionChoosePanel = new ProfessionChoosePanel(game);
                this.add(professionChoosePanel);
                break;
            case GAME:
                this.setLayout(new GridLayout(2, 1));
                topPanel = new TopPanel(game);
                bottomPanel = new BottomPanel(game);
                this.add(topPanel);
                this.add(bottomPanel);
                break;
            case ENDGAME:
                endGamePanel = new EndGamePanel();
                this.add(endGamePanel);
                break;
        }
    }

    public JLabel createTitleLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(900, 100));
        label.setFont(new Font("LabelFont", Font.BOLD | Font.ITALIC, size));
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

    public EndGamePanel getEndGamePanel() {
        return endGamePanel;
    }

    public void setEndGamePanel(EndGamePanel endGamePanel) {
        this.endGamePanel = endGamePanel;
    }
}
