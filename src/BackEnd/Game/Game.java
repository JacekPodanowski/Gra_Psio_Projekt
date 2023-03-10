package BackEnd.Game;

import BackEnd.Chararcter.Item.Armor;
import BackEnd.Chararcter.Item.Item;
import BackEnd.Chararcter.Item.Potion;
import BackEnd.Chararcter.Item.Weapon;
import BackEnd.Chararcter.Player;
import Observers.Observer;
import GUI.View.MainWindow;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Game implements Serializable {
    //============================================ ATRYBUTY KLASY ======================================================
    private int day;
    private Map map;
    private Player player;
    private int startY;
    private int startX;
    private int mapSize = 5;// rozmiar mapy
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private boolean userWantToAddItem;
    private String text;
    public ArrayList<Item> basicItems; //?
    public static ArrayList<Weapon> basicWepons = new ArrayList<>();
    public static ArrayList<Armor> basicArmors = new ArrayList<>();
    public static ArrayList<Potion> basicPotions = new ArrayList<>();
    private boolean locationChanged = false;

    private boolean gameFinished = false;

    //==================================================================================================================


    //============================================= KONSTRUKTORY =======================================================
    public Game() {
        this.day = 0;
        player = new Player(mapSize);
        map = new Map(this.player, mapSize);
        map.displayMapFloor(1);
        System.out.println("\nRozpocząłeś nową grę!\n\n");
    }

    public Game(int day, Player player, Map map) {
        //public Game(int day, Player player, Map map){
        this.day = day;
        this.player = player;
        this.map = map;

    }
    //==================================================================================================================


    //========================================= SETTERY I GETTERY ======================================================


    public boolean isUserWantToAddItem() {
        return userWantToAddItem;
    }

    public void setUserWantToAddItem(boolean userWantToAddItem) {
        this.userWantToAddItem = userWantToAddItem;
    }
    public Map getMap() {
        return map;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public ArrayList<Observer> getObservers() {
        return observers;
    }
    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public boolean isLocationChanged() {
        return locationChanged;
    }

    public void setLocationChanged(boolean locationChanged) {
        this.locationChanged = locationChanged;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void finishGame() {
        this.gameFinished = true;
    }

    //==================================================================================================================


    //============================================= METODY KLASY =======================================================
    public void restartGame() {
        this.map = null; //POWINNO LOSOWAĆ NOWĄ MAPĘ
        this.day = 0;
    }

    public void saveGame(String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));) {
            outputStream.writeObject(this);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znalezione Pliku");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Coś się schrzaniło podczas zapisu" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Game loadGame(String filePath) {

        Game game = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
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
        return game;
    }

    public static int askForChoice() {
        int choice;
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                if ((choice = input.nextInt()) >= 1 && choice <= 4) {
                    break;
                } else
                    System.out.println("Niepoprawny wybór! Wybierz spośród 1-4.");
            } catch (InputMismatchException eeee) {
                System.out.println("Podaj liczbę!");
            }
        }
        return choice;
    }

    public static int askForChoice(int limit) {
        int choice;
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                if ((choice = input.nextInt()) >= 1 && choice <= limit) {
                    break;
                } else
                    System.out.println("Niepoprawny wybór! Wybierz spośród 1 - " + limit);
            } catch (InputMismatchException eeee) {
                System.out.println("Podaj liczbę!");
            }
        }
        return choice;
    }

    public static void generateItems(){
        java.util.Map qualityTab =makeQualityTab();

        //Bron Archer
        Weapon weapon0 = new Weapon("Proca", 3, "Zwykły", 'A', 20, 6,1);
        Weapon weapon1 = new Weapon("Łuk", 5, "Zwykły", 'A', 30, 8,2);
        Weapon weapon2 = new Weapon("Długi Łuk", 7, "Zwykły", 'A', 35, 11,3);
        Weapon weapon3 = new Weapon("Harpun", 9, "Zwykły", 'A', 40, 13,4);
        Weapon weapon4 = new Weapon("Ciężka Kusza", 12, "Zwykły", 'A', 50, 20,5);

        //Bron Mage
        Weapon weapon5 = new Weapon("Szklana Kula", 3, "Zwykły", 'I', 20, 5,1);
        Weapon weapon6 = new Weapon("Różdżka losu", 5, "Zwykły", 'I', 30, 8,2);
        Weapon weapon7 = new Weapon("Kryształowy Kostur", 10, "Zwykły", 'I', 35, 11,3);
        Weapon weapon8 = new Weapon("Korzeń Zła", 12, "Zwykły", 'I', 40, 13,4);
        Weapon weapon9 = new Weapon("Kij Św.Teresy", 20, "Zwykły", 'I', 65, 25,5);

        //Bron Warrior
        Weapon weapon10 = new Weapon("Miecz Treningowy", 3, "Zwykły", 'S', 20, 4,1);
        Weapon weapon11 = new Weapon("Tasak", 5, "Zwykły", 'S', 30, 8,2);
        Weapon weapon12 = new Weapon("Siekiera", 10, "Zwykły", 'S', 35, 10,3);
        Weapon weapon13 = new Weapon("Długi Miecz", 15, "Zwykły", 'S', 45, 16,4);
        Weapon weapon14 = new Weapon("Spartańska Dzida", 25, "Zwykły", 'S', 55, 23,5);

        basicWepons.add(weapon0);
        basicWepons.add(weapon1);
        basicWepons.add(weapon2);
        basicWepons.add(weapon3);
        basicWepons.add(weapon4);
        basicWepons.add(weapon5);
        basicWepons.add(weapon6);
        basicWepons.add(weapon7);
        basicWepons.add(weapon8);
        basicWepons.add(weapon9);
        basicWepons.add(weapon10);
        basicWepons.add(weapon11);
        basicWepons.add(weapon12);
        basicWepons.add(weapon13);
        basicWepons.add(weapon14);

//        for (int i = 0; i < basicWepons.size(); i++) {
//            for (int j = 0; j < 8; j++) {
//                allItems.add(new Weapon(basicWepons.get(i).getName(), basicWepons.get(i).getValue(), (String) qualityTab.keySet().toArray()[j], basicWepons.get(i).getType(), basicWepons.get(i).getRequirement(), basicWepons.get(i).getBasicDMG()));
//            }
//        }

        //Armor na Archer
        Armor armor0 = new Armor("Szmata", 1, "Zwykły", 10, 0, 5,1);
        Armor armor1 = new Armor("Narzuta", 3, "Zwykły", 3, 6, 9,2);
        Armor armor2 = new Armor("Jesionka", 5, "Zwykły", 6, 9, 12,3);
        Armor armor3 = new Armor("Kurtka", 10, "Zwykły", 2, 8, 9,4);
        Armor armor4 = new Armor("Mundur Zwiadowcy", 20, "Zwykły", 25, 20, 50,5);

        //Armor na Mage
        Armor armor5 = new Armor("Peleryna", 5, "Zwykły", 10, 20, 10,1);
        Armor armor6 = new Armor("Tunika", 3, "Zwykły", 15, 15, 15,2);
        Armor armor7 = new Armor("Przeszywalnica", 6, "Zwykły", 25, 20, 25,3);
        Armor armor8 = new Armor("Magiczny Koc", 10, "Zwykły", 10, 50, 25,4);
        Armor armor9 = new Armor("Naładowana Sutanna", 20, "Zwykły", 20, 80, 30,5);

        //Armor na Warrior
        Armor armor10 = new Armor("Beczka", 1, "Zwykły", 30, 0, 5,1);
        Armor armor11 = new Armor("Skórzana Szata", 7, "Zwykły", 30, 10, 20,2);
        Armor armor12 = new Armor("Żółwia skorupa", 10, "Zwykły", 85, 10, 10,3);
        Armor armor13 = new Armor("Kolczuga", 15, "Zwykły", 40, 10, 20,4);
        Armor armor14 = new Armor("Rycerska Zbroja", 25, "Zwykły", 60, 20, 30,5);

        basicArmors.add(armor0);
        basicArmors.add(armor1);
        basicArmors.add(armor2);
        basicArmors.add(armor3);
        basicArmors.add(armor4);
        basicArmors.add(armor5);
        basicArmors.add(armor6);
        basicArmors.add(armor7);
        basicArmors.add(armor8);
        basicArmors.add(armor9);
        basicArmors.add(armor10);
        basicArmors.add(armor11);
        basicArmors.add(armor12);
        basicArmors.add(armor13);
        basicArmors.add(armor14);

        Potion P1 = new Potion("Woda",1,"Zwykły",10);
        Potion P2 = new Potion("Wino",5,"Zwykły",20);
        Potion P3 = new Potion("Niebieski Grzyb",1,"Zwykły",-10);
        Potion P4 = new Potion("Czerwony Grzyb",1,"Zwykły",50);
        Potion P5 = new Potion("Piwo",1,"Zwykły",30);

        basicPotions.add(P1);
        basicPotions.add(P2);
        basicPotions.add(P3);
        basicPotions.add(P4);
        basicPotions.add(P5);

//        for (int i = 0; i < basicArmors.size(); i++) {
//            for (int j = 0; j < 8; j++) {
//                allItems.add(new Armor(basicArmors.get(i).getName(), basicArmors.get(i).getValue(), (String) qualityTab.keySet().toArray()[j], basicArmors.get(i).getStrengthProtection(), basicArmors.get(i).getMagicProtection(), basicArmors.get(i).getAgilityProtection()));
//            }
//        }


        //System.out.println(allItems);
    }

    public static java.util.Map makeQualityTab() {
        java.util.Map<String, Double> qualityTab = new TreeMap<>();
        qualityTab.put("Zdewastowany", 0.25);
        qualityTab.put("Lichy", 0.5);
        qualityTab.put("Stary", 0.75);
        qualityTab.put("Zwykły", 1.0);
        qualityTab.put("Solidny", 1.25);
        qualityTab.put("Dorodny", 1.5);
        qualityTab.put("Perfekcyjny", 1.75);
        qualityTab.put("Mityczny", 2.0);
        return qualityTab;
    }
    public  static Item generateItem() {
        generateItems();
        Random R = new Random();
        switch (R.nextInt(3)) {
            case 0:
                Weapon B= basicWepons.get(R.nextInt(basicWepons.size()));
                return new Weapon(B.getName(), B.getValue(),B.getType(),B.getRequirement(),B.getBasicDMG(),B.getLvl());
            case 1:
                Armor B1= basicArmors.get(R.nextInt(basicArmors.size()));
                return new Armor(B1.getName(), B1.getValue(),B1.getStrengthProtection(), B1.getMagicProtection(), B1.getAgilityProtection(),B1.getLvl());
            case 2:
                Potion B2= basicPotions.get(R.nextInt(basicPotions.size()));
                return new Potion(B2.getName(), B2.getValue(),B2.getHealing());
        }
        return new Weapon();
    }


    public  static Item generateItem(char choice) {
        generateItems();
        Random R = new Random();
        switch (choice) {
            case 'W':
                Weapon B= basicWepons.get(R.nextInt(basicWepons.size()));
                return new Weapon(B.getName(), B.getValue(),B.getType(),B.getRequirement(),B.getBasicDMG(),B.getLvl());
            case 'A':
                Armor B1= basicArmors.get(R.nextInt(basicArmors.size()));
                return new Armor(B1.getName(), B1.getValue(),B1.getStrengthProtection(), B1.getMagicProtection(), B1.getAgilityProtection(),B1.getLvl());
            case 'P':
                Potion B2= basicPotions.get(R.nextInt(basicPotions.size()));
                return new Potion(B2.getName(), B2.getValue(),B2.getHealing());
        }
        return new Weapon();
    }

    public  static Item generateItem(char choice,int lvl) {
        generateItems();
        Random R = new Random();
        switch (choice) {
            case 'W':
                Weapon B= basicWepons.get(R.nextInt(basicWepons.size()));
                while (B.getLvl()>lvl){
                    B= basicWepons.get(R.nextInt(basicWepons.size()));
                }
                Weapon W = new Weapon(B.getName(), B.getValue(),B.getType(),B.getRequirement(),B.getBasicDMG(),B.getLvl());
                return W;
            case 'A':
                Armor B1= basicArmors.get(R.nextInt(basicArmors.size()));
                while (B1.getLvl()>lvl){
                    B1= basicArmors.get(R.nextInt(basicArmors.size()));
                }
                Armor A = new Armor(B1.getName(), B1.getValue(),B1.getStrengthProtection(), B1.getMagicProtection(), B1.getAgilityProtection(),B1.getLvl());
                return A;
            case 'P':
                Potion B2= basicPotions.get(R.nextInt(basicPotions.size()));
                Potion P = new Potion(B2.getName(), B2.getValue(),B2.getHealing());
                return P;
        }
        return new Weapon();
    }

    public int generateRestEvent(){

        map.getTabOfRoom()[player.getLocation_X()][player.getLocation_Y()].setRested(true);
        int anwser;
        Random random = new Random();
        anwser = random.nextInt(6);
        switch (anwser){
            case 0 :
                player.healMissingHealth(5);
                break;
            case 1 :
                player.healMissingHealth(10);
                break;
            case 2 :
                player.setHealth(player.getHealth()-15);
                break;
            case 3 :
                break;
            case 4 :
                player.setInventory(new Item[5]);
                player.healMissingHealth(10);
                break;
            case 5 :
                player.setWeapon(new Weapon());
                player.setArmor(new Armor());
                break;
        }

        return anwser;
    }



    //==================================================================================================================
}

