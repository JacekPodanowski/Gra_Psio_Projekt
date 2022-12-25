package Game;
import java.util.Random;

public class Map {
    private Room[][] TabOfRoom;  // tablica z pokojow 5x5 (na razie)

    public Room[][] getTabOfRoom() {
        return TabOfRoom;
    }

    public void setTabOfRoom(Room[][] tabOfRoom) {
        this.TabOfRoom = tabOfRoom;
    }

    public Map() {
        TabOfRoom = new Room[5][5];
        generateMap();
    }

    public Map(int row, int col) {
        TabOfRoom = new Room[row][col];
        generateMap();
    }

    public void generateMap() {
        Random random = new Random();

        // przypisanie pokojow do tablicy mapy
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.TabOfRoom[i][j] = new Room(i, j);
            }
        }

        // losujemy jedno wejście i jedno wyjście
        // wyjscie zaklada sie ze bedzie na granicy mapy, czyli na indeksie tablicy 0 lub 4
        //wejscie dam na [0,0] na razie dla testow

        int x = 4 * random.nextInt(2);
        int y = 4 * random.nextInt(2);
        this.TabOfRoom[x][y].setExit(true);
        this.TabOfRoom[0][0].setEnter(true);
        System.out.println("    Nota techniczna - Wspolzednde wyjscia to : [" + x + "," + y + "]");
        //this.TabOfRoom[4*random.nextInt(2)][4*random.nextInt(2)].setEnter(true);

        //nwm do czego to sluzy
        /*/
        {
            int rowTemp = random.nextInt(5);
            // if
            int colTemp = 4*random.nextInt(2);
            while (!this.TabOfRoom[rowTemp][colTemp].getEnter() || this.TabOfRoom[rowTemp][colTemp].getExit()){}

        }

        for (int i = 0; i < this.TabOfRoom.length; i++) {
            for (int j = 0; j < this.TabOfRoom[i].length; j++) {

            }

        }
        /*/
    }

    public void displayMapFloor(int pientro) {

        for (int i = 0; i < TabOfRoom.length; i++) {
            for (int j = 0; j < TabOfRoom[0].length; j++) {
                System.out.print("[" + i + "," + j + "]" + TabOfRoom[i][j].getEvent().toString()+", ");
            }
            System.out.println();
        }
    }
}
