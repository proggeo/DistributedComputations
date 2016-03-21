package dc5lab.task3;


import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class MainClass {
    static CyclicBarrier cb = new CyclicBarrier(3);

    public static void main(String[] args) {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();
        Worker worker3 = new Worker();

        worker1.neighbour1 = worker2;
        worker1.neighbour2 = worker3;
        worker2.neighbour1 = worker1;
        worker2.neighbour2 = worker3;
        worker3.neighbour1 = worker1;
        worker3.neighbour2 = worker2;

        Thread thread1= new Thread(worker1);
        Thread thread2= new Thread(worker2);
        Thread thread3= new Thread(worker3);

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
