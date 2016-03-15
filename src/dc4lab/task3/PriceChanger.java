package dc4lab.task3;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/15/2016.
 */
public class PriceChanger implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Random random = new Random();
                while (true) {
                    Schedule.rwl.writeLock().lock();
                    System.out.println("PriceChanger locked writing");
                    Schedule.cities.get(random.nextInt(Schedule.cities.size()))
                            .changePrice(Schedule.cities.get(random.nextInt(Schedule.cities.size())), random.nextInt(50));
                    System.out.println("PriceChanger unlocked writing");
                    Schedule.rwl.writeLock().unlock();
                    sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
