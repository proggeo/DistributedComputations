package dc2lab;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 2/27/2016.
 */
class Ivanov implements Runnable{

    Integer a;

    @Override
    public void run() {
        while (true) {
            a = produceItem();
            try {
                HonourArmy.uncleSam.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            HonourArmy.truck.add(a);
            HonourArmy.uncleSam.release();
            System.out.println("Ivanov: " + a + " is in buffer.");
        }
    }

    Integer produceItem(){
        Random randomGenerator = new Random();
        try {
            sleep(HonourArmy.sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ivanov: stealing...");
        return randomGenerator.nextInt(1000000);
    }


}
