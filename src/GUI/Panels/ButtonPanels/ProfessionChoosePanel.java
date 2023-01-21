package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Profession.Archer;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Warrior;
import BackEnd.Game.Game;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfessionChoosePanel extends JPanel implements Subject {
    private JButton warrior;
    private JButton mage;
    private JButton archer;
    private Game game;
    private ArrayList<Observer> observers = new ArrayList<>();


    public ProfessionChoosePanel(Game game){
        this.game = game;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.BLACK);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textPanel.setBackground(Color.black);
        textPanel.add(Box.createRigidArea(new Dimension(900,0)));

        JButton textChooseType = new JButton();
        textChooseType.setAlignmentX(Component.CENTER_ALIGNMENT);
        textChooseType.setFocusable(false);
        textChooseType.setBorderPainted(false);
        textChooseType.setEnabled(false);
        textChooseType.setContentAreaFilled(false);
        textChooseType.add(new TextChooseTypeChar());

        textChooseType.setMaximumSize(new Dimension(900,50));
        textChooseType.setMinimumSize(new Dimension(900,50));
        textChooseType.setPreferredSize(new Dimension(900,50));
        textChooseType.setFont(new Font(null, Font.BOLD, 30));

        textPanel.add(Box.createRigidArea(new Dimension(900,0)));


        // napis nazwa Wojownik
        JButton textChar1 = new JButton();
        textChar1.setContentAreaFilled(false);

        textChar1.setMinimumSize(new Dimension(290,50));
        textChar1.setMaximumSize(new Dimension(290,50));
        textChar1.setPreferredSize(new Dimension(290,50));

        textChar1.setText("Wojownik");
        Font font = new Font(null, Font.BOLD, 25);
        textChar1.setForeground(Color.getHSBColor(0,1,0.5f));
        textChar1.setFont(font);
        textChar1.setFocusable(false);
        textChar1.setBorderPainted(false);

        // napis nazwa Mag
        JButton textChar2 = new JButton();
        textChar2.setContentAreaFilled(false);

        textChar2.setMinimumSize(new Dimension(290,50));
        textChar2.setMaximumSize(new Dimension(290,50));
        textChar2.setPreferredSize(new Dimension(290,50));

        textChar2.setText("Mag");
        textChar2.setForeground(Color.getHSBColor(0,1,0.5f));
        textChar2.setFont(font);
        textChar2.setFocusable(false);
        textChar2.setBorderPainted(false);


        // napis nazwa Lucznik
        JButton textChar3 = new JButton();
        textChar3.setContentAreaFilled(false);

        textChar3.setMinimumSize(new Dimension(290,50));
        textChar3.setMaximumSize(new Dimension(290,50));
        textChar3.setPreferredSize(new Dimension(290,50));

        textChar3.setText("Lucznik");
        textChar3.setForeground(Color.getHSBColor(0,1,0.5f));
        textChar3.setFont(font);
        textChar3.setFocusable(false);
        textChar3.setBorderPainted(false);

        // dodanie komponentow
        textPanel.add(textChooseType);
        textPanel.add(Box.createRigidArea(new Dimension(900,60)));
        textPanel.add(textChar1);
        textPanel.add(textChar2);
        textPanel.add(textChar3);
        this.add(textPanel);


        // przyciski z profesjami
        this.add(Box.createRigidArea(new Dimension(0, 0)));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        initiateWarrior(game);
        initiateMage(game);
        initiateArcher(game);

        buttonPanel.add(warrior);
        buttonPanel.add(mage);
        buttonPanel.add(archer);
        this.add(buttonPanel);
    }
    private void initiateWarrior(Game game){

        warrior = new JButton();
        warrior.setBackground(Color.getHSBColor(30,1,0.1f));
        Border border = BorderFactory.createLineBorder(Color.getHSBColor(0,1,0.5f), 5);
        warrior.setBorder(border);

        ImageIcon pictureWarrior = new ImageIcon("images/Wojownik.png");
        warrior.add(new JLabel(pictureWarrior, JLabel.CENTER));

        warrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Warrior());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                System.out.println(game.getPlayer().getProfession());
                game.setLocationChanged(true);
                notifyObservers();
                removeObserver(observers.get(0));
            }
        });
    }
    private void initiateMage(Game game){
        mage = new JButton();
        mage.setBackground(Color.getHSBColor(30,1,0.1f));
        Border border = BorderFactory.createLineBorder(Color.getHSBColor(0,1,0.5f), 5);
        mage.setBorder(border);

        ImageIcon pictureWarrior = new ImageIcon("images/Mag.png");
        mage.add(new JLabel(pictureWarrior, JLabel.CENTER));
        //warrior.setText("Mag");

        mage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Mage());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                game.setLocationChanged(true);
                notifyObservers();
                removeObserver(observers.get(0));
            }
        });
    }
    private void initiateArcher(Game game){
        archer = new JButton();
        archer.setBackground(Color.getHSBColor(30,1,0.1f));
        Border border = BorderFactory.createLineBorder(Color.getHSBColor(0,1,0.5f), 5);
        archer.setBorder(border);

        ImageIcon pictureWarrior = new ImageIcon("images/Lucznik.png");
        archer.add(new JLabel(pictureWarrior, JLabel.CENTER));
        archer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(new Archer());
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                game.setLocationChanged(true);
                notifyObservers();
                removeObserver(observers.get(0));
            }
        });
    }

    static class TextChooseTypeChar extends JComponent{
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(Color.getHSBColor(0,1,0.5f));
            g2.drawString("Aby rozpocząć grę, wybierz swoją profesję", 150,30 );
        }
    }

//    static class TextWojownik extends JComponent{
//        @Override
//        protected void paintComponent(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setFont(new Font(null, Font.BOLD, 25));
//            g2.setPaint(Color.getHSBColor(0,1,0.5f));
//            g2.drawString("Wojownik", 70,30 );
//        }
//    }
//
//    static class TextMag extends JComponent{
//        @Override
//        protected void paintComponent(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setFont(new Font(null, Font.BOLD, 25));
//            g2.setPaint(Color.getHSBColor(0,1,0.5f));
//            g2.drawString("Mag", 100,30 );
//        }
//    }
//
//    static class TextLucznik extends JComponent{
//        @Override
//        protected void paintComponent(Graphics g) {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setFont(new Font(null, Font.BOLD, 25));
//            g2.setPaint(Color.getHSBColor(0,1,0.5f));
//            g2.drawString("Lucznik", 80,30 );
//        }
//    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
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
