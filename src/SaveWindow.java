import Game.Game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SaveWindow extends JDialog implements LSOWindow{

    Label title;
    Saver saver;
    List<JButton> slots;

    public SaveWindow(Game game){

            this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
            this.setSize(200,300);
            saver = new Saver();

            JPanel mainPanel = new JPanel();
            GridLayout gridLayout = new GridLayout(saver.getNumberOfSlots()+1, 1);
            mainPanel.setLayout(gridLayout);

            setContentPane(mainPanel);
            title = new Label("Zapis gry");
            mainPanel.add(title);
            slots = new ArrayList<JButton>();

            setContentPane(mainPanel);
            for(int i = 0; i< saver.getNumberOfSlots(); i++){
                slots.add(new JButton(saver.getTextForSlot(i)));
                mainPanel.add(slots.get(i));
                slots.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saver.save(game, slots.indexOf(e.getSource()));
                        setVisible(false);
                    }
                });
            }
    }
}
