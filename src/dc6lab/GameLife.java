package dc6lab;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class GameLife {
    static final int size = 10;
    static int[][] field = new int[size][size];
    static int[][] nextField = new int[size][size];
    static final int countThreads = 10;
    static CyclicBarrier cb = new CyclicBarrier(2);

    public static void main(String[] args) {
        try {

            Random random = new Random();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    field[i][j] = random.nextInt(2);
                }
            }

            Thread processor = new Thread(new Processor(0, 0, size, size));
            processor.start();
            while (true) {
                cb.await();
                print();
                cb.await();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    static void print() {
        String out = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                out += field[i][j];
            }
            out += "\n";
        }
        out+="------------------------------------";
        System.out.println(out);
    }

}
