import Chararcter.*;
import Game.*;
import Game.Event.Walka;

public class Main {
    public static void main (String[] args){
        Player P1 = new Player(100,1,10,1,0,1);
        Walka w = new Walka();
        w.event(P1);
    }
}
