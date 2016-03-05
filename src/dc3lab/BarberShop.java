package dc3lab;

import java.util.ArrayList;

/**
 * Created by Proggeo on 3/5/2016.
 */
public class BarberShop {
    static ArrayList visitors = new ArrayList();
    static Barberer master;

    public static void main(String[] args) {
        master = new Barberer();
        master.start();

    }

}

class Barberer extends Thread{



    @Override
    public void run() {
        if(BarberShop.visitors.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{

        }
    }
}

class Visitor extends Thread {
    @Override
    public void run() {
        if(BarberShop.master.)
    }
}
