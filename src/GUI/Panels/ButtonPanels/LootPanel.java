package GUI.Panels.ButtonPanels;

import javax.swing.*;

<<<<<<< Updated upstream
public class LootPanel extends JPanel {
=======
    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(this.getGame());
    }

    public Game getGame() {
        return game;
    }
>>>>>>> Stashed changes
}
