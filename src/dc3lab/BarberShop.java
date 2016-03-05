package dc3lab;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/5/2016.
 */
public class BarberShop {
    static ArrayList<Visitor> visitors = new ArrayList();
    final static Barberer master = new Barberer();

    public static void main(String[] args) {
        master.start();
        Random randomGenerator = new Random();
        Visitor comingVisitor;
        for (int i = 0; i < 10; i++) {
            comingVisitor = new Visitor(i);
            try {
                sleep(randomGenerator.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            comingVisitor.start();
            visitors.add(comingVisitor);
        }
    }

}

class Barberer extends Thread{
    @Override
    public void run() {
        while(true){
            if(BarberShop.visitors.isEmpty()){
                System.out.println("Barberer:  No visitors, time to sleep");
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                Visitor nextVisitor = BarberShop.visitors.remove(0);
                System.out.println("Barberer:  Start barbering...");
                try { // barbering
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (nextVisitor) {
                    nextVisitor.notify();
                }
                System.out.println("Barberer:  Barbering finished.");
                while(nextVisitor.getState()!=State.TERMINATED){
                }
                System.out.println(".");
            }
        }
    }
}

class Visitor extends Thread {
    int id;

    Visitor(int name){
        this.id = name;
    }
    @Override
    public void run() {
        if(BarberShop.master.getState()==State.WAITING){
            System.out.println("Visitor " + id + ":  Waking up barberer");
            synchronized (BarberShop.master){
                BarberShop.master.notify();
            }
        }
        synchronized (this){
            System.out.println("Visitor " + id + ":  Falling asleep");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Visitor " + id + ":  Thank you, master");
        }
    }
}
