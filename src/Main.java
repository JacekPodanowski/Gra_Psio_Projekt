import Chararcter.*;
import Game.*;

public class Main {
    public static void main (String[] args){
        Player P1 = new Player(100,1,10,1,0,1);
        
        Walka w = new Walka();
        w.event(P1);
        
        Weapon mlotek = new Weapon("Mlotek",1,"Budowlany","sila",2,1,70);
        //cel jest taki żeby dać graczpwi mlotek
    }
}
