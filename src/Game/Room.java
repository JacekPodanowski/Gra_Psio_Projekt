package Game;

import Chararcter.Player;
import Game.Event.*;

import java.util.Random;

public class Room {
    //================================================= ATRYBUTY KLASY =================================================
    private boolean enter;
    private boolean exit;
    private int rowRoom;
    private int colRoom;
    private int [][] pathSet;
    private int difficulty;  // + lub - od lewela gracza
    private Event event;
    private int numRoom;

    //==================================================================================================================



    //============================================= GETTERY I SETTERY ==================================================
    public int getNumRoom() {
        return numRoom;
    }
    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }
    public boolean getEnter() {
        return enter;
    }
    public boolean getExit() {
        return exit;
    }
    public boolean isEnter() {
        return enter;
    }
    public void setEnter(boolean enter) {
        this.enter = enter;
    }
    public boolean isExit() {
        return exit;
    }
    public void setExit(boolean exit) {
        this.exit = exit;
    }
    public int[][] getPathSet() {
        return pathSet;
    }
    public void setPathSet(int[][] pathSet) {
        this.pathSet = pathSet;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
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
    public void setRowRoom(int rowRoom) {
        this.rowRoom = rowRoom;
    }

    public void setColRoom(int colRoom) {
        this.colRoom = colRoom;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    //==================================================================================================================



    //================================================ KONSTRUKTORY ====================================================
    public Room(int rowRoom, int colRoom, Player player){
        this.rowRoom = rowRoom;
        this.colRoom = colRoom;
        enter = false;
        exit = false;
        pathSet = new int[4][2];
        difficulty = 0;
        randomEvent(player);
    }

    //==================================================================================================================



    //================================================== METODY KLASY ==================================================
    public boolean eventLoop(Player player){
        do
            this.event = this.event.event(player);
        while(event != null);
        if(!(player.getHealth() > 0)) {
            System.out.println("Przegrałeś");
            player.death();
            return false;
        }
        else
            return true;
    }
    public void randomEvent(Player player){
        Random generate = new Random();
        switch(generate.nextInt(3)){
            case 0:
                this.event = new Fight();
                break;
            case 1:
                this.event = new Loot();
                break;
            case 2:
                this.event = new EmptyRoom();
                break;
            default:
                System.out.println("Błąd przy losowaniu eventu!");
                break;
        }
    }

    //==================================================================================================================
}
