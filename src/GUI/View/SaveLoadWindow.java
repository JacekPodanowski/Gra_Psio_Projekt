package GUI.View;

import BackEnd.Game.Game;
import BackEnd.Game.SaveLoadHelper;
import GUI.SaveLoadStrategy.ISaveLoadStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SaveLoadWindow extends JDialog {

    Label title;
    SaveLoadHelper helper;
    List<JButton> slots;
    ISaveLoadStrategy strategy;
    Game game;

    boolean finishedSucceslyffly;

    public SaveLoadWindow(Game g, ISaveLoadStrategy strategy) {

        this.game = g;
        this.strategy = strategy;
        helper = new SaveLoadHelper();
        strategy.setSaver(helper);
        finishedSucceslyffly = false;

        this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        this.setSize(200, 300);

        JPanel mainPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(helper.getNumberOfSlots()+1, 1);
        mainPanel.setLayout(gridLayout);
        setContentPane(mainPanel);
        title = new Label(strategy.getTitle());
        mainPanel.add(title);
        slots = new ArrayList<JButton>();

        for (int i = 0; i < helper.getNumberOfSlots(); i++) {
            slots.add(strategy.createButton(i));
            mainPanel.add(slots.get(i));
            slots.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game = strategy.actionAfterClick(slots.indexOf(e.getSource()),game);
                    setVisible(false);
                    finishedSucceslyffly = true;
                }
            });
        }
    }

    public Game getGame() {
        return game;
    }

    public boolean isFinishedSucceslyffly() {
        return finishedSucceslyffly;
    }
}
