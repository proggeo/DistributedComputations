package dc2lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Proggeo on 2/23/2016.
 */
public class WinnieBees {

    static boolean found = false;
    static int columns = 10000;
    static int rows = 10000;
    static int threadsCount = 10;
    static Semaphore uncleSam = new Semaphore(1);
    static ArrayList<List<Boolean>> tasks;
    static int rowsChecked = 0;
    static Date begin;


    public static void main(String[] args) {
        tasks = new ArrayList();

        begin = new Date();

        ArrayList<Boolean> forest = new ArrayList<>();
        for (int i = 0; i < columns * rows; i++)
            forest.add(false);

        Random randomGenerator = new Random();
        int winnie = randomGenerator.nextInt(columns * rows);
        forest.add(winnie, true);
        System.out.println("Winnie is hiding under bush on " + (winnie/columns+1) + " row, "+ (winnie%columns+1)+" column");

        for (int i = 0; i < rows; i++)
            tasks.add(forest.subList(i * columns, (i + 1) * columns - 1));

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            threads.add(i, new Thread(new Bees(i)));
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
            System.out.println(i);
        }
    }
}
