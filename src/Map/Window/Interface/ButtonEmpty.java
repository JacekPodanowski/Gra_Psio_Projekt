package Map.Window.Interface;

import Game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ButtonEmpty implements IMapWindowStrategy {


    @Override
    public JButton createButton(Game game, int i, int j) {
        JButton room = new JButton();
//        try {
//            room.setIcon(new ImageIcon(ImageIO.read(new File("empty.png"))));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().setPlayerLocation(game.getPlayer(), game.getMap().getTabOfRoom()[i][j]);
                game.notifyObservers();
            }
        });
        return room;
    }
}
