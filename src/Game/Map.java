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
    }




    public static void GenerateMap() {
        Map map = new Map();
        Random random = new Random();

        // przypisanie pokojow do tablicy mapy
        Room room00 = new Room(0,0);
        map.TabOfRoom[0][0] = room00;
        Room room01 = new Room(0,1);
        map.TabOfRoom[0][1] = room01;
        Room room02 = new Room(0,2);
        map.TabOfRoom[0][2] = room02;
        Room room03 = new Room(0,3);
        map.TabOfRoom[0][3] = room03;
        Room room04 = new Room(0,4);
        map.TabOfRoom[0][4] = room04;

        Room room10 = new Room(1,0);
        map.TabOfRoom[1][0] = room10;
        Room room11 = new Room(1,1);
        map.TabOfRoom[1][1] = room11;
        Room room12 = new Room(1,2);
        map.TabOfRoom[1][2] = room12;
        Room room13 = new Room(1,3);
        map.TabOfRoom[1][3] = room13;
        Room room14 = new Room(1,4);
        map.TabOfRoom[1][4] = room14;

        Room room20 = new Room(2,0);
        map.TabOfRoom[2][0] = room20;
        Room room21 = new Room(2,1);
        map.TabOfRoom[2][1] = room21;
        Room room22 = new Room(2,2);
        map.TabOfRoom[2][2] = room22;
        Room room23 = new Room(2,3);
        map.TabOfRoom[2][3] = room23;
        Room room24 = new Room(2,4);
        map.TabOfRoom[2][4] = room24;

        Room room30 = new Room(3,0);
        map.TabOfRoom[3][0] = room30;
        Room room31 = new Room(3,1);
        map.TabOfRoom[3][1] = room31;
        Room room32 = new Room(3,2);
        map.TabOfRoom[3][2] = room32;
        Room room33 = new Room(3,3);
        map.TabOfRoom[3][3] = room33;
        Room room34 = new Room(3,4);
        map.TabOfRoom[3][4] = room34;

        Room room40 = new Room(4,0);
        map.TabOfRoom[4][0] = room40;
        Room room41 = new Room(4,1);
        map.TabOfRoom[4][1] = room41;
        Room room42 = new Room(4,2);
        map.TabOfRoom[4][2] = room42;
        Room room43 = new Room(4,3);
        map.TabOfRoom[4][3] = room43;
        Room room44 = new Room(4,4);
        map.TabOfRoom[4][4] = room44;


        // losujemy jedno wejście i jedno wyjście
        // wejscie i wyjscie zaklada sie ze bedzie na granicy mapy, czyli na indeksie tablicy 0 lub 4



        map.TabOfRoom[4*random.nextInt(2)][4*random.nextInt(2)].setEnter(true);
        {
            int rowTemp = random.nextInt(5);
            // if
            int colTemp = 4*random.nextInt(2);
            while (!map.TabOfRoom[rowTemp][colTemp].getEnter() || map.TabOfRoom[rowTemp][colTemp].getExit()){}

        }

        for (int i = 0; i < map.TabOfRoom.length; i++) {
            for (int j = 0; j < map.TabOfRoom[i].length; j++) {

            }

        }





    }
}
