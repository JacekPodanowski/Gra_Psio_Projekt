
import BackEnd.Game.Game;

import GUI.View.MainWindow;
import Observers.GUIRefresher;


public class Main {
    public static void main (String[] args){


        //Tworzę sobie uchyt
        Game g1 = null;
        new GUIRefresher(new MainWindow());

//        int choice = 0;
//
//            while(choice!=4){
//                System.out.println("Witaj w grze: Proba Ucieczki Z Kostki");
//                System.out.println("1. Rozpocznij nową gre\t\t");
//                System.out.println("2. Wczytaj zapisaną gre\t\t");
//                System.out.println("3. Zapisz grę\t\t");
//                System.out.println("4. Wyjdź\t\t");
//                switch (Game.askForChoice()){
//                    case 1:
//                        g1 = new Game();
//                        break;
//                    case 2:
//                        SaveLoadWindow loadWindow = new SaveLoadWindow(g1, new LoadStrategy());
//                        loadWindow.setModal(true);
//                        loadWindow.setAlwaysOnTop(true);
//                        loadWindow.setVisible(true);
//
//                        g1 =loadWindow.getGame();
//                        break;
//                    case 3:
//                        SaveLoadWindow saveWindow = new SaveLoadWindow(g1, new SaveStrategy());
//                        saveWindow.setModal(true);
//                        saveWindow.setAlwaysOnTop(true);
//                        saveWindow.setVisible(true);
//
//                        break;
//
//                    case 4:
//                        System.out.println("Masz 3 opcje, wybierz lepiej nastepnym razem");
//                        System.exit(0);
//                        break;
//                }}

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
    }
}
