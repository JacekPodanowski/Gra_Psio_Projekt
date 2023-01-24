package BackEnd.Game;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SaveLoadHelper {
    private final int numberOfSlots = 4;
    private LocalDate[] slots;
    private final String filePath = "save.bin";

    public SaveLoadHelper(){
        File file = new File(filePath);
        if (file.exists()){
            slots = loadSlots();
        }else {
            slots = new LocalDate[numberOfSlots];
        }
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public String getTextForSlot(int index)
    {
        if(slots[index] ==null){
            return "puste";
        } else{
            return slots[index].toString();
        }
    }
    public boolean isEmptySlot(int index)
    {
        return slots[index]==null;
    }

    public void save(Game game, int slotIndex){
        game.saveGame("SaveGame"+slotIndex+".bin");
        slots[slotIndex] = LocalDate.now();
        saveSlots();
    }

    public Game load(int slotIndex){
        return Game.loadGame("SaveGame"+slotIndex+".bin");
    }

    private LocalDate[] loadSlots(){
        LocalDate[] slots = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))){
            slots = (LocalDate[]) inputStream.readObject();
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



}
