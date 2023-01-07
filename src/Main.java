import Chararcter.Item.Weapon;
import Game.*;
import SaveLoadStrategy.LoadStrategy;
import SaveLoadStrategy.SaveStrategy;
import View.MainWindow;
import View.SaveLoadWindow;


public class Main {
    public static void main (String[] args){

        Weapon w = new Weapon("mlot",5,'S',20,2);
        Weapon w1 = new Weapon("mlot",5,'S',20,2);
        Weapon w2 = new Weapon("mlot",5,'S',20,2);
        Weapon w3 = new Weapon("mlot",5,'S',20,2);
        Weapon w4 = new Weapon("mlot",5,'S',20,2);
        System.out.println(w.toString());
        System.out.println(w1.toString());
        System.out.println(w2.toString());
        System.out.println(w3.toString());
        System.out.println(w4.toString());


        //Tworzę sobie uchyt
        Game g1 = null;

        MainWindow mainWindow = new MainWindow();
        mainWindow.setModal(true);
        mainWindow.setAlwaysOnTop(true);
        mainWindow.setVisible(true);
        int choice = 0;

            while(choice!=4){
                System.out.println("Witaj w grze: Proba Ucieczki Z Kostki");
                System.out.print("1. Rozpocznij nową gre\t\t");
                System.out.print("2. Wczytaj zapisaną gre\t\t");
                System.out.print("3. Zapisz grę\t\t");
                System.out.println("4. Wyjdź\t\t");
                switch (Game.askForChoice(4)){
                    case 1:
                        g1 = new Game();
                        break;
                    case 2:
                        SaveLoadWindow loadWindow = new SaveLoadWindow(g1, new LoadStrategy());
                        loadWindow.setModal(true);
                        loadWindow.setAlwaysOnTop(true);
                        loadWindow.setVisible(true);

                        g1 =loadWindow.getGame();
                        break;
                    case 3:
                        SaveLoadWindow saveWindow = new SaveLoadWindow(g1, new SaveStrategy());
                        saveWindow.setModal(true);
                        saveWindow.setAlwaysOnTop(true);
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
