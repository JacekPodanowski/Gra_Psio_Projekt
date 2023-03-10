package BackEnd.Game;
import BackEnd.Chararcter.Player;
import BackEnd.Game.Event.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Map {
    //================================================= ATRYBUTY KLASY =================================================
    private Room[][] tabOfRoom;  // tablica z pokojow 5x5 (na razie)
    private RoomType[][] roomTypes;
    private ArrayList<Room> toExitRooms;

    //==================================================================================================================



    //================================================== KONSTRUKTORY ==================================================
    public Map(Player player,int size) {
        tabOfRoom = new Room[size][size];
        toExitRooms = new ArrayList<Room>();
        generateMap(player);
        this.setRoomTypes(1, player);
    }
    //==================================================================================================================



    //============================================= GETTERY I SETTERY ==================================================
    public Room[][] getTabOfRoom() {
        return tabOfRoom;
    }

    public void setTabOfRoom(Room[][] tabOfRoom) {
        this.tabOfRoom = tabOfRoom;
    }

    public RoomType[][] getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(RoomType[][] roomTypes) {
        this.roomTypes = roomTypes;
    }

    public ArrayList<Room> getToExitRooms() {
        return toExitRooms;
    }

    public void setToExitRooms(ArrayList<Room> toExitRooms) {
        this.toExitRooms = toExitRooms;
    }
    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public void generateMap(Player player) {
        Random random = new Random();

        // przypisanie pokojow do tablicy mapy
        for (int i = 0; i < this.tabOfRoom.length; i++) {
            for (int j = 0; j < this.tabOfRoom[i].length; j++) {
                this.tabOfRoom[i][j] = new Room(i,j, player);
            }
        }

        // przelicza i numeruje pokoi w tablicy
        int numRoom = 0;

        for (int i = 0; i < this.tabOfRoom.length; i++) {
            for (int j = 0; j < this.tabOfRoom.length; j++) {
                this.tabOfRoom[i][j].setNumRoom(numRoom);
                numRoom++;
            }
        }

        // lewy dolny r??g zawsze jest wej??ciem. Inicjacja wej??cia
        this.tabOfRoom[this.tabOfRoom.length-1][0].setEnter(true);
        this.tabOfRoom[this.tabOfRoom.length-1][0].setEvent1(RoomEvent.ENTRANCE);
        this.tabOfRoom[this.tabOfRoom.length-1][0].setVisited(true);

        // losujemy wyj??cie i dwa zau??ki na g??rnej lub na prawej granice mapy
        // losowanie wyj??cia
        Room roomTemp = RandRoomOnEdge(this.tabOfRoom);
        int exitNum = roomTemp.getNumRoom();
        this.tabOfRoom[roomTemp.getRowRoom()][roomTemp.getColRoom()].setExit(true);
        this.tabOfRoom[roomTemp.getRowRoom()][roomTemp.getColRoom()].setEvent1(RoomEvent.EXIT);

        // losowanie ??lepego zau??ku nr1
        // je??li wyj??cie jest na g??rnej granicy, to zau??ki s?? na prawej. W przeciwnym przypadku podobnie
        roomTemp = RandRoomOnEdge(this.tabOfRoom, FindRoomByNum(exitNum, this.tabOfRoom));
        int blindEnd1_Num = roomTemp.getNumRoom();
        // je??li si?? powtarza, to losowanie jesczsze raz
        while (blindEnd1_Num == exitNum) {
            roomTemp = RandRoomOnEdge(this.tabOfRoom);
            blindEnd1_Num = roomTemp.getNumRoom();
        }
        FindRoomByNum(blindEnd1_Num,this.tabOfRoom).setEvent(new Up());

        // losowanie ??lepego zau??ku nr2
        roomTemp = RandRoomOnEdge(this.tabOfRoom, FindRoomByNum(exitNum, this.tabOfRoom));
        int blindEnd2_Num = roomTemp.getNumRoom();
        // je??li si?? powtarza, to losowanie jesczsze raz
        while (blindEnd2_Num == exitNum || blindEnd2_Num == blindEnd1_Num) {
            roomTemp = RandRoomOnEdge(this.tabOfRoom);
            blindEnd2_Num = roomTemp.getNumRoom();
        }
        FindRoomByNum(blindEnd2_Num,this.tabOfRoom).setEvent(new Down());


        // Adjacency list dla przechowywania po????cze?? mi??dzy numerami pokoj??w
        // to jest pomocnicza tablica, kt??ra przekazywana do metody odnalezienia najkr??tszej drogi
        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<ArrayList<Integer>>(numRoom);
        for (int i = 0; i < numRoom; i++) {
            adj.add(new ArrayList<Integer>());
        }


        // generacja wszystkich ??cie??ek dla wszystkich pokoi i
        // przypisywanie tych ??cie??ek do Adjacency list
        int MapLength = this.tabOfRoom.length; // pomocnicza zmienna
        //??cie??ki do pokojow s??siednich dla pokoj??w na rogach:
        addEdge(adj, 0, 1);
        addEdge(adj, 0, MapLength);
        addEdge(adj, MapLength-1, MapLength-2);
        addEdge(adj, MapLength-1, 2*MapLength-1);
        addEdge(adj, MapLength*(MapLength-1), MapLength*(MapLength-1)+1);
        addEdge(adj, MapLength*(MapLength-1), MapLength*(MapLength-2));
        addEdge(adj, MapLength*MapLength -1 , MapLength*MapLength-2);
        addEdge(adj, MapLength*MapLength -1,  MapLength*(MapLength-1)-1);

        // generacja ??cie??ek dla lewej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, MapLength*i, MapLength*i+1);
            addEdge(adj, MapLength*i, MapLength*(i-1));
            addEdge(adj, MapLength*i, MapLength*(i+1));
        }
        // generacja ??cie??ek dla prawej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, MapLength*(i+1)-1, MapLength*(i+1)-2);
            addEdge(adj, MapLength*(i+1)-1, MapLength*(i  )-1);
            addEdge(adj, MapLength*(i+1)-1, MapLength*(i+2)-1);
        }
        // generacja ??cie??ek dla g??rnej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, i, i-1);
            addEdge(adj, i, i+1);
            addEdge(adj, i, i+MapLength);
        }
        // generacja ??cie??ek dla dolnej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, MapLength*(MapLength-1)+i, MapLength*(MapLength-1)+i-1);
            addEdge(adj, MapLength*(MapLength-1)+i, MapLength*(MapLength-1)+i+1);
            addEdge(adj, MapLength*(MapLength-1)+i, MapLength*(MapLength-2)+i);
        }

        // generacja ??cie??ek dla ??rodkowej cz????ci tablicy pokoj??w
        for (int i = 1; i < MapLength-1; i++) {
            for (int j = 1; j < MapLength-1; j++) {
                addEdge(adj, MapLength*i+j, MapLength*i+j+1);
                addEdge(adj, MapLength*i+j, MapLength*i+j-1);
                addEdge(adj, MapLength*i+j, MapLength*(i-1)+j);
                addEdge(adj, MapLength*i+j, MapLength*(i+1)+j);
            }
        }


        // source and destination przekazywane do metody, mi??dzy czym b??dzie odnajdywana najkr??tsza droga
        int source = this.tabOfRoom[this.tabOfRoom.length-1][0].getNumRoom();
        int destination = exitNum;

        // wywo??anie metody "findshortestdistance" i przepisanie wynikowej ??cie??ki z LinkedList to ArrayList
        ArrayList<Integer> toExit = new ArrayList<>();
        toExit.addAll(findShortestDistance(adj, source, destination, numRoom));
        Collections.reverse(toExit);

        // skasowanie ??cie??ek przej??cia z pokoju wyj??ciowego dla tego, ??eby zau??ki si?? generowa??y nie przez exit
        adj.get(exitNum).clear();



        // tu szukamy najkr??tszej ??cie??ki z pokoju ??lepego zau??ku do pierwszych czterech pokoi z toExit.
        // Dla ??atwiejszego zrozumienia kodu nie wyrzucam powielanie na zewn??trz w oddzieln?? funkcj?? (Dla ??lepego zau??ka 1 i 2)

        source = blindEnd1_Num;


        ArrayList<Integer> toBlind1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            destination = toExit.get(i);

            LinkedList<Integer> pathRoom = findShortestDistance(adj, source, destination, numRoom);
            // sprawdzamy czy ??cie??ka kr??tsza, ni?? poprzednio. Je??li tak, to przepisujemy na kr??tsz??
            if (toBlind1.size() == 0 || pathRoom.size()<toBlind1.size()) {
                toBlind1.clear();
                toBlind1.addAll(pathRoom);
            }
            pathRoom.clear();
        }

        // tu szukamy najkr??tszej ??cie??ki z pokoju ??lepego zau??ku nr2 do ??cie??ki toExit
        source = blindEnd2_Num;

        ArrayList<Integer> toBlind2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            destination = toExit.get(i);

            LinkedList<Integer> pathRoom = findShortestDistance(adj, source, destination, numRoom);
            // sprawdzamy czy ??cie??ka kr??tsza, ni?? poprzednio. Je??li tak, to przepisujemy na kr??tsz??
            if (toBlind2.size() == 0 || pathRoom.size()<toBlind2.size()) {
                toBlind2.clear();
                toBlind2.addAll(pathRoom);
            }
        }

        // robimy tablic?? wszystkich numer??w pokoi (toExit, toBlind1, toBlind2)
        // poprostu dodajemy do istniej??cej toExit dodatkowe pokoje z toBlind1, toBlind2
        for (int i = 0; i < toBlind1.size(); i++) {
            if (toExit.contains(toBlind1.get(i)) ) {
            } else {
                toExit.add(toBlind1.get(i));
            }
        }
        for (int i = 0; i < toBlind2.size(); i++) {
            if (toExit.contains(toBlind2.get(i)) ) {
            } else {
                toExit.add(toBlind2.get(i));
            }
        }

        // (mo??liwo???? techniczna) drukowanie tablicy mo??liwych pokoi do przejsia
        // + oznacza pok??j mo??liwy do odwiedzenia
//        for (int i = 0; i < this.tabOfRoom.length; i++) {
//            for (int j = 0; j < this.tabOfRoom[0].length; j++) {
//                if (toExit.contains(this.tabOfRoom[i][j].getNumRoom())) {
//                    System.out.print(this.tabOfRoom[i][j].getNumRoom() + "+\t\t");
//                } else {
//                    System.out.print(this.tabOfRoom[i][j].getNumRoom() + "\t\t");
//                }
//            }
//            System.out.println();
//        }


        // przypisanie ??cie??ek do pokoi. Najpierw robimy tablice obiekt??w pokoi zamiast ich numer??w
        for (int i = 0; i < toExit.size(); i++) {
            toExitRooms.add(FindRoomByNum(toExit.get(i), this.tabOfRoom));
        }

        // mamy zbi??r pokoi przez kt??re mo??e player si?? porusza??. Przypisujemy ??cie??ki przej???? do ka??dego z tych pokoi
        for(int i = 0; i < toExitRooms.size(); i++){
            int col = toExitRooms.get(i).getColRoom();
            int row = toExitRooms.get(i).getRowRoom();

            try {
                if (toExitRooms.contains(tabOfRoom[row][col + 1])) {
                    tabOfRoom[row][col].getPathSet().add(new int[]{row, col + 1});
                    tabOfRoom[row][col].getAvailableRoomsAround().add(tabOfRoom[row][col+1]);
                }
            }catch (IndexOutOfBoundsException e){}

            try {
                if (toExitRooms.contains(tabOfRoom[row][col - 1])) {
                    tabOfRoom[row][col].getPathSet().add(new int[]{row, col - 1});
                    tabOfRoom[row][col].getAvailableRoomsAround().add(tabOfRoom[row][col-1]);
                }
            }catch (IndexOutOfBoundsException e){}

            try {
                if (toExitRooms.contains(tabOfRoom[row+1][col])) {
                    tabOfRoom[row][col].getPathSet().add(new int[]{row+1, col});
                    tabOfRoom[row][col].getAvailableRoomsAround().add(tabOfRoom[row+1][col]);
                }
            }catch (IndexOutOfBoundsException e){}

            try {
                if (toExitRooms.contains(tabOfRoom[row-1][col])) {
                    tabOfRoom[row][col].getPathSet().add(new int[]{row-1, col});
                    tabOfRoom[row][col].getAvailableRoomsAround().add(tabOfRoom[row-1][col]);
                }
            }catch (IndexOutOfBoundsException e){}
        }
    }
//===========================KONIEC METODY generateMap==============================================================



    // metoda, kt??ra losowo wybiera pok??j na g??rnej lub prawej granice mapy
    // je??li losuje sie true, to wyj??cie jest na g??rnej granicy mapy
    // je??li losuje sie false, to wyj??cie jest na prawej granicy mapy
    public static Room RandRoomOnEdge(Room [][] TabOfRoom) {
        Random random = new Random();
        int row;
        int col;

        if (random.nextBoolean()) {
            row = 0;
            col = random.nextInt(TabOfRoom.length);
        } else {
            row = random.nextInt(TabOfRoom.length);
            col = TabOfRoom.length-1;
        }

        return TabOfRoom[row][col];
    }

    // metoda dla ??lepych zau??k??w, do generowania na innej stronie, ni?? wyj??cie
    public static Room RandRoomOnEdge(Room [][] TabOfRoom,Room exit) {
        Random random = new Random();
        int row;
        int col;

        int exitRow=exit.getRowRoom();
        int exitCol=exit.getColRoom();

        if(exitRow != 0) {
            row = 0;
            col = random.nextInt(TabOfRoom.length);
        } else {
            row = random.nextInt(TabOfRoom.length);
            col = TabOfRoom.length-1;
        }

        return TabOfRoom[row][col];
    }

    // metoda wyszukania pokoju wedlug numera pokoju
    public static Room FindRoomByNum(int numR, Room [][] TabOfRoom) {
        Room room = null;

        for (int i = 0; i < TabOfRoom.length; i++) {
            for (int j = 0; j < TabOfRoom.length; j++) {
                if (TabOfRoom[i][j].getNumRoom() == numR) {
                    room = TabOfRoom[i][j];
                }
            }
        }
        return room;
    }

    // metoda do tworzenia ??cie??ki pomi??dzy dwoma pokojami
    // source and dest
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
    }


    // metoda do odnalezienia najkr??tszej drogi mi??dzy dwoma pokojami
    // metoda pobiera adj - lista po????cze?? pomi??dzy pokojami,
    // source and destination - to numery pokoj??w, mi??dzy kt??rymi szukamy drogi
    // v - to liczba wierzcho??k??w, czyli liczba pokoj??w
    private static LinkedList<Integer> findShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
            int source, int destination, int v)
    {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from source
        int pred[] = new int[v];
        int dist[] = new int[v];
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();

        boolean check = BFS(adj, source, destination, v, pred, dist);
        while (check == false) {
            check = BFS(adj, source, destination, v, pred, dist);
        }

        // wypisanie ??cie??ki
        int crawl = destination;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        /*/ Print distance
       // System.out.println("Shortest path length is: " + dist[destination]);

        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        /*/
        return path;
    }

    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                               int dest, int v, int pred[], int dist[])
    {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        Random random = new Random();
        boolean isReverseLoop = random.nextBoolean();

        if (isReverseLoop) {
            while (!queue.isEmpty()) {
                int u = queue.remove();

                for (int i = 0; i < adj.get(u).size(); i++) {
                    if (visited[adj.get(u).get(i)] == false) {
                        visited[adj.get(u).get(i)] = true;
                        dist[adj.get(u).get(i)] = dist[u] + 1;
                        pred[adj.get(u).get(i)] = u;
                        queue.add(adj.get(u).get(i));

                        // stopping condition (when we find
                        // our destination)
                        if (adj.get(u).get(i) == dest)
                            return true;
                    }
                }
            }
        } else {
            while (!queue.isEmpty()) {
                int u = queue.remove();

                for (int i = adj.get(u).size()-1; i > -1; i--) {
                    if (visited[adj.get(u).get(i)] == false) {
                        visited[adj.get(u).get(i)] = true;
                        dist[adj.get(u).get(i)] = dist[u] + 1;
                        pred[adj.get(u).get(i)] = u;
                        queue.add(adj.get(u).get(i));

                        // stopping condition (when we find
                        // our destination)
                        if (adj.get(u).get(i) == dest)
                            return true;
                    }
                }
            }
        }
        return false;
    }




    public void displayMapFloor(int floor) {
        System.out.println("Mapa pi??tra: " + floor);
        for (int i = 0; i < tabOfRoom.length; i++) {
            for (int j = 0; j < tabOfRoom[0].length; j++) {
                System.out.printf("%-14s", "[" + i + "," + j + "]" + tabOfRoom[i][j].getEvent().toString());
            }
            System.out.println();
        }
    }
    public void setRoomTypes(int floor, Player player) {
        roomTypes = new RoomType[this.getTabOfRoom().length][this.getTabOfRoom()[0].length];
        for (int i = 0; i < tabOfRoom.length; i++) {
            for (int j = 0; j < tabOfRoom[0].length; j++) {
                if(i == player.getLocation_X() && j == player.getLocation_Y()) {
                    roomTypes[i][j] = RoomType.withPlayer;
                } else if(tabOfRoom[i][j].isVisited()) {
                    roomTypes[i][j] = RoomType.visited;
                } else if(!this.toExitRooms.contains(this.tabOfRoom[i][j])){
                    roomTypes[i][j] = RoomType.hidden;
                } else
                    roomTypes[i][j] = RoomType.available;
                if (this.tabOfRoom[i][j].getAvailableRoomsAround().contains(tabOfRoom[player.getLocation_X()][player.getLocation_Y()]))
                    tabOfRoom[i][j].setAvailable(true);
                else {
                    tabOfRoom[i][j].setAvailable(false);
                }
            }
        }
    }

    public Room getPlayerLocation(Player player){
        return tabOfRoom[player.getLocation_X()][player.getLocation_Y()];
    }
    
    public void setPlayerLocation(Player player, Room room){
        player.setLocation_X(room.getRowRoom());
        player.setLocation_Y(room.getColRoom());
    }
}
//======================================================================================================================