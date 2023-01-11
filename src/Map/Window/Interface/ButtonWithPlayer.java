package Map.Window.Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ButtonWithPlayer implements IMapWindowStrategy{
    @Override
    public JButton createButton() {
        JButton room = new JButton();
//        try {
//            room.setIcon(new ImageIcon(ImageIO.read(new File("withPlayer.png"))));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return room;
    }
}
