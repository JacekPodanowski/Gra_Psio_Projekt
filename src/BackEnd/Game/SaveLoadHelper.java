package BackEnd.Game;

import java.io.*;
import java.time.LocalDateTime;

public class SaveLoadHelper {
    private final int numberOfSlots = 4;
    private LocalDateTime[] slots;
    private final String filePath = "save.bin";

    public SaveLoadHelper(){
        File file = new File(filePath);
        if (file.exists()){
            slots = loadSlots();
        }else {
            slots = new LocalDateTime[numberOfSlots];
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
        //game.saveGame("SaveGame"+slotNumber+".bin");
        slots[slotIndex] = LocalDateTime.now();
        saveSlots();
    }

    public Game load(int slotIndex){
        //return BackEnd.Game.loadGame("SaveGame"+slotIndex+".bin");
        return null;
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



}
