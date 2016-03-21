package dc5lab.task2;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class MainClass52 {
    static final int size = 4;
    static CyclicBarrier cb = new CyclicBarrier(size + 1);
    static boolean finished = false;


    public static void main(String[] args) {
        try {
            ArrayList<Writer> threads = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                threads.add(new Writer(i));
            }
            for (int i = 0; i < 4; i++) {
                threads.get(i).start();
            }
            ArrayList<Integer> letCount = new ArrayList<>();

            while (!finished) {
                cb.await();
                for (int i = 0; i < size; i++) {
                    letCount.add(i,threads.get(i).getACount());
                }
                for (int i = 0; i < size; i++) {
                    int count = 0;
                    for (int j = 0; j < size; j++) {
                        if (letCount.get(i) == letCount.get(j)) count++;
                    }
                    if (count >= 3) {
                        finished = true;
                    }
//                    System.out.println("count: " + count);
                }
                cb.await();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

}
