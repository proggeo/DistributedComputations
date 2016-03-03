package dc2lab;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by Proggeo on 2/27/2016.
 */
public class HonourArmy {
    static Semaphore uncleSam = new Semaphore(1);
    static ArrayList<Integer> truck = new ArrayList<>();
    static ArrayList<Integer> prices = new ArrayList<>();
    static int sleepingTime = 1000;


    public static void main(String[] args) {

        Thread ivanov = new Thread(new Ivanov());
        Thread petrov = new Thread(new Petrov());
        Thread necheporchuk = new Thread(new Necheporchuk());

        ivanov.start();
        petrov.start();
        necheporchuk.start();
    }
}
