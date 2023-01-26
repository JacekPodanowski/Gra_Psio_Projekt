package BackEnd.Game;

import BackEnd.Chararcter.Enemy;
import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Event.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room implements Serializable {
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
    private List<Item> lootTab;
    private Enemy enemy;

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
    public boolean isRested() {
        return rested;
    }
    public void setRested(boolean rested) {
        this.rested = rested;
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
        if(this.event1 == RoomEvent.LOOT || this.event1 == RoomEvent.FIGHT){
            generateLoot();
            if(this.event1 == RoomEvent.FIGHT)
                enemy = new Enemy(player.getLevel());
        }
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

    public void generateLoot(){
        Random R = new Random();
        this.lootTab = new ArrayList<>();
        if (R.nextBoolean()) {
            this.lootTab.add(Game.generateItem());//moga byc 2 czasem
        } else {
            this.lootTab.add(Game.generateItem());
            this.lootTab.add(Game.generateItem());
        }
    }

    public List<Item> getLootTab() {
        return lootTab;
    }

    public int countItems(){
        return this.lootTab.size();
    }

    public Item getItem(){
        Item item = lootTab.get(0);
        lootTab.remove(0);
        return item;
    }

    public Enemy getEnemy() {
        return enemy;
    }
    //==================================================================================================================
}
