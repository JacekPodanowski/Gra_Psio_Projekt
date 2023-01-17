package Game;
import Chararcter.Player;
import Game.Event.Down;
import Game.Event.Entrance;
import Game.Event.Exit;
import Game.Event.Up;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Map {
    //================================================= ATRYBUTY KLASY =================================================
    private Room[][] tabOfRoom;  // tablica z pokojow 5x5 (na razie)
    private ArrayList<Room[][]> bigMap=new ArrayList<>();
    private ArrayList<Room> toExitRooms;

    private ArrayList<int[]> Ups=new ArrayList<>();
    private ArrayList<int[]> Downs=new ArrayList<>();
    private boolean p1UP=false;
    private boolean p0UP=false;
    private boolean p2Down=false;
    private boolean p1Down=false;

    //==================================================================================================================



    //================================================== KONSTRUKTORY ==================================================
    public Map(Player player,int size) {
        tabOfRoom = new Room[size][size];
        toExitRooms = new ArrayList<Room>();
        //generateMap(player,1,false);
        generateBigMap(player,size);
    }
    //==================================================================================================================



    //============================================= GETTERY I SETTERY ==================================================
    public Room[][] getTabOfRoom() {
        return tabOfRoom;
    }

    public void setTabOfRoom(Room[][] tabOfRoom) {
        this.tabOfRoom = tabOfRoom;
    }

    public ArrayList<int[]> getUps() {
        return Ups;
    }

    public void setUps(ArrayList<int[]> ups) {
        Ups = ups;
    }

    public ArrayList<int[]> getDowns() {
        return Downs;
    }

    public void setDowns(ArrayList<int[]> downs) {
        Downs = downs;
    }
    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public void generateBigMap(Player player,int size) {
        Random r =new Random();
        boolean E0=r.nextBoolean();
        boolean E2=!E0;

        tabOfRoom = new Room[size][size];
        toExitRooms = new ArrayList<Room>();//nwm
        generateMap(player,0,E0);
        this.bigMap.add(this.tabOfRoom);
        this.tabOfRoom=new Room[size][size];
        toExitRooms = new ArrayList<Room>();

        generateMap(player,1,false);
        this.bigMap.add(this.tabOfRoom);
        this.tabOfRoom=new Room[size][size];
        toExitRooms = new ArrayList<Room>();

        generateMap(player,2,E2);
        this.bigMap.add(this.tabOfRoom);

    }

    public void generateMap(Player player,int level,boolean isExit) {
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

        if(level==1) {
            // lewy dolny róg zawsze jest wejściem. Inicjacja wejścia
            this.tabOfRoom[this.tabOfRoom.length - 1][0].setEnter(true);
            this.tabOfRoom[this.tabOfRoom.length - 1][0].setEvent(new Entrance());
        }
        // losujemy wyjście i dwa zaułki na górnej lub na prawej granice mapy
        // losowanie wyjścia
        Room roomTemp = RandRoomOnEdge(this.tabOfRoom);
        int exitNum = roomTemp.getNumRoom();

        if(isExit) {
            this.tabOfRoom[roomTemp.getRowRoom()][roomTemp.getColRoom()].setExit(true);
            this.tabOfRoom[roomTemp.getRowRoom()][roomTemp.getColRoom()].setEvent(new Exit());
        }

        // losowanie ślepego zaułku nr1
        // jeśli wyjście jest na górnej granicy, to zaułki są na prawej. W przeciwnym przypadku podobnie
        roomTemp = RandRoomOnEdge(this.tabOfRoom, FindRoomByNum(exitNum, this.tabOfRoom));
        int blindEnd1_Num = roomTemp.getNumRoom();
        // jeśli się powtarza, to losowanie jesczsze raz
        while (blindEnd1_Num == exitNum) {
            roomTemp = RandRoomOnEdge(this.tabOfRoom);
            blindEnd1_Num = roomTemp.getNumRoom();
        }
        if(level==0 && p0UP==false) {
            FindRoomByNum(blindEnd1_Num, this.tabOfRoom).setEvent(new Up());
            int[] wsp = {level, FindRoomByNum(blindEnd1_Num, this.tabOfRoom).getRowRoom(), FindRoomByNum(blindEnd1_Num, this.tabOfRoom).getColRoom()};
            Ups.add(wsp);  // wsp to współżędne
            p0UP=true;
        }
        if(level==1 && p1UP==false) {
            FindRoomByNum(blindEnd1_Num, this.tabOfRoom).setEvent(new Up());
            int[] wsp = {level, FindRoomByNum(blindEnd1_Num, this.tabOfRoom).getRowRoom(), FindRoomByNum(blindEnd1_Num, this.tabOfRoom).getColRoom()};
            Ups.add(wsp);
            p1UP=true;
        }


        // losowanie ślepego zaułku nr2
        roomTemp = RandRoomOnEdge(this.tabOfRoom, FindRoomByNum(exitNum, this.tabOfRoom));
        int blindEnd2_Num = roomTemp.getNumRoom();
        // jeśli się powtarza, to losowanie jesczsze raz
        while (blindEnd2_Num == exitNum || blindEnd2_Num == blindEnd1_Num) {
            roomTemp = RandRoomOnEdge(this.tabOfRoom);
            blindEnd2_Num = roomTemp.getNumRoom();
        }
        if(level==2 && p2Down==false) {
            FindRoomByNum(blindEnd2_Num, this.tabOfRoom).setEvent(new Down());
            int[] wsp = {level, FindRoomByNum(blindEnd2_Num, this.tabOfRoom).getRowRoom(), FindRoomByNum(blindEnd2_Num, this.tabOfRoom).getColRoom()};
            Downs.add(wsp);
            p2Down=true;
        }
        if(level==1 && p1Down==false) {
            FindRoomByNum(blindEnd2_Num, this.tabOfRoom).setEvent(new Down());
            int[] wsp = {level, FindRoomByNum(blindEnd2_Num, this.tabOfRoom).getRowRoom(), FindRoomByNum(blindEnd2_Num, this.tabOfRoom).getColRoom()};
            Downs.add(wsp);
            p1Down=true;
        }


        // Adjacency list dla przechowywania połączeń między numerami pokojów
        // to jest pomocnicza tablica, która przekazywana do metody odnalezienia najkrótszej drogi
        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<ArrayList<Integer>>(numRoom);
        for (int i = 0; i < numRoom; i++) {
            adj.add(new ArrayList<Integer>());
        }


        // generacja wszystkich ścieżek dla wszystkich pokoi i
        // przypisywanie tych ścieżek do Adjacency list
        int MapLength = this.tabOfRoom.length; // pomocnicza zmienna
        //ścieżki do pokojow sąsiednich dla pokojów na rogach:
        addEdge(adj, 0, 1);
        addEdge(adj, 0, MapLength);
        addEdge(adj, MapLength-1, MapLength-2);
        addEdge(adj, MapLength-1, 2*MapLength-1);
        addEdge(adj, MapLength*(MapLength-1), MapLength*(MapLength-1)+1);
        addEdge(adj, MapLength*(MapLength-1), MapLength*(MapLength-2));
        addEdge(adj, MapLength*MapLength -1 , MapLength*MapLength-2);
        addEdge(adj, MapLength*MapLength -1,  MapLength*(MapLength-1)-1);

        // generacja ścieżek dla lewej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, MapLength*i, MapLength*i+1);
            addEdge(adj, MapLength*i, MapLength*(i-1));
            addEdge(adj, MapLength*i, MapLength*(i+1));
        }
        // generacja ścieżek dla prawej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, MapLength*(i+1)-1, MapLength*(i+1)-2);
            addEdge(adj, MapLength*(i+1)-1, MapLength*(i  )-1);
            addEdge(adj, MapLength*(i+1)-1, MapLength*(i+2)-1);
        }
        // generacja ścieżek dla górnej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, i, i-1);
            addEdge(adj, i, i+1);
            addEdge(adj, i, i+MapLength);
        }
        // generacja ścieżek dla dolnej granicy tablicy
        for (int i = 1; i < MapLength-1; i++) {
            addEdge(adj, MapLength*(MapLength-1)+i, MapLength*(MapLength-1)+i-1);
            addEdge(adj, MapLength*(MapLength-1)+i, MapLength*(MapLength-1)+i+1);
            addEdge(adj, MapLength*(MapLength-1)+i, MapLength*(MapLength-2)+i);
        }

        // generacja ścieżek dla środkowej części tablicy pokojów
        for (int i = 1; i < MapLength-1; i++) {
            for (int j = 1; j < MapLength-1; j++) {
                addEdge(adj, MapLength*i+j, MapLength*i+j+1);
                addEdge(adj, MapLength*i+j, MapLength*i+j-1);
                addEdge(adj, MapLength*i+j, MapLength*(i-1)+j);
                addEdge(adj, MapLength*i+j, MapLength*(i+1)+j);
            }
        }


        // source and destination przekazywane do metody, między czym będzie odnajdywana najkrótsza droga
        int source = this.tabOfRoom[this.tabOfRoom.length-1][0].getNumRoom();
        int destination = exitNum;

        // wywołanie metody "findshortestdistance" i przepisanie wynikowej ścieżki z LinkedList to ArrayList
        ArrayList<Integer> toExit = new ArrayList<>();
        toExit.addAll(findShortestDistance(adj, source, destination, numRoom));
        Collections.reverse(toExit);

        // skasowanie ścieżek przejścia z pokoju wyjściowego dla tego, żeby zaułki się generowały nie przez exit
        adj.get(exitNum).clear();



        // tu szukamy najkrótszej ścieżki z pokoju ślepego zaułku do pierwszych czterech pokoi z toExit.
        // Dla łatwiejszego zrozumienia kodu nie wyrzucam powielanie na zewnątrz w oddzielną funkcję (Dla ślepego zaułka 1 i 2)

        source = blindEnd1_Num;


        ArrayList<Integer> toBlind1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            destination = toExit.get(i);

            LinkedList<Integer> pathRoom = findShortestDistance(adj, source, destination, numRoom);
            // sprawdzamy czy ścieżka krótsza, niż poprzednio. Jeśli tak, to przepisujemy na krótszą
            if (toBlind1.size() == 0 || pathRoom.size()<toBlind1.size()) {
                toBlind1.clear();
                toBlind1.addAll(pathRoom);
            }
            pathRoom.clear();
        }

        // tu szukamy najkrótszej ścieżki z pokoju ślepego zaułku nr2 do ścieżki toExit
        source = blindEnd2_Num;

        ArrayList<Integer> toBlind2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            destination = toExit.get(i);

            LinkedList<Integer> pathRoom = findShortestDistance(adj, source, destination, numRoom);
            // sprawdzamy czy ścieżka krótsza, niż poprzednio. Jeśli tak, to przepisujemy na krótszą
            if (toBlind2.size() == 0 || pathRoom.size()<toBlind2.size()) {
                toBlind2.clear();
                toBlind2.addAll(pathRoom);
            }
        }

        // robimy tablicę wszystkich numerów pokoi (toExit, toBlind1, toBlind2)
        // poprostu dodajemy do istniejącej toExit dodatkowe pokoje z toBlind1, toBlind2
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

        // (możliwość techniczna) drukowanie tablicy możliwych pokoi do przejsia
        // + oznacza pokój możliwy do odwiedzenia
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


        // przypisanie ścieżek do pokoi. Najpierw robimy tablice obiektów pokoi zamiast ich numerów
        for (int i = 0; i < toExit.size(); i++) {
            toExitRooms.add(FindRoomByNum(toExit.get(i), this.tabOfRoom));
        }

        // mamy zbiór pokoi przez które może player się poruszać. Przypisujemy ścieżki przejść do każdego z tych pokoi
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



    // metoda, która losowo wybiera pokój na górnej lub prawej granice mapy
    // jeśli losuje sie true, to wyjście jest na górnej granicy mapy
    // jeśli losuje sie false, to wyjście jest na prawej granicy mapy
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

    // metoda dla ślepych zaułków, do generowania na innej stronie, niż wyjście
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

    // metoda do tworzenia ścieżki pomiędzy dwoma pokojami
    // source and dest
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
    }


    // metoda do odnalezienia najkrótszej drogi między dwoma pokojami
    // metoda pobiera adj - lista połączeń pomiędzy pokojami,
    // source and destination - to numery pokojów, między którymi szukamy drogi
    // v - to liczba wierzchołków, czyli liczba pokojów
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

        // wypisanie ścieżki
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
        System.out.println("Mapa piętra: " + floor);
        Room[][] mapa=bigMap.get(floor);
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                System.out.printf("%-14s", "[" + i + "," + j + "]" + mapa[i][j].getEvent().toString());
            }
            System.out.println();
        }
    }
    public void displayCurrentMapFloor(int floor, Player player) {
        System.out.println("Mapa piętra: " + floor);
        for (int i = 0; i < tabOfRoom.length; i++) {
            for (int j = 0; j < tabOfRoom[0].length; j++) {
//              int[] tab = {player.getLocation_X(), player.getLocation_Y()};
                if(i == player.getLocation_X() && j == player.getLocation_Y()) {
                    System.out.printf("%-6s", "[ x ]");
                } else if(tabOfRoom[i][j].isVisited()) {
                    System.out.printf("%-6s", "[   ]");
                } else if (this.tabOfRoom[i][j].getAvailableRoomsAround().contains(tabOfRoom[player.getLocation_X()][player.getLocation_Y()])) {
                    System.out.printf("%-6s", "[ ? ]");
                } else{
                    System.out.printf("%-6s", "");
                }
            }
            System.out.println();
        }
    }

    public ArrayList<Room> displayRoomsToGo(Player player) {
        ArrayList<Room> availableRoomsAround = this.tabOfRoom[player.getLocation_X()][player.getLocation_Y()].getAvailableRoomsAround();
        ArrayList<Room> roomsToGo = new ArrayList<Room>();

        for (int i = 0; i < availableRoomsAround.size(); i++) {
            if (availableRoomsAround.get(i).isVisited()){
            } else {
                roomsToGo.add(availableRoomsAround.get(i)) ;
            }
        }
        return roomsToGo;
    }

    public RoomType[][] displayTheMapInGUI(Player player) {

        RoomType [][]roomType = new RoomType[tabOfRoom.length][tabOfRoom.length];

        for (int i = 0; i < tabOfRoom.length; i++) {
            for (int j = 0; j < tabOfRoom[0].length; j++) {
                int[] tab = {player.getLocation_X(), player.getLocation_Y()};

                if(i == player.getLocation_X() && j == player.getLocation_Y()) {
                    roomType[i][j] = RoomType.withPlayer;

                } else if(tabOfRoom[i][j].isVisited()) {
                    roomType[i][j] = RoomType.visited;

                } else if (this.tabOfRoom[i][j].getAvailableRoomsAround().contains(tabOfRoom[player.getLocation_X()][player.getLocation_Y()])) {
                    roomType[i][j] = RoomType.available;

                } else{
                    roomType[i][j] = RoomType.empty;
                }
            }

        }
        return roomType;
    }

}
//======================================================================================================================