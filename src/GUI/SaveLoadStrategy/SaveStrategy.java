package GUI.SaveLoadStrategy;

import BackEnd.Game.Game;
import BackEnd.Game.SaveLoadHelper;

import javax.swing.*;

public class SaveStrategy implements ISaveLoadStrategy {

    private SaveLoadHelper helper;

    @Override
    public JButton createButton(int index) {
        return new JButton(helper.getTextForSlot(index));
    }

    @Override
    public Game actionAfterClick(int index, Game game) {
        helper.save(game,index);
        return game;
    }

    @Override
    public String getTitle() {
        return "Zapis gry";
    }

    @Override
    public void setSaver(SaveLoadHelper helper) {
            this.helper = helper;
    }

}
