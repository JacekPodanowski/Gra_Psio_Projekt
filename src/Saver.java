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
            slots = loadSlots();
        }else {

        }
        slots = new LocalDateTime[numberOfSlots];

    }


    public void save(Game game, int slotNumber){

        game.saveGame("SaveGame"+slotNumber+".bin");
        slots[slotNumber -1] = LocalDateTime.now();
        saveSlots();
    }

    public Game load(int slotIndex){

        return Game.loadGame("SaveGame"+slotIndex+".bin");
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
            outputStream.writeObject(slots);
        }catch (FileNotFoundException e){
            System.out.println("Nie znalezione Pliku");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Coś się schrzaniło podczas zapisu" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public int getNumberOfSlots(){
        return  numberOfSlots;
    }

    public String getTextForSlot(int index){
        if( slots[index] == null){
            return "puste";
        } else {
            return slots[index].toString();
        }
    }

}
