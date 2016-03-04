package dc3lab;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/4/2016.
 */
public class HoneyPot {

    static int pot = 0;
    static int potSize = 500; // H
    static int beesCount = 32;
    static Semaphore uncleSam = new Semaphore(1);
    static Winnie pooh = new Winnie();

    public static void main(String[] args) {
        ArrayList<Thread> bees = new ArrayList<>();
        for (int i = 0; i < beesCount ; i++) {
            bees.add(new Thread(new Bee(i)));
            bees.get(i).start();
        }

    }


}

class Winnie implements Runnable{

    @Override
    public void run() {
        try {
            HoneyPot.uncleSam.acquire();
            sleep(1000);
            System.out.println("Winnie eats");
            HoneyPot.pot=0;
            Bee.full=false;
            HoneyPot.uncleSam.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Bee implements Runnable{

    int id;
    static boolean full=false;

    Bee(int id){
        this.id = id;
    }

    @Override
    public void run() {
        while(true){
            try {
                if(!full)add();
                sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void add() throws InterruptedException {
        HoneyPot.uncleSam.acquire();
        if(full){
            HoneyPot.uncleSam.release();
            return;
        }
        if(HoneyPot.pot<HoneyPot.potSize) {
            HoneyPot.pot++;
            System.out.println("" + id+ ": " + HoneyPot.pot);
            HoneyPot.uncleSam.release();
        }
        else{
            System.out.println("Wake up:" + id+ ": " + HoneyPot.pot);
            full=true;
            HoneyPot.uncleSam.release();
            HoneyPot.pooh.run();
        }
    }
}
