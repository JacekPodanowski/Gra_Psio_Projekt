package Map.Window.Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ButtonEmpty implements IMapWindowStrategy {


    @Override
    public JButton createButton() {
        JButton room = new JButton();
//        try {
//            room.setIcon(new ImageIcon(ImageIO.read(new File("empty.png"))));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        return room;
    }
}
