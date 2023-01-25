package GUI.View;

import BackEnd.Game.Game;
import BackEnd.Game.SaveLoadHelper;
import GUI.SaveLoadStrategy.ISaveLoadStrategy;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static GUI.View.MainWindow.centerLocation;

public class SaveLoadWindow extends JDialog implements Subject {

    JLabel title;
    SaveLoadHelper helper;
    List<JButton> slots;
    ISaveLoadStrategy strategy;
    Game game;
    private ArrayList<Observer> observers = new ArrayList<>();

    boolean finishedSucceslyffly;

    public SaveLoadWindow(Game g, ISaveLoadStrategy strategy) {

        this.game = g;
        this.strategy = strategy;
        helper = new SaveLoadHelper();
        strategy.setSaver(helper);
        finishedSucceslyffly = false;

        this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        this.setSize(200, 300);
        this.setLocation(centerLocation(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                this.getWidth(),
                this.getHeight()));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(199, 196, 181));
        GridLayout gridLayout = new GridLayout(helper.getNumberOfSlots()+1, 1);
        mainPanel.setLayout(gridLayout);
        setContentPane(mainPanel);
        title = new JLabel(strategy.getTitle());
        title.setFont(new Font("", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(title);
        slots = new ArrayList<JButton>();

        for (int i = 0; i < helper.getNumberOfSlots(); i++) {
            slots.add(strategy.createButton(i));
            slots.get(i).setBackground(new Color(136, 93, 44));
            mainPanel.add(slots.get(i));
            slots.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game = strategy.actionAfterClick(slots.indexOf(e.getSource()),game);
                    setVisible(false);
                    finishedSucceslyffly = true;
                    game.setLocationChanged(true);
                    notifyObservers();
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

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(this.getGame());
    }
}
