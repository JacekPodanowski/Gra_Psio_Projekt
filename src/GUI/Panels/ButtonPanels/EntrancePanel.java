package GUI.Panels.ButtonPanels;

import BackEnd.Game.Game;
import GUI.View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EntrancePanel extends JPanel {
    public EntrancePanel(Game game){
        Dimension d = new Dimension(900, 300);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //this.setBackground(Color.BLACK);
        JLabel entranceText = new JLabel("Budzisz się w dziwnym, pustym sześciennym pokoju");
        JLabel entranceText2 = new JLabel("Jedyne co się wyróżnia to drzwi w ścianie");
        JLabel entranceHint = new JLabel("Na mapie wybierz pokój, do którego chcesz przejść");
        entranceText.setFont(new Font("Verdana",Font.BOLD,25));
        entranceText2.setFont(new Font("Verdana",Font.BOLD,20));
        entranceHint.setFont(new Font("Verdana",Font.ITALIC,17));
        JPanel panelText = new JPanel();
        JPanel panelText2 = new JPanel();
        JPanel panelHint = new JPanel();
        panelText.setSize(500, 50);
        panelText2.setSize(500, 50);
        panelHint.setSize(500, 50);
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.LINE_AXIS));
        panelText2.setLayout(new BoxLayout(panelText2, BoxLayout.LINE_AXIS));
        panelHint.setLayout(new BoxLayout(panelHint, BoxLayout.LINE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(panelText);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(panelText2);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(panelHint);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        panelText.add(entranceText);
        panelText2.add(entranceText2);
        panelHint.add(entranceHint);
    }
}
