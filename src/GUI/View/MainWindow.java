package GUI.View;


import BackEnd.Game.Game;
import GUI.Panels.MainPanel;
import GUI.Panels.WindowStates;
import GUI.SaveLoadStrategy.LoadStrategy;
import GUI.SaveLoadStrategy.SaveStrategy;
import Observers.GUIRefresher;
import Observers.Observer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Observer observer;
    private Game game;
    private MainPanel mainPanel;

    public MainWindow() {
        Dimension d = new Dimension(900, 700);
        this.setSize(d);
        this.setPreferredSize(d);
        this.setLocation(centerLocation(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                this.getWidth(),
                this.getHeight()));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                int playerSelection = JOptionPane.showConfirmDialog(MainWindow.this, "Czy chcesz zapisać grę?", "Potwierdż zamknięcie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (playerSelection == JOptionPane.YES_NO_OPTION) {
                    SaveLoadWindow saveWindow = new SaveLoadWindow(mainPanel.getGame(), new SaveStrategy());
                    saveWindow.setModal(true);
                    saveWindow.setAlwaysOnTop(true);
                    saveWindow.setVisible(true);

                    if (saveWindow.isFinishedSucceslyffly()) {
                        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    } else setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                } else if (playerSelection == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    
                } else setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });
        mainPanel = new MainPanel(game, WindowStates.STARTMENU);
        setContentPane(mainPanel);
        setVisible(true);
    }


    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    public static Point centerLocation(int parentWidth, int parentHeight, int width, int height){
        return new Point((parentWidth -width) /2,(parentHeight -height)/2);
    }
    public static Point centerLocation(Component parent, Component object){
        return new Point((parent.getWidth() - object.getWidth()) / 2,(parent.getHeight() - object.getHeight()) / 2);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
