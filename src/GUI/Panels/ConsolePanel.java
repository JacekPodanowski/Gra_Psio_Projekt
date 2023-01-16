package GUI.Panels;

import BackEnd.Game.Event.Fight;

import javax.swing.*;
import java.awt.*;

public class ConsolePanel extends JTextArea implements IConsolePanel {

    private Fight figth;
    public ConsolePanel(Fight figth){
        this.figth = figth;
        figth.setConsolePanel(this);
        this.setEditable(false);
        this.setVisible(true);
    }

    @Override
    public void setMessage(String message) {
        this.setText(this.getText()+"\n"+message);
    }

    public JTextArea getConsole() {
        return this;
    }

    public Fight figth() {
        return figth;
    }
}
