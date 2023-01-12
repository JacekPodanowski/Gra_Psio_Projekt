package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(){
//        mainPanel.setLayout(new GridLayout(2, 1));
//        mainPanel.setPreferredSize(d);
        this.add(new TopPanel());
        this.add(new BottomPanel());
    }
}
