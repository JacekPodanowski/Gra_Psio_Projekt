package BackEnd.Game;

import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Event.*;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    //================================================= ATRYBUTY KLASY =================================================
    private boolean enter;
    private boolean exit;
    private boolean visited;
    private boolean rested=false;
    private int rowRoom;
    private int colRoom;
    private ArrayList<int[]> pathSet; //arraylista przechowujaca wspolrzedne pokojow do ktorych mozna pojsc z danego pokoju
    private ArrayList<Room> availableRoomsAround = new ArrayList<Room>();
    private Event event;
    private int numRoom;
    private boolean available;
    private RoomEvent event1;

    //==================================================================================================================



    //============================================= GETTERY I SETTERY ==================================================
    public int getNumRoom() {
        return numRoom;
    }
    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }
    public void setEnter(boolean enter) {
        this.enter = enter;
    }
    public void setExit(boolean exit) {
        this.exit = exit;
    }
    public ArrayList<int[]> getPathSet() {
        return pathSet;
    }
    public int getRowRoom() {
        return rowRoom;
    }
    public int getColRoom() {
        return colRoom;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public ArrayList<Room> getAvailableRoomsAround() {
        return availableRoomsAround;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public RoomEvent getEvent1() {
        return event1;
    }
    public void setEvent1(RoomEvent event1) {
        this.event1 = event1;
    }
    //==================================================================================================================



    //================================================ KONSTRUKTORY ====================================================
    public Room(int rowRoom, int colRoom, Player player){
        this.rowRoom = rowRoom;
        this.colRoom = colRoom;
        enter = false;
        exit = false;
        visited = false;
        pathSet = new ArrayList<int[]>();
        randomEvent(player);
    }

    //==================================================================================================================



    //================================================== METODY KLASY ==================================================
    public void randomEvent(Player player){
        Random generate = new Random();
        switch(generate.nextInt(4)){
            case 0, 3:
                this.event = new Fight();
                this.setEvent1(RoomEvent.FIGHT);
                break;
            case 1:
                this.event = new Loot();
                this.setEvent1(RoomEvent.LOOT);
                break;
            case 2:
                this.event = new EmptyRoom();
                this.setEvent1(RoomEvent.EMPTYROOM);
                break;
            default:
                System.out.println("Błąd przy losowaniu eventu!");
                break;
        }
    }

    public int randomRest(Player player){
        Random r = new Random();
        int option = r.nextInt(6);
        switch (option){
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
        return option;
    }

    //==================================================================================================================
}
