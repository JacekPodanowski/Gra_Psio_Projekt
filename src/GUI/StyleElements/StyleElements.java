package GUI.StyleElements;

import java.awt.*;
import java.io.*;

import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.getFont;

public class StyleElements {
    public static Font gameFont(int size){
        Font fontStyle = null;
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/res/MedievalSharp-Regular.ttf"));
            fontStyle = Font.createFont(TRUETYPE_FONT, is);
            is.close();
        } catch (IOException | FontFormatException e) {
            System.out.println("font not loaded");
        }
        Font gameFont = new Font(fontStyle.getFontName(), Font.PLAIN, size);
        return gameFont;
    }
}
