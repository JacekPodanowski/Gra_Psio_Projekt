package Game;
import java.util.Random;

public class Map {
    private Room [][] TabOfRoom;  // tablica z pokojow 5x5 (na razie)

    public Room [][] getTabOfRoom() {
        return TabOfRoom;
    }
    public void setTabOfRoom(Room [][] tabOfRoom) {
        this.TabOfRoom = tabOfRoom;
    }

    public Map() {
        TabOfRoom = new Room[5][5];
        generateMap();
    }
    public Map(int row, int col){
        TabOfRoom = new Room[row][col];
        generateMap();
    }

    public void generateMap() {
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.TabOfRoom[i][j] = new Room(i,j);// przypisanie pokojow do tablicy mapy
            }
        }
        // losujemy jedno wejście i jedno wyjście
        // wejscie i wyjscie zaklada sie ze bedzie na granicy mapy, czyli na indeksie tablicy 0 lub 4



        this.TabOfRoom[4*random.nextInt(2)][4*random.nextInt(2)].setEnter(true);
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





    }
}
