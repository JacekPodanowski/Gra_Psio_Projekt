package GUI.Panels.ButtonPanels;

import BackEnd.Game.Event.Fight;
import BackEnd.Game.Game;
import GUI.Panels.ConsolePanel;
import Observable.Subject;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FightPanel extends JPanel implements Subject {

    private Game game;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private ConsolePanel consolePanel;
    private int abilityChoice;
    private ArrayList<Observer> observers = new ArrayList<>();


    public FightPanel(Game game){
        this.game = game;
        this.add(new NewJPanel());
    }

    public class NewJPanel extends javax.swing.JPanel {

        /**
         * Creates new form NewJPanel
         */
        public NewJPanel() {
            initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            jPanel2 = new javax.swing.JPanel();
            jScrollPane2 = new javax.swing.JScrollPane();
            consolePanel = new ConsolePanel((Fight) game.getMap().getPlayerLocation(game.getPlayer()).getEvent());
            jButton5 = skill_1();
            jButton6 = skill_2();
            jButton7 = skill_3();
            jButton8 = skill_4();

            consolePanel.setColumns(20);
            consolePanel.setRows(5);
            jScrollPane2.setViewportView(consolePanel);


            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(jButton5)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton6)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton7)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton8))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(35, 35, 35)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(28, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton7)
                                            .addComponent(jButton8)
                                            .addComponent(jButton6)
                                            .addComponent(jButton5)))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(138, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
        }// </editor-fold>


        // Variables declaration - do not modify

        // End of variables declaration
    }

    private JButton skill_1(){
        JButton skill_1 = new JButton();
        skill_1.setText(game.getPlayer().getAbilities()[0].toString());
        skill_1.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().attack(((Fight) game.getMap().getPlayerLocation(game.getPlayer()).getEvent()).getEnemy(), 0);
                abilityChoice = 0;
                notifyObservers();
            }});
        return skill_1;
    }

    private JButton skill_2(){
        JButton skill_2 = new JButton();
        skill_2.setText(game.getPlayer().getAbilities()[1].toString());
        skill_2.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().attack(((Fight) game.getMap().getPlayerLocation(game.getPlayer()).getEvent()).getEnemy(), 1);
                abilityChoice = 1;
                notifyObservers();
            }});
        return skill_2;
    }

    private JButton skill_3(){
        JButton skill_3 = new JButton();
        skill_3.setText(game.getPlayer().getAbilities()[2].toString());
        skill_3.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().attack(((Fight) game.getMap().getPlayerLocation(game.getPlayer()).getEvent()).getEnemy(), 2);
                abilityChoice = 2;
                notifyObservers();
            }});
        return skill_3;
    }

    private JButton skill_4(){
        JButton skill_4 = new JButton();
        skill_4.setText(game.getPlayer().getAbilities()[3].toString());
        skill_4.setFont(new Font("ButtonFont", Font.BOLD, 20));
        skill_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().attack(((Fight) game.getMap().getPlayerLocation(game.getPlayer()).getEvent()).getEnemy(), 3);
                abilityChoice = 3;
                notifyObservers();
            }});
        return skill_4;
    }


    @Override
    public void registerObserver(Observer observer) {observers.add(observer);}

    @Override
    public void removeObserver(Observer observer) {observers.remove(observer);}

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) observer.update(this.getGame());
    }

    public Game getGame() {
        return game;
    }

    public ConsolePanel getConsolePanel() {
        return consolePanel;
    }

    public JButton getjButton5() {
        return jButton5;
    }

    public JButton getjButton6() {
        return jButton6;
    }

    public JButton getjButton7() {
        return jButton7;
    }

    public JButton getjButton8() {
        return jButton8;
    }

    public int getAbilityChoice() {
        return abilityChoice;
    }
}
