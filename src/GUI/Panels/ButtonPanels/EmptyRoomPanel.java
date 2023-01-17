package GUI.Panels.ButtonPanels;

import BackEnd.Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmptyRoomPanel extends JPanel {
    private Game game;
    public EmptyRoomPanel(Game game){
        this.game = game;
        Dimension d = new Dimension(900, 300);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
    }
    public JButton nextRoomButton(){
        JButton nextRoomButton = setBasicButton("Przejdź do następnego pokoju");
        nextRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return nextRoomButton;
    }
    public JButton restButton(){
        JButton restButton = setBasicButton("Odpocznij");
        restButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return restButton;
    }
    public JButton useItemButton(){
        JButton useItemButton = setBasicButton("Użyj przedmiotu");
        useItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return useItemButton;
    }
    private JButton setBasicButton(String title){
        JButton button = new JButton(title);
        button.setFont(new Font("czcionka", Font.BOLD|Font.ITALIC, 10));
        Dimension d = new Dimension(100, 30);
        button.setMinimumSize(d);
        button.setMaximumSize(d);
        button.setPreferredSize(d);
        return button;
    }
}
