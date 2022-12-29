import Chararcter.*;
import Chararcter.Item.Weapon;
import Game.*;
import Game.Event.Fight;


public class Main {
    public static void main (String[] args){

        //Tworzę sobie uchyt
        Game g1 = null;
        Saver saver = new Saver();
        //new SaveWindow(g1, saver);

        int choice = 0;

            while(choice!=4){
                System.out.println("Witaj w grze: Proba Ucieczki Z Kostki");
                System.out.println("1. Rozpocznij nową gre\t\t");
                System.out.println("2. Wczytaj zapisaną gre\t\t");
                System.out.println("3. Zapisz grę\t\t");
                System.out.println("4. Wyjdź\t\t");
                switch (Game.askForChoice()){
                    case 1:
                        g1 = new Game();
                        break;
                    case 2:
                        //g1 = saver.load();
                        break;
                    case 3:
                        SaveWindow saveWindow = new SaveWindow(g1);
                        saveWindow.setModal(true);
                        saveWindow.setVisible(true);

                        break;
                    case 4:
                        System.out.println("Masz 3 opcje, wybierz lepiej nastepnym razem");
                        System.exit(0);
                        break;
                }}

        /*/
        Player P1 = new Player(100,1,10,1,0,1);
        
        Fight w = new Fight();
        w.event(P1);
        
        Weapon mlotek = new Weapon("Mlotek",1,"Budowlany","S",2,1,70);
        //cel jest taki żeby dać graczpwi mlotek
        /*/


    }
}
