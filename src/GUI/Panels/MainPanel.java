package GUI.Panels;

import BackEnd.Game.Game;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(Game game){
        this.setPreferredSize(new Dimension(900, 700));
        this.setLayout(new GridLayout(2, 1));

        this.add(new TopPanel(game));
        this.add(new BottomPanel(game));
    }
}
