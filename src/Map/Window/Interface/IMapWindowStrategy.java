package Map.Window.Interface;

import Game.Game;

import javax.swing.*;

public interface IMapWindowStrategy {

    JButton createButton(Game game, int i, int j);
}
