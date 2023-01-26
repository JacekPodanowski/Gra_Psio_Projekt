import GUI.View.MainWindow;
import Observers.GUIRefresher;


public class Main {
    public static void main (String[] args){
        new GUIRefresher(new MainWindow());
    }
}
