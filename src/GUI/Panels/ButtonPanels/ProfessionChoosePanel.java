package GUI.Panels.ButtonPanels;

import BackEnd.Chararcter.Profession.Archer;
import BackEnd.Chararcter.Profession.Mage;
import BackEnd.Chararcter.Profession.Profession;
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

        Font font = new Font(null, Font.BOLD, 25);

        // dodanie komponentow
        textPanel.add(textChooseType);
        textPanel.add(Box.createRigidArea(new Dimension(900,60)));
        // pola Wojownik, Mag, Lucznik
        textPanel.add(getTextTypeChar("Wojownik", font));
        textPanel.add(getTextTypeChar("Mag", font));
        textPanel.add(getTextTypeChar("Lucznik", font));

        this.add(textPanel);

        // przyciski z profesjami
        this.add(Box.createRigidArea(new Dimension(0, 0)));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));


        buttonPanel.add(initiateProfession(game, warrior, "images/Wojownik.png", new Warrior()));
        buttonPanel.add(initiateProfession(game, mage, "images/Mag.png",  new Mage()));
        buttonPanel.add(initiateProfession(game, archer, "images/Lucznik.png",  new Archer()));
        this.add(buttonPanel);
    }
    private  JButton initiateProfession(Game game, JButton typeChar, String fileName,
                                        Profession profession){

        typeChar = new JButton();
        typeChar.setBackground(Color.getHSBColor(30,1,0.1f));
        Border border = BorderFactory.createLineBorder(Color.getHSBColor(0,1,0.5f), 5);
        typeChar.setBorder(border);

        ImageIcon pictureWarrior = new ImageIcon(fileName);
        typeChar.add(new JLabel(pictureWarrior, JLabel.CENTER));

        typeChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().setProfession(profession);
                game.getPlayer().getProfession().attributesInitiation(game.getPlayer());
                System.out.println(game.getPlayer().getProfession());
                game.setLocationChanged(true);
                notifyObservers();
                removeObserver(observers.get(0));
            }
        });
        return typeChar;
    }

    static class TextChooseTypeChar extends JComponent{
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(Color.getHSBColor(0,1,0.5f));
            g2.drawString("Aby rozpocząć grę, wybierz swoją profesję", 150,30 );
        }
    }

    public static JButton getTextTypeChar(String typeChar, Font font){
        JButton textChar1 = new JButton();
        textChar1.setContentAreaFilled(false);

        textChar1.setMinimumSize(new Dimension(290,50));
        textChar1.setMaximumSize(new Dimension(290,50));
        textChar1.setPreferredSize(new Dimension(290,50));

        textChar1.setText(typeChar);
        textChar1.setForeground(Color.getHSBColor(0,1,0.5f));
        textChar1.setFont(font);
        textChar1.setFocusable(false);
        textChar1.setBorderPainted(false);

        return textChar1;
    }


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
