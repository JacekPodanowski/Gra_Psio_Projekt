package Map.Window.Interface;

import Game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ButtonWithPlayer implements IMapWindowStrategy{
    @Override
    public JButton createButton(Game game, int i, int j) {
        JButton room = new JButton();
//        try {
//            room.setIcon(new ImageIcon(ImageIO.read(new File("withPlayer.png"))));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return room;
    }
}
