package Game;

import Chararcter.Item.*;
import Chararcter.Player;
import Observable.Subject;
import Observers.Observer;
import Observers.PlayerOnMapPosition;
import View.MainWindow;

import java.io.*;
import java.util.*;

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

    private String text;
    private int choice;
    public ArrayList<Item> basicItems;
    public ArrayList<Weapon> basicWepons=new ArrayList<>();
    public ArrayList<Armor> basicArmors=new ArrayList<>();
    public ArrayList<Potion> basicPotions=new ArrayList<>();
    public ArrayList<Item> allItems=new ArrayList<>();

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

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public void startGame(){
       
        notifyObservers();
        generateItems();
        text = "Aby wejść do kostki wpisz 1.";
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
/*   NIE DZIAŁA
    public int askForChoiceTextField(){
        while(!mainWindow.isAnswer()){}
        String anwser = mainWindow.gAnswerChar()+"";
        return Integer.parseInt(anwser);
    }
*/
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

    @Override
    public void update(Game game) {

    }

    public void generateItems(){
        java.util.Map qualityTab =makeQualityTab();

        //Bron Archer
        Weapon weapon0 =new Weapon("Proca", 3,"Zwykły", 'A', 20, 6);
        Weapon weapon1 =new Weapon("Łuk", 5, "Zwykły", 'A', 30, 8);
        Weapon weapon2 =new Weapon("Długi Łuk", 7, "Zwykły", 'A', 35, 11);
        Weapon weapon3 =new Weapon("Harpun", 9, "Zwykły", 'A', 40, 13);
        Weapon weapon4 =new Weapon("Ciężka Kusza", 12, "Zwykły", 'A', 50, 20);

        //Bron Mage
        Weapon weapon5 =new Weapon("Szklana Kula", 3, "Zwykły", 'I', 20, 5);
        Weapon weapon6 =new Weapon("Różdżka losu", 5, "Zwykły", 'I', 30, 8);
        Weapon weapon7 =new Weapon("Kryształowy Kostur", 10, "Zwykły", 'I', 35, 11);
        Weapon weapon8 =new Weapon("Korzeń Zła", 12, "Zwykły", 'I', 40, 13);
        Weapon weapon9 =new Weapon("Laska Św.Teresy", 20, "Zwykły", 'I', 65, 25);

        //Bron Warrior
        Weapon weapon10 =new Weapon("Miecz Treningowy", 3, "Zwykły", 'S', 20, 4);
        Weapon weapon11 =new Weapon("Tasak", 5, "Zwykły", 'S', 30, 8);
        Weapon weapon12 =new Weapon("Siekiera", 10, "Zwykły", 'S', 35, 10);
        Weapon weapon13 =new Weapon("Długi Miecz", 15, "Zwykły", 'S', 45, 16);
        Weapon weapon14 =new Weapon("Spartańska Dzida", 25, "Zwykły", 'S', 55, 23);

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

        for (int i = 0; i < basicWepons.size(); i++) {
            for (int j = 0; j < 8; j++) {
                allItems.add(new Weapon(basicWepons.get(i).getName(), basicWepons.get(i).getValue(), (String) qualityTab.keySet().toArray()[j],basicWepons.get(i).getType(),basicWepons.get(i).getRequirement(),basicWepons.get(i).getBasicDMG()));
            }
        }

        //Armor na Archer
        Armor armor0 =new Armor("Szmata", 1,"Zwykły",10, 0, 5);
        Armor armor1 =new Armor("Narzuta", 3,"Zwykły",3, 6, 9);
        Armor armor2 =new Armor("Jesionka", 5,"Zwykły",6, 9, 12);
        Armor armor3 =new Armor("Kurtka", 10,"Zwykły",2, 8, 9);
        Armor armor4 =new Armor("Mundur Zwiadowcy", 20,"Zwykły",25, 20, 50);

        //Armor na Mage
        Armor armor5 =new Armor("Peleryna", 5,"Zwykły",10, 20, 10);
        Armor armor6 =new Armor("Tunika", 3,"Zwykły",15, 15, 15);
        Armor armor7 =new Armor("Przeszywalnica", 6,"Zwykły",25, 20, 25);
        Armor armor8 =new Armor("Magiczny Koc", 10,"Zwykły",10, 50, 25);
        Armor armor9 =new Armor("Naładowana Sutanna", 20,"Zwykły",20, 80, 30);

        //Armor na Warrior
        Armor armor10 =new Armor("Beczka", 1,"Zwykły",30, 0, 5);
        Armor armor11 =new Armor("Skórzana Szata", 7,"Zwykły",30, 10, 20);
        Armor armor12 =new Armor("Żółwia skorupa", 10,"Zwykły",85, 10, 10);
        Armor armor13 =new Armor("Kolczuga", 15,"Zwykły",40, 10, 20);
        Armor armor14 =new Armor("Rycerska Zbroja", 25,"Zwykły",60, 20, 30);

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

        for (int i = 0; i < basicArmors.size(); i++) {
            for (int j = 0; j < 8; j++) {
                allItems.add(new Armor(basicArmors.get(i).getName(),basicArmors.get(i).getValue(),(String) qualityTab.keySet().toArray()[j],basicArmors.get(i).getStrengthProtection(),basicArmors.get(i).getMagicProtection(),basicArmors.get(i).getAgilityProtection()));
            }
        }


        //System.out.println(allItems);
    }
    public java.util.Map makeQualityTab(){
        java.util.Map<String, Double> qualityTab = new TreeMap<>();
        qualityTab.put("Zdewastowany",0.25);
        qualityTab.put("Lichy",0.5);
        qualityTab.put("Stary",0.75);
        qualityTab.put("Zwykły",1.0);
        qualityTab.put("Solidny",1.25);
        qualityTab.put("Dorodny",1.5);
        qualityTab.put("Perfekcyjny",1.75);
        qualityTab.put("Mityczny",2.0);
        return qualityTab;
    }
}
