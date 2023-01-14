package GUI.Panels;

import BackEnd.Game.Event.Fight;

import javax.swing.*;

public class ConsolePanel extends JPanel implements IConsolePanel {
    private JTextArea console = new JTextArea();
    private Fight figth;
    public ConsolePanel(Fight figth){
        this.figth = figth;
        figth.setConsolePanel(this);
        JScrollPane jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(console);
        console.setColumns(20);
        console.setRows(5);
        console.setEditable(false);
    }

    @Override
    public void setMessage(String message) {
        console.setText(console.getText()+"\n"+message);
    }
}
