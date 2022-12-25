import Chararcter.*;
import Chararcter.Item.Weapon;
import Game.*;
import Game.Event.Fight;


public class Main {
    public static void main (String[] args){

        System.out.println("Witaj w grze : Proba Ucieczki Z Kostki");
        System.out.println("1 - Rozpocznij nową gre");
        System.out.println("2 - Wczytaj zapisaną gre");
        System.out.println("3 - Wyjdź");
        switch (Game.askForChoice()){
            case 1:
                Game g1 = new Game();
                break;
            case 2:
                //Game g1 = new Game(Plik zapisu);
                break;
            case 3:
                System.exit(0);
                break;
            case 4:
                System.out.println("Masz 3 opcje, wybierz lepiej nastepnym razem");
                System.exit(0);
                break;
        }

        /*/
        Player P1 = new Player(100,1,10,1,0,1);
        
        Fight w = new Fight();
        w.event(P1);
        
        Weapon mlotek = new Weapon("Mlotek",1,"Budowlany","S",2,1,70);
        //cel jest taki żeby dać graczpwi mlotek
        /*/


    }
}
