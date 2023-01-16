package GUI.SaveLoadStrategy;

import BackEnd.Game.Game;
import BackEnd.Game.SaveLoadHelper;

import javax.swing.*;

public interface ISaveLoadStrategy {
    JButton createButton(int index);
    Game actionAfterClick(int index, Game game);
    String getTitle();
    void setSaver(SaveLoadHelper helper);
}
