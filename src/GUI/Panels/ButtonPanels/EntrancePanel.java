package GUI.Panels.ButtonPanels;

import BackEnd.Game.Game;

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

        JLabel entranceText = new JLabel("Budzisz się w dziwnym, pustym sześciennym pokoju. Nie jesteś pewien co się wydarzyło, jedyne co widzisz to drzwi w ścianie.");
        JLabel entranceHint = new JLabel("Na mapie wybierz pokój, do którego chcesz przejść.");
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(entranceText);
        this.add(entranceHint);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private JButton roomChooseButton(int[] path){
        JButton roomChooseButton = new JButton("" + path[0] + ", " + path[1]);
        Dimension d = new Dimension(100, 40);
        roomChooseButton.setMinimumSize(d);
        roomChooseButton.setMaximumSize(d);
        roomChooseButton.setPreferredSize(d);
        roomChooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return roomChooseButton;
    }
}
