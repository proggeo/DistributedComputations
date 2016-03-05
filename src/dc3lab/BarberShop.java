package dc3lab;

import java.util.ArrayList;

/**
 * Created by Proggeo on 3/5/2016.
 */
public class BarberShop {
    static ArrayList<Visitor> visitors = new ArrayList();
    final static Barberer master = new Barberer();

    public static void main(String[] args) {
        master.start();

    }

}

class Barberer extends Thread{



    @Override
    public void run() {
        while(true){
            if(BarberShop.visitors.isEmpty()){
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
                try { // barbering
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (nextVisitor) {
                    nextVisitor.notify();
                }
            }
        }
    }
}

class Visitor extends Thread {
    @Override
    public void run() {
        if(BarberShop.master.getState()==State.WAITING){
            synchronized (BarberShop.master){
                BarberShop.master.notify();
            }
        }
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
