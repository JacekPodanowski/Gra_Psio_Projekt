package View;


import Game.*;
import Game.Event.*;
import Observable.Subject;
import Observers.Observer;
import Observers.PlayerOnMapPosition;
import SaveLoadStrategy.*;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainWindow extends JFrame implements Subject {
    JButton[][] rooms;
    ArrayList<Observer> observers = new ArrayList<Observer>();
    Game game;
    JTextArea display;
    JTextField answerField;
    boolean answer;
    char answerChar;
    private JPanel mapPanel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel mainPanel;
    private JPanel gamePanel;


    public MainWindow() {
        Dimension d = new Dimension(900, 700);
        this.setSize(d);
        this.setPreferredSize(d);
        this.setLocation(centerLocation(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                this.getWidth(),
                this.getHeight()));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setPreferredSize(d);
        createMenuPanel();

        upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(1, 2));
        gamePanel = new JPanel();
        upPanel.add(gamePanel);
        gamePanel.setPreferredSize(new Dimension(450, 400));
        upPanel.add(createMapPanel());

        downPanel = new JPanel();
        downPanel.setPreferredSize(new Dimension(450, 300));
        downPanel.setLayout(new FlowLayout());
        mapPanel = createTextFieldPanel();
        downPanel.add(mapPanel);

        mainPanel.add(upPanel);
        mainPanel.add(downPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                int playerSelection = JOptionPane.showConfirmDialog(MainWindow.this, "Czy chcesz zapisać grę?", "Potwierdż zamknięcie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (playerSelection == JOptionPane.YES_NO_OPTION) {
                    SaveLoadWindow saveWindow = new SaveLoadWindow(game, new SaveStrategy());
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

        answer = false;

        setContentPane(mainPanel);
    }

    private JPanel createMapPanel() {
        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(450, 400));
        if (game == null) {
            return mapPanel;
        }
        rooms = new JButton[game.getMap().getTabOfRoom().length][game.getMap().getTabOfRoom()[0].length];
        mapPanel.setLayout(new GridLayout(game.getMap().getTabOfRoom().length, game.getMap().getTabOfRoom().length));
        for (int i = 0; i < game.getMap().getTabOfRoom().length; i++) {
            for (int j = 0; j < game.getMap().getTabOfRoom()[0].length; j++) {
                switch (game.getMap().getRoomTypes()[i][j]) {
                    case empty:
                        rooms[i][j] = createButton(i, j);
                        break;
                    case visited:
                        rooms[i][j] = createButton(i, j);
                        break;
                    case withPlayer:
                        rooms[i][j] = createButton(i, j);
                        break;
                    case hidden:
                        rooms[i][j] = createButton(i, j);
                        break;
                }
                mapPanel.add(rooms[i][j]);
                rooms[i][j].setEnabled(game.getMap().getTabOfRoom()[i][j].isAvailable());
                mapPanel.add(rooms[i][j]);
            }
        }
        return mapPanel;
    }

    private void createMenuPanel() {
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
                if (loadWindow.isFinishedSucceslyffly()) {
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
                notifyObservers();
                game.startGame();
                upPanel.remove(mapPanel);
                mapPanel = createMapPanel();
                upPanel.add(mapPanel);
                upPanel.revalidate();
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
                if (saveWindow.isFinishedSucceslyffly()) {
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

    private JPanel createTextFieldPanel() {

        JPanel panel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        display = new JTextArea();
        answerField = new JTextField(2);
        JButton okButton = new JButton();
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answerField.getText().length() == 1) {
                    answerChar = answerField.getText().charAt(0);
                    answerField.setText("");
                    answer = true;
                } else {
                    answer = false;
                }
            }
        });


        display.setColumns(20);
        display.setRows(5);
        display.setEditable(false);
        jScrollPane1.setViewportView(display);

        answerField.setText("");
        answerField.setSize(50, 20);

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

    private JPanel gamePanel() {
        if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Entrance) {

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof EmptyRoom) {

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Exit) {

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Fight) {
            for (int i = 0; i < game.getPlayer().getAbilities().length; i++);

        } else if (game.getMap().getPlayerLocation(game.getPlayer()).getEvent() instanceof Loot) {

        }
        return gamePanel;
    }
    public JButton createButton(int i, int j) {
        JButton room = new JButton();
//        try {
//            room.setIcon(new ImageIcon(ImageIO.read(new File("empty.png"))));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getMap().setPlayerLocation(game.getPlayer(), game.getMap().getTabOfRoom()[i][j]);
                game.notifyObservers();
            }
        });
        return room;
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
        for(int i = 0; i < observers.size(); i++) {
                observers.get(i).update(this);
        }
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public char getAnswerChar() {
        return answerChar;
    }

    public void setAnswerChar(char answerChar) {
        this.answerChar = answerChar;
    }

    public boolean isAnswer() {
        return answer;
    }

    public char gAnswerChar() {
        answer = false;
        return answerChar;
    }

    public void println(String text) {
        String text2 = display.getText() + text + "\n";
        display.setText(text2);
    }
    public Point centerLocation(int parentWidth, int parentHeight, int width, int height){
        return new Point((parentWidth -width) /2,(parentHeight -height)/2);
    }
}
