package Game;

import Game.Event.Event;
import Game.Event.Fight;

import java.util.Random;

public class Room {
    private boolean enter;
    private boolean exit;
    private int rowRoom;
    private int colRoom;
    private int [][] pathSet;
    private int difficulty;  // + lub - od lewela gracza
    private Event event;


    public boolean getEnter() {
        return enter;
    }
    public boolean getExit() {
        return exit;
    }
    public Room(int rowRoom, int colRoom){
        this.rowRoom = rowRoom;
        this.colRoom = colRoom;
        enter = false;
        exit = false;
        pathSet = new int[4][2];
        difficulty = 0;
        RandomEvent();
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

    public void RandomEvent(){
        Random generate = new Random();
        switch(generate.nextInt(5)){
            case 1:
                this.event = new Fight();
                break;
            case 2:
                //this.event = new EmptyRoom();
                break;
            default:
                System.out.println("Błąd przy losowaniu eventu!");
                break;
        }
    }

}
