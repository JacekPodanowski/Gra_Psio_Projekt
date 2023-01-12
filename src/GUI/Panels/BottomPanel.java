package GUI.Panels;

import BackEnd.Game.Game;
import GUI.Panels.ButtonPanels.ProfessionChoosePanel;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    public BottomPanel(Game game){
        this.setPreferredSize(new Dimension(900, 300));
        this.setLayout(new FlowLayout());
        this.add(new ProfessionChoosePanel(game));
    }
}
