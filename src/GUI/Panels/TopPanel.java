package GUI.Panels;

import BackEnd.Game.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TopPanel extends JPanel {
    private MapPanel mapPanel;
    private GamePanel gamePanel;
    private Game game;
    public TopPanel(Game game){
        this.game = game;
        this.setPreferredSize(new Dimension(800, 400));
        this.setMinimumSize(new Dimension(800, 400));
        this.setMaximumSize(new Dimension(800, 400));
        this.setLayout(new GridLayout(1, 2));
        gamePanel = new GamePanel(game);
        mapPanel = new MapPanel(game);
        this.add(gamePanel);
        this.add(mapPanel);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public void setMapPanel(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
