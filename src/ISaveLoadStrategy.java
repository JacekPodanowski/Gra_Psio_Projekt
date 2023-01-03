import Game.Game;

import javax.swing.*;

public interface ISaveLoadStrategy {
    JButton createButton(int index);
    Game actionAfterClick(int index, Game game);
    String getTitle();
    void setSaver(SaveLoadHelper helper);
}
