package GUI.Panels;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel(){
        this.add(new TopPanel());
        this.add(new BottomPanel());
    }
}
