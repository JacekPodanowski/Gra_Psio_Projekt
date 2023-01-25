package GUI.SaveLoadStrategy;

import BackEnd.Game.Game;
import BackEnd.Game.SaveLoadHelper;

import javax.swing.*;

public class LoadStrategy implements ISaveLoadStrategy {

    private SaveLoadHelper helper;

    @Override
    public JButton createButton(int index) {
        JButton button =new JButton(helper.getTextForSlot(index));
        if(helper.isEmptySlot(index))
        {
            button.setEnabled(false);
        }

        return button;
    }

    @Override
    public Game actionAfterClick(int index, Game game) {
        game = helper.load(index);
        return game;
    }

    @Override
    public String getTitle() {
        return "          Odczyt gry";
    }

    @Override
    public void setSaver(SaveLoadHelper helper) {
        this.helper = helper;
    }
}
