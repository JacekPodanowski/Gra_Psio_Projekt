
import BackEnd.Game.Game;

import GUI.View.MainWindow;
import Observers.GUIRefresher;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.TRUETYPE_FONT;


public class Main {
    public static void main (String[] args){


        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();;
        try {
            ge.registerFont(Font.createFont(TRUETYPE_FONT, new File("MedievalSharp-Regular.ttf")));
        } catch (IOException | FontFormatException e) {
            //Handle exception
        }
        for(int i = 0; i < ge.getAvailableFontFamilyNames().length; i++){
            System.out.println(i + ge.getAvailableFontFamilyNames()[i]);
        }

        System.out.println(ge.getAvailableFontFamilyNames()[119]);

        new GUIRefresher(new MainWindow());
    }
}
