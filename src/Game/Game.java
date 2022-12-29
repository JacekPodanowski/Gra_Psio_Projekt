package Game;

import Chararcter.Player;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    //============================================ ATRYBUTY KLASY ======================================================
    private int day;
    private Map map;
    private Player player;
    private int startY;
    private int startX;
    private int mapSize =5;// rozmiar mapy

    //==================================================================================================================



    //============================================= KONSTRUKTORY =======================================================
    public Game(){
        this.day = 0;
        player = new Player(mapSize);
        map = new Map(this.player,mapSize);
        map.displayMapFloor(1);
        System.out.println("\nRozpocząłeś nową grę!\n\n");
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

    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public void startGame(){
        System.out.println("Aby wejść do kostki wpisz 1");
        int wybor = askForChoice();
        if(wybor == 1)
            while(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].eventLoop(player)) {
                for (int i = 0; i < this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().size(); i++) {
                    System.out.print((i+1)+" - ");
                    System.out.print("[");
                    System.out.print(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().get(i)[0]);
                    System.out.print(", ");
                    System.out.print(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().get(i)[1]);
                    System.out.println("]");
                }
                System.out.println("Gdzie chesz iść ? ");
                int choice = Game.askForChoice(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().size());
                player.setLocation_X(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().get(choice-1)[0]);
                player.setLocation_Y(this.map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].getPathSet().get(choice-1)[1]);
                }
        System.out.printf("Nie zyjesz");
        System.exit(5);
    }
    public void restartGame(){
        this.map = null; //POWINNO LOSOWAĆ NOWĄ MAPĘ
        this.day = 0;
    }
    public void saveGame(String nazwaSave){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nazwaSave));
            writer.write("day" + '\t' + this.day);
            writer.newLine();
            writer.write("map" + '\t'); // POWINNO ZAPISAĆ CAŁĄ MAPĘ, JESZCZE NIE WIEM JAK, RACZEJ PRZEZ ZAPISANIE POSZCZEGÓLNYCH ELEMENTÓW MAPY, KAŻDĄ PO NEW LINE
            //+Zapis postaci
            writer.close();
        }catch(IOException e){
            System.out.println("Failed to save the progress." + e.getMessage());
        }
    }
    public void loadGame(String nazwaSave){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nazwaSave));
            this.day = Integer.parseInt(reader.readLine().split("\t")[1]);
            //this.mapa.costam = reader.read(); - ma wczytac dane mapy zapisane wczesniej w saveGame
            reader.close();
        }catch(IOException e){
            System.out.println("Nie udało się wczytać gry." + e.getMessage());
        }
    }
    public static int askForChoice(){
        int choice;
        while(true) {
            Scanner input = new Scanner(System.in);
            try {
                if ((choice = input.nextInt()) >= 1 && choice <= 4)
                    break;
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
                if ((choice = input.nextInt()) >= 1 && choice <= limit)
                    break;
                else
                    System.out.println("Niepoprawny wybór! Wybierz spośród 1 - " + limit);
            }catch(InputMismatchException eeee){
                System.out.println("Podaj liczbę!");
            }
        }
        return choice;
    }

    //==================================================================================================================
}
