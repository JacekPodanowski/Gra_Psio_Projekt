import Game.*;


public class Main {
    public static void main (String[] args){

        //Tworzę sobie uchyt
        Game g1 = null;
        SaveLoadHelper saver = new SaveLoadHelper();
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
