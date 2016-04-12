package dc5lab.task1;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class MainClass {
    static final int size = 5;
    static CyclicBarrier cb = new CyclicBarrier(size+1);
    static Boolean stable = false;
    static ArrayList<Recruits> recruits= new ArrayList<>();

    public static void main(String[] args) {
        for (int i=0;i<size;i++) {
            recruits.add(new Recruits(75,i));
            if(i>0)recruits.get(i).setLeft(recruits.get(i - 1));
        }
        Thread prapor = new Thread(new Prapor(recruits));

        for (int i = 0; i < size; i++) {
            recruits.get(i).start();
        }
        prapor.start();

    }

}
