package Game;
import Chararcter.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Map {
    //================================================= ATRYBUTY KLASY =================================================
    private Room[][] TabOfRoom;  // tablica z pokojow 5x5 (na razie)


    //==================================================================================================================



    //================================================== KONSTRUKTORY ==================================================
    public Map(Player player,int size) {
        TabOfRoom = new Room[size][size];
        generateMap(player);
    }

    public Map(int row, int col, Player player){
        TabOfRoom = new Room[row][col];
        generateMap(player);
    }
    //==================================================================================================================



    //============================================= GETTERY I SETTERY ==================================================
    public Room[][] getTabOfRoom() {
        return TabOfRoom;
    }

    public void setTabOfRoom(Room[][] tabOfRoom) {
        this.TabOfRoom = tabOfRoom;
    }

    //==================================================================================================================



    //============================================= METODY KLASY =======================================================
    public void generateMap(Player player) {
        Random random = new Random();

        // przypisanie pokojow do tablicy mapy
        for (int i = 0; i < this.TabOfRoom.length; i++) {
            for (int j = 0; j < this.TabOfRoom[i].length; j++) {
                this.TabOfRoom[i][j] = new Room(i,j, player);
            }
        }

        // lewy dolny róg zawsze jest wejściem. Inicjacja wejścia
        this.TabOfRoom[this.TabOfRoom.length-1][0].setEnter(true);

        // losujemy wyjście na górnej lub na prawej granice mapy
        // jeśli losuje sie true, to wyjście jest na górnej granicy mapy
        // jeśli losuje sie false, to wyjście jest na prawej granicy mapy
        int exitRow; // numer komórki w tablicy: row and col
        int exitCol;

        if (random.nextBoolean()) {
            exitRow = 0;
            exitCol = random.nextInt(this.TabOfRoom.length);
            this.TabOfRoom[0][exitCol].setExit(true);
        } else {
            exitRow = random.nextInt(this.TabOfRoom.length);
            exitCol = this.TabOfRoom.length-1;
            this.TabOfRoom[exitRow][0].setExit(true);
        }
        /*/ temp druk
        System.out.println(exitRow);
        System.out.println(exitCol);
/*/
        // numeracja pokoi w tablicy
        int numRoom = 0;

        for (int i = 0; i < this.TabOfRoom.length; i++) {
            for (int j = 0; j < this.TabOfRoom.length; j++) {
                this.TabOfRoom[i][j].setNumRoom(numRoom);
                numRoom++;
            }
        }

        // Adjacency list for storing which vertices are connected
        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<ArrayList<Integer>>(numRoom);
        for (int i = 0; i < numRoom; i++) {
            adj.add(new ArrayList<Integer>());
        }


        //generacja wszystkich ścieżek dla wszystkich pokoi
        int MapLength = this.TabOfRoom.length; // pomocnicza zmienna
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
        for (int i = 1; i < MapLength-2; i++) {
            for (int j = 1; j < MapLength-2; j++) {
                addEdge(adj, MapLength*i+j, MapLength*i+j+1);
                addEdge(adj, MapLength*i+j, MapLength*i+j-1);
                addEdge(adj, MapLength*i+j, MapLength*(i-1)+j);
                addEdge(adj, MapLength*i+j, MapLength*(i+1)+j);
            }
        }

        // source and destination of exit and enter
        int source = this.TabOfRoom[this.TabOfRoom.length-1][0].getNumRoom();
        int dest = this.TabOfRoom[exitRow][exitCol].getNumRoom();

        // przepisanie wynikowej ścieżki z LinkedList to ArrayList
        ArrayList<Integer> toExit = new ArrayList<>();
        toExit.addAll(printShortestDistance(adj, source, dest, numRoom));

        // przypisanie ścieżek do pokoi
        Room roomTemp = null;
        Room [] ToExitRooms = new Room[toExit.size()];

        for (int i = 0; i < toExit.size(); i++) {
            ToExitRooms[i] = FindRoomByNum(toExit.get(i), this.TabOfRoom);
        }

<<<<<<< Updated upstream
        // pokoj wejsciowy
        ToExitRooms[ToExitRooms.length-1].setPathSet(new int[][]{{ToExitRooms[ToExitRooms.length-2].getRowRoom(),
                ToExitRooms[ToExitRooms.length-2].getColRoom()}});
        // pokoj wyjsciowy
        ToExitRooms[0].setPathSet(new int[][]{{ToExitRooms[1].getRowRoom(), ToExitRooms[1].getColRoom()}});
        // pokoje posrednie
=======
        // przypisujemy ścieżki jako atrybuty do obiektów pokoi. Wyjście i wejście ma tylko po jednym pokoju
        // sąsiednim, dlatego przypisujemy do nich oddzielnie, nie przez pętlę.
        ArrayList<int []> tempArr = new ArrayList<int[]>(4); // pomocnicza tablica służąca do przekazania

        // Pokoj wejsciowy:
        tempArr.add(new int[]{ToExitRooms[ToExitRooms.length-2].getRowRoom(),ToExitRooms[ToExitRooms.length-2].getColRoom()});
        ToExitRooms[ToExitRooms.length-1].setPathSet(tempArr);
        tempArr.clear();

        // pokoj wyjsciowy:
        tempArr.add(new int[]{ToExitRooms[1].getRowRoom(), ToExitRooms[1].getColRoom()});
        ToExitRooms[0].setPathSet(tempArr);
        tempArr.clear();

        // pokoje posrednie:
>>>>>>> Stashed changes
        for (int i = 1; i < ToExitRooms.length-2; i++) {
            tempArr.add(new int[]{ToExitRooms[i-1].getRowRoom(),ToExitRooms[i-1].getColRoom()});
            tempArr.add(new int[]{ToExitRooms[i+1].getRowRoom(),ToExitRooms[i+1].getColRoom()});
            ToExitRooms[i].setPathSet(tempArr);
            tempArr.clear();
        }
<<<<<<< Updated upstream

=======
        tempArr=null;
>>>>>>> Stashed changes
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

    // function to form edge between two vertices
    // source and dest
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        adj.get(i).add(j);
        //adj.get(j).add(i);
    }



    // function to print the shortest distance and path
    // between source vertex and destination vertex
    private static LinkedList<Integer> printShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
            int s, int dest, int v)
    {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();

        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                    "are not connected");
            return path;
        }

        // wypisanie ścieżki
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        /*/ Print distance
       // System.out.println("Shortest path length is: " + dist[dest]);

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
        return false;
    }

//    public void main(String[] args) {
//        generateMap(new Player());
//    }








    public void displayMapFloor(int floor) {
        System.out.println("Mapa piętra: " + floor);
        for (int i = 0; i < TabOfRoom.length; i++) {
            for (int j = 0; j < TabOfRoom[0].length; j++) {
                System.out.printf("%-12s", "[" + i + "," + j + "]" + TabOfRoom[i][j].getEvent().toString());
            }
            System.out.println();
        }
    }
}
//======================================================================================================================