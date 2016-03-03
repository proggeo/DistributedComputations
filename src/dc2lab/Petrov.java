package dc2lab;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 2/27/2016.
 */
class Petrov implements Runnable {

    Integer a;

    @Override
    public void run() {
        while (true) {
            try {
                HonourArmy.uncleSam.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (HonourArmy.truck.size() > 0) {
                a = HonourArmy.truck.remove(0);
                HonourArmy.uncleSam.release();
                System.out.println("Petrov: Got " + a);
                try {
                    sleep(HonourArmy.sleepingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                HonourArmy.uncleSam.release();
                try {
                    sleep(HonourArmy.sleepingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            try {
                HonourArmy.uncleSam.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HonourArmy.prices.add(a);
            HonourArmy.uncleSam.release();
            System.out.println("Petrov put in truck " + a);
        }
    }
}
