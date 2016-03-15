package dc4lab.task3;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/15/2016.
 */
public class CitiesChanger implements Runnable {

    @Override
    public void run() {
        try {
            Random random = new Random();
            while (true) {
                Schedule.rwl.writeLock().lock();
                System.out.println("CitiesChanger locked writing");
                City toDelete = Schedule.cities.get(random.nextInt(Schedule.cities.size()));
                Schedule.availableCities.add(toDelete);
                Schedule.cities.remove(toDelete);
                for (int i = 0; i < Schedule.cities.size(); i++) {
                    Schedule.cities.get(i).deleteConnection(toDelete);
                }
                System.out.println("CitiesChanger unlocked writing");
                Schedule.rwl.writeLock().unlock();

                sleep(10);

                Schedule.rwl.writeLock().lock();
                System.out.println("CitiesChanger locked writing");
                City toAdd = Schedule.availableCities.get(random.nextInt(Schedule.availableCities.size()));
                Schedule.cities.add(toAdd);
                Schedule.availableCities.remove(toAdd);
                System.out.println("CitiesChanger unlocked writing");
                Schedule.rwl.writeLock().unlock();

                sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
