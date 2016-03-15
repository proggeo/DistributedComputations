package dc4lab.task2;

import java.util.Random;

/**
 * Created by Proggeo on 3/14/2016.
 */
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
