package View;


import Game.Game;
import Game.Map;
import Game.RoomType;
import Map.Window.Interface.*;
import Observable.Subject;
import Observers.Observer;
import SaveLoadStrategy.ISaveLoadStrategy;
import SaveLoadStrategy.LoadStrategy;
import SaveLoadStrategy.SaveStrategy;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainWindow extends JDialog implements Observer, Subject{
    JButton[][] rooms;
    JButton saveButton;
    JButton loadButton;
    JButton startGameButton;
    IMapWindowStrategy strategy;
    ISaveLoadStrategy saveLoadStrategy;
    ArrayList<Observer> observers = new ArrayList<Observer>();
    Game game;
    JTextArea display;
    JTextField answerField;
    boolean answer;
    char answerChar;


    public MainWindow() {

        this.setSize(400, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        createMenuPanel();

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(1, 2));
        upPanel.add(new JButton("Okno danego pokoju"));
        upPanel.add(createGamePanel());

        JPanel downPanel = new JPanel();
        downPanel.setLayout(new FlowLayout());
        downPanel.add(createTextFieldPanel());

        mainPanel.add(upPanel);
        mainPanel.add(downPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                int playerSelection = JOptionPane.showConfirmDialog(MainWindow.this, "Czy chcesz zapisać grę?", "Potwierdż zamknięcie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (playerSelection == JOptionPane.YES_NO_OPTION){
                    SaveLoadWindow saveWindow = new SaveLoadWindow(game, new SaveStrategy());
                    saveWindow.setModal(true);
                    saveWindow.setAlwaysOnTop(true);
                    saveWindow.setVisible(true);

                    if(saveWindow.isFinishedSucceslyffly()){
                        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    } else setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

                } else if (playerSelection == JOptionPane.NO_OPTION) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                } else setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });

        answer = false;

        setContentPane(mainPanel);
    }

    private JPanel createGamePanel()
    {
        JPanel gamePanel = new JPanel();
        if(game == null) {
            return gamePanel;
        }

        Map map = game.getMap();
        RoomType[][] roomsType = map.displayTheMapInGUI(game.getPlayer());


        GridLayout gridLayout = new GridLayout(map.getTabOfRoom().length, map.getTabOfRoom().length);
        gamePanel.setLayout(gridLayout);

        for (int i = 0; i < map.getTabOfRoom().length; i++) {
            for (int j = 0; j < map.getTabOfRoom().length; j++) {
                switch (roomsType[i][j]) {
                    case empty:
                        strategy = new ButtonEmpty();
                        strategy.createButton(rooms[i][j]);
                        break;
                    case visited:
                        strategy = new ButtonVisited();
                        strategy.createButton(rooms[i][j]);
                        break;
                    case available:
                        strategy = new ButtonAvaiable();
                        strategy.createButton(rooms[i][j]);
                        break;
                    case withPlayer:
                        strategy = new ButtonWithPlayer();
                        strategy.createButton(rooms[i][j]);
                        break;
                    case hidden:
                        strategy = new ButtonHidden();
                        strategy.createButton(rooms[i][j]);
                        break;
                }
                gamePanel.add(rooms[i][j]);
            }
        }
        return gamePanel;
    }

    private void createMenuPanel(){
        JMenu jMenu = new JMenu();
        jMenu.setText("Opcje Gry");

        JMenuItem jMenuItemLoad = new JMenuItem();
        jMenuItemLoad.setText("Załaduj Grę");
        jMenuItemLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLoadWindow loadWindow = new SaveLoadWindow(game, new LoadStrategy());
                loadWindow.setModal(true);
                loadWindow.setAlwaysOnTop(true);
                loadWindow.setVisible(true);
                if(loadWindow.isFinishedSucceslyffly()) {
                    game = loadWindow.getGame();
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
        jMenu.add(jMenuItemLoad);

        JMenuItem jMenuItemNewGame = new JMenuItem();
        jMenuItemNewGame.setText("Nowa Gra");
        jMenuItemNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new Game();
            }
        });
        jMenu.add(jMenuItemNewGame);

        JMenuItem jMenuItemSave = new JMenuItem();
        jMenuItemSave.setText("Zapisz lub nadpisz Grę");
        jMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLoadWindow saveWindow = new SaveLoadWindow(game, new SaveStrategy());
                saveWindow.setModal(true);
                saveWindow.setAlwaysOnTop(true);
                saveWindow.setVisible(true);
                if(saveWindow.isFinishedSucceslyffly()) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
        jMenu.add(jMenuItemSave);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);

    }

    private JPanel createTextFieldPanel(){

        JPanel panel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        display = new JTextArea();
        answerField = new JTextField(2);
        JButton okButton = new JButton();
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(answerField.getText().length()==1)
                {
                    answerChar = answerField.getText().charAt(0);
                    answerField.setText("");
                    answer = true;
                }
                else
                {
                    answer = false;
                }
            }
        });



        display.setColumns(20);
        display.setRows(5);
        display.setEditable(false);
        jScrollPane1.setViewportView(display);

        answerField.setText("");
        answerField.setSize(50,20);

        okButton.setText("OK");

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(okButton)
                                                .addGap(0, 242, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(answerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(okButton))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        return panel;
    }



    @Override
    public void update(Game game) {

    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean isAnswer() {
        return answer;
    }

    public char gAnswerChar() {
        answer = false;
        return answerChar;
    }

    public void println(String text){
        String text2 = display.getText() + text+ "\n";
        display.setText(text2);
    }
}
