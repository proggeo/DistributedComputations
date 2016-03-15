package dc4lab.task1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Proggeo on 3/12/2016.
 */
public class ReaderWriter {
    final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static String path = "D:\\OneDrive\\$Programming\\DistributedComputations\\src\\dc4lab\\task1\\database";

    public static void main(String[] args) throws InterruptedException{
        Thread writer1 = new Thread(new Writer("Andrew Bloyd:556-75-89"));
        Thread writer2 = new Thread(new Writer("Andrew Cloyd:556-76-89"));
        Thread writer3 = new Thread(new Writer("Andrew Dloyd:556-77-89"));
        Thread reader1 = new Thread(new dc4lab.task1.Reader("556-75-89"));
        Thread reader2 = new Thread(new dc4lab.task1.Reader("Andrew", "Cloyd"));
        writer1.start();
        reader1.start();
        writer2.start();
        reader2.start();
        writer3.start();
        writer1.join();
        writer2.join();
        writer3.join();
        reader1.join();
        reader2.join();

    }
}


