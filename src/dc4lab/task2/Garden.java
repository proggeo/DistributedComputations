package dc4lab.task2;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/14/2016.
 */
public class Garden {
    final static int size = 10;
    static boolean[][] garden = new boolean[size][size];
    final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);

    public static void main(String[] args) {
//initialization start
        Random randomGenerator = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                garden[i][j] = randomGenerator.nextBoolean();
            }
        }
        Thread gardener = new Thread(new Gardener());
        Thread nature = new Thread(new Nature());
        Thread monitor1 = new Thread(new Monitor1());
        Thread monitor2 = new Thread(new Monitor2());
//initialization end
        System.out.println("Program start!");
        gardener.start();
        nature.start();
        monitor1.start();
        monitor2.start();

    }
}

