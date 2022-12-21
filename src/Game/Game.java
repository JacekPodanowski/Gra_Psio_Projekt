package Game;

import Chararcter.Player;

import java.io.*;

public class Game {
    private int day;
    private Map map;
    private Player player;

    public Game(){
        this.day = 0;
    }

    public Game(int day, Map map){
        this.day = day;
        this.map = map;
    }

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
}
