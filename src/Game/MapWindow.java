    package Game;

    import Map.Window.Interface.*;

    import javax.swing.*;
    import java.awt.*;

    public class MapWindow extends JDialog {

        JButton[][] rooms;
        Map map;
        IMapWindowStrategy strategy;

        public MapWindow(Map map){

            this.setSize(100, 100);

            JPanel mainPanel = new JPanel();
            GridLayout gridLayout = new GridLayout(map.getTabOfRoom().length, map.getTabOfRoom().length);
            mainPanel.setLayout(gridLayout);
            setContentPane(mainPanel);

            for(int i = 0; i< map.getTabOfRoom().length; i++){
                for(int j = 0; j< map.getTabOfRoom().length; j++){
                    switch(map.getRoomTypes()[i][j]){
                        case empty:
                            strategy = new ButtonEmpty();
                            strategy.createButton(rooms[i][j]);
                            break;
                        case visited:
                            strategy = new ButtonVisited();
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
                    if(map.getTabOfRoom()[i][j].isAvailable()) {
                        rooms[j][j].setEnabled(true);
                    }
                    mainPanel.add(rooms[i][j]);
                }
            }
        }

}
