package dc4lab.task3;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/15/2016.
 */
public class Passenger implements Runnable {
    @Override
    public void run() {
        try {
            Random random = new Random();
            int origin;
            int destination;
            String output = "";
            while (true) {
                output = "";
                Schedule.rwl.readLock().lock();
                System.out.println("Passenger locked reading");
                origin = random.nextInt(Schedule.cities.size());
                destination = random.nextInt(Schedule.cities.size());
                while (destination == origin) destination = random.nextInt(Schedule.cities.size());
                Schedule.cities.get(origin).findRoute(Schedule.cities.get(destination));
                System.out.println("Passenger unlocked reading");
                Schedule.rwl.readLock().unlock();
                sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
