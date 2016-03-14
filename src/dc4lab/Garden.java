package dc4lab;


import java.io.BufferedWriter;
import java.io.FileWriter;
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

class Gardener implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < Garden.size; i++) {
                    for (int j = 0; j < Garden.size; j++) {
                        Garden.rwl.writeLock().lock();
                        System.out.println("Writing locked by gardener on cell:" + i + " " + j);
                        if (!Garden.garden[i][j]) {
                            Garden.garden[i][j] = true;
                        }
                        System.out.println("Writing unlocked by gardener");
                        Garden.rwl.writeLock().unlock();
                        //sleep(100);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Nature implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Random rg = new Random();
                Garden.rwl.writeLock().lock();
                System.out.println("Writing locked by nature");
                Garden.garden[rg.nextInt(Garden.size)][rg.nextInt(Garden.size)] = rg.nextBoolean();
                System.out.println("Writing unlocked by nature");
                Garden.rwl.writeLock().unlock();
                //sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Monitor1 implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                String file = "";
                Garden.rwl.readLock().lock();
                System.out.println("Reading locked by monitor1");
                for (int i = 0; i < Garden.size; i++) {
                    //file += (" " + i + ": ");
                    for (int j = 0; j < Garden.size; j++) {
                        if (Garden.garden[i][j]) file += 1;
                        else file += 0;
                    }
                }
                System.out.println("Reading unlocked by monitor1");
                Garden.rwl.readLock().unlock();

                BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\OneDrive\\$Programming\\DistributedComputations\\src\\dc4lab\\gardenFile"));
                bw.write(file);
                bw.close();
                //sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Monitor2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                String output = "";
                Garden.rwl.readLock().lock();
                System.out.println("Reading locked by monitor2");
                for (int i = 0; i < Garden.size; i++) {
                    output += (i + ": ");
                    for (int j = 0; j < Garden.size; j++) {
                        if (Garden.garden[i][j]) output += 1;
                        else output += 0;
                    }
                    output += "\n";
                }
                System.out.println(output);
                System.out.println("Reading unlocked by monitor2");
                Garden.rwl.readLock().unlock();

                //sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
