package Game;

import Chararcter.Player;
import Observable.Subject;
import Observers.Observer;
import Observers.PlayerOnMapPosition;
import View.MainWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game implements Serializable, Subject{
    //============================================ ATRYBUTY KLASY ======================================================
    private int day;
    private Map map;
    private Player player;
    private int startY;
    private int startX;
    private int mapSize =5;// rozmiar mapy
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private MainWindow mainWindow;


    //==================================================================================================================



    //============================================= KONSTRUKTORY =======================================================
    public Game(){
        this.day = 0;
        player = new Player(mapSize);
        map = new Map(this.player,mapSize);
        map.displayMapFloor(1);
        this.registerObserver(new PlayerOnMapPosition());
        System.out.println("\nRozpocząłeś nową grę!\n\n");
        //this.startGame();
    }
    public Game(int day, Player player,Map map){
        this.day = day;
        this.player = player;
        this.map = map;
    }
    //==================================================================================================================



    //========================================= SETTERY I GETTERY ======================================================
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Map getMapa() {
        return map;
    }

    public void setMapa(Map mapa) {
        this.map = mapa;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }
    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public void startGame(){
        System.out.println("Aby wejść do kostki wpisz 1");
        int wybor = askForChoice();
        if(wybor == 1)
            while(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].eventLoop(player)) {
                this.notifyObservers();
                for (int i = 0; i < this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().size(); i++) {
                    System.out.print((i+1)+" - ");
                    System.out.println(Arrays.toString(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().get(i)));
                }
                System.out.println("Gdzie chesz iść? ");
                int choice = Game.askForChoice(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().size());
                int x = player.getLocation_X();
                player.setLocation_X(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().get(choice-1)[0]);
                player.setLocation_Y(this.map.getTabOfRoom()[x][player.getLocation_Y()].getPathSet().get(choice-1)[1]);
            }
        System.out.print("Nie zyjesz");
        System.exit(5);
    }

    public void restartGame(){
        this.map = null; //POWINNO LOSOWAĆ NOWĄ MAPĘ
        this.day = 0;
    }
    public void saveGame(String filePath){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));) {
            outputStream.writeObject(this);
        }catch (FileNotFoundException e){
            System.out.println("Nie znalezione Pliku");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Coś się schrzaniło podczas zapisu" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Game loadGame(String filePath){

        Game game = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))){
            game = (Game) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nie odnaleziono pliku");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Podana klasa jest niepoprawna");
            throw new RuntimeException(e);
        }
        return game;}

    public static int askForChoice(){
        int choice;
        while(true) {
            Scanner input = new Scanner(System.in);
            try {
                if ((choice = input.nextInt()) >= 1 && choice <= 4) {
                    break;
                }
                else
                    System.out.println("Niepoprawny wybór! Wybierz spośród 1-4.");
            }catch(InputMismatchException eeee){
                System.out.println("Podaj liczbę!");
            }
        }
        return choice;
    }

    public static int askForChoice(int limit){
        int choice;
        while(true) {
            Scanner input = new Scanner(System.in);
            try {
                if ((choice = input.nextInt()) >= 1 && choice <= limit) {
                    break;
                }
                else
                    System.out.println("Niepoprawny wybór! Wybierz spośród 1 - " + limit);
            }catch(InputMismatchException eeee){
                System.out.println("Podaj liczbę!");
            }
        }
        return choice;
    }

    public int askForChoiceTextField(){
        while(!mainWindow.isAnswer()){}
        String anwser = mainWindow.gAnswerChar()+"";
        return Integer.parseInt(anwser);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).update(this);
    }
    //==================================================================================================================
}
