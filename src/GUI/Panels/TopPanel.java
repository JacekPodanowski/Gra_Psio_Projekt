package GUI.Panels;

import BackEnd.Game.Game;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel(Game game){
        this.setPreferredSize(new Dimension(800, 400));
        this.setLayout(new GridLayout(1, 2));
        this.add(new GamePanel());
        this.add(new MapPanel(game));
    }
}
