package dc2lab;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 2/27/2016.
 */
class Necheporchuk implements Runnable {

    Integer a;

    @Override
    public void run() {
        while (true) {
            try {
                HonourArmy.uncleSam.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (HonourArmy.prices.size() > 0) {
                a = HonourArmy.prices.remove(0);
                HonourArmy.uncleSam.release();
            }
            else
            {
                HonourArmy.uncleSam.release();
                try {
                    sleep(HonourArmy.sleepingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            consumeItem(a);
        }
    }

    void consumeItem(Integer a){
        System.out.println("Necheporchuk: Got " + a);
        try {
            sleep(HonourArmy.sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random randomGenerator = new Random();
        System.out.println("Necheporchuk: Price of object " + a + ": USD " + randomGenerator.nextDouble()*1000);

    }
}
