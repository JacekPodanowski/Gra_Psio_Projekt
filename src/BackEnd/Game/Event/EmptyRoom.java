package BackEnd.Game.Event;

import BackEnd.Chararcter.Player;
import BackEnd.Game.Game;
import BackEnd.Chararcter.Item.Armor;
import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Item.Weapon;


import java.util.Random;

public class EmptyRoom implements Event {
    private String name = "Pusty";

    @Override
    public Event event(Player player, int choice) {
        return null;
    }

    @Override
    public Event event(Player player) {
        boolean rested=false;
        System.out.println("\n\nZnalazłeś się w pustym pokoju, masz chwilę dla siebie.");
        int wybor;
        do{
            if(rested==false) {
                System.out.print("Co chcesz zrobić?\n 1. Przejdź do następnego pokoju.\t 2. Użyj przedmiotu.\t3. Zobacz swoje statystyki.");
                System.out.println("\t4. Odpocznij.");
                wybor = Game.askForChoice(4);
            }else {
                System.out.println("Co chcesz zrobić?\n 1. Przejdź do następnego pokoju.\t 2. Użyj przedmiotu.\t3. Zobacz swoje statystyki.");
                wybor = Game.askForChoice(3);
            }

            switch(wybor) {
                case 1:
                    //nic
                    break;
                case 2:
                    System.out.println("Jakiego przedmiotu chcesz użyć?");
                    player.displayInventoryAndUse();
                    break;
                case 3:
                    player.showStats();
                    break;
                case 4:
                    System.out.println("Wyczerpany padasz na ziemie.... \n");
                    rested=true;

                    Random E = new Random();
                    switch (E.nextInt(6)){
                        case 0 :
                            player.healMissingHealth(5);
                            System.out.println("Wstajesz czując się nieco lepeij");
                            break;
                        case 1 :
                            player.healMissingHealth(10);
                            System.out.println("Wstajesz czując się znacznie lepeij");
                            break;
                        case 2 :
                            player.setHealth(player.getHealth()-15);
                            System.out.println("Budzą cie 3 szybkie lecz bolesne ciosy kijem ale pokój wydaje się być pusty");
                            System.out.printf("|Zycie zmiejszone o 15 punktów|");
                            break;
                        case 3 :
                            System.out.println("Wstajesz i ruszasz dalej");
                            break;
                        case 4 :
                            player.setInventory(new Item[5]);
                            player.healMissingHealth(10);
                            System.out.println("Budzą się wypoczęty lecz nigdzie nie możesz znaleść swojej torby");
                            break;
                        case 5 :
                            player.setWeapon(null);
                            player.setArmor(null);
                            System.out.println("Budzisz się kompletnie nagi a po twoim sprzęcie ani śladu, ciekawe jako to sie stało ?");
                            break;
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Niepoprawna opcja!");
                    break;
            }
        }while (wybor != 1);
        return null;
    }
    public String toString() {return name;}
}
