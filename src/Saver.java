import Game.Game;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Saver {

    private final int numberOfSlots = 4;
    private LocalDateTime[] slots;
    private final String filePath = "save.bin";


    public Saver(){
        File file = new File(filePath);

        if (file.exists()){

        }else {

        }
        slots = new LocalDateTime[numberOfSlots];

    }

    public void print(){
        for (int i=0; i<slots.length; i++){
            System.out.println((i+1)+ " ");
            if(slots ==null){
                System.out.println(" - brak");
            } else{
                System.out.println(slots[i]);
            }
        }
    }

    public void save(Game game){
        System.out.println("Zapisz Grę");
        print();
        Scanner scanner = new Scanner(System.in);
        System.out.print("numer: ");

        int number = scanner.nextInt();
        game.saveGame("SaveGame"+number+".bin");
        slots[number-1] = LocalDateTime.now();
        saveSlots();
    }

    public Game load(){
        System.out.println("Odczytaj Grę");
        print();
        Scanner scanner = new Scanner(System.in);
        System.out.print("numer: ");

        int number = scanner.nextInt();
        return Game.loadGame("SaveGame"+number+".bin");
    }

    private LocalDateTime[] loadSlots(){

        LocalDateTime[] slots = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))){
            slots = (LocalDateTime[]) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Nie odnaleziono pliku");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Podana klasa jest niepoprawna");
            throw new RuntimeException(e);
        }
    return slots;}


    private void saveSlots(){
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



}
