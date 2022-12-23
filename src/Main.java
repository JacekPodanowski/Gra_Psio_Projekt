import Chararcter.*;
import Chararcter.Item.Weapon;
import Game.*;
import Game.Event.Fight;


public class Main {
    public static void main (String[] args){
        Player P1 = new Player(100,1,10,1,0,1);
        
        Fight w = new Fight();
        w.event(P1);
        
        Weapon mlotek = new Weapon("Mlotek",1,"Budowlany","S",2,1,70);
        //cel jest taki żeby dać graczpwi mlotek

        Game g2 = new Game();
        g2.saveGame("Saveuje");
    }
}
