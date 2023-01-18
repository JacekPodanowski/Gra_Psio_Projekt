package GUI.Panels;

import BackEnd.Game.Event.Fight;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ConsolePanel extends JTextArea implements IConsolePanel {

    public ConsolePanel(){
        this.setEditable(false);
        this.setVisible(true);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);
    }

    @Override
    public void setMessage(String message) {
        this.setText(this.getText() + message);
    }

    @Override
    public void newLine() {
        this.setText(this.getText() + '\n');
    }

    public JTextArea getConsole() {
        return this;
    }
}
