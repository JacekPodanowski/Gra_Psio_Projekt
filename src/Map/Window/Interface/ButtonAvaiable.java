package Map.Window.Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ButtonAvaiable implements IMapWindowStrategy{
    @Override
    public JButton createButton(JButton room) {
        room = new JButton();

        try {
            room.setIcon(new ImageIcon(ImageIO.read(new File("avaiable.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return room;
    }
}
