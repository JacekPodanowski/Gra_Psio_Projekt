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
        Dimension d = new Dimension(1300, 300);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel entranceText = new JLabel("Budzisz się w dziwnym, pustym sześciennym pokoju. Nie jesteś pewien co się wydarzyło, jedyne co widzisz to drzwi w ścianie.");
        JLabel entranceHint = new JLabel("Na mapie wybierz pokój, do którego chcesz przejść.");
        entranceText.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        entranceHint.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        entranceText.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        entranceHint.setFont(new Font("Times New Roman", Font.ITALIC, 25));

        JPanel panelText = new JPanel();
        JPanel panelHint = new JPanel();
        panelText.setSize(500, 50);
        panelHint.setSize(500, 50);
        panelText.setLayout(new BoxLayout(panelText, BoxLayout.LINE_AXIS));
        panelHint.setLayout(new BoxLayout(panelHint, BoxLayout.LINE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 120)));
        this.add(panelText);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(panelHint);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        panelText.add(entranceText);
        panelHint.add(entranceHint);


    }
}
