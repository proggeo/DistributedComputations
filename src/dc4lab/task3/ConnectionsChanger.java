package dc4lab.task3;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/15/2016.
 */
public class ConnectionsChanger implements Runnable {

    @Override
    public void run() {
        try {
            int origin;
            int destination;
            Random random = new Random();
            while (true) {
                Schedule.rwl.writeLock().lock();
                System.out.println("ConnectionChanger locked writing");

                origin = random.nextInt(Schedule.cities.size());
                destination = random.nextInt(Schedule.cities.size());
                while (destination == origin) destination = random.nextInt(Schedule.cities.size());
                Schedule.cities.get(origin).addConnection(Schedule.cities.get(destination), random.nextInt(50));
                System.out.println("ConnectionChanger unlocked writing");
                Schedule.rwl.writeLock().unlock();

                sleep(10);

                Schedule.rwl.writeLock().lock();
                System.out.println("ConnectionChanger locked writing");
                origin = random.nextInt(Schedule.cities.size());
                destination = random.nextInt(Schedule.cities.size());
                while (destination == origin) destination = random.nextInt(Schedule.cities.size());
                Schedule.cities.get(origin).deleteConnection(Schedule.cities.get(destination));
                System.out.println("ConnectionChanger unlocked writing");
                Schedule.rwl.writeLock().unlock();

                sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
