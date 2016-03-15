package dc4lab.task2;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Created by Proggeo on 3/14/2016.
 */
class Monitor1 implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                String file = "";
                Garden.rwl.readLock().lock();
                System.out.println("Reading locked by monitor1");
                for (int i = 0; i < Garden.size; i++) {
                    //file += (" " + i + ": ");
                    for (int j = 0; j < Garden.size; j++) {
                        if (Garden.garden[i][j]) file += 1;
                        else file += 0;
                    }
                }
                System.out.println("Reading unlocked by monitor1");
                Garden.rwl.readLock().unlock();

                BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\OneDrive\\$Programming\\DistributedComputations\\src\\dc4lab\\task2\\gardenFile"));
                bw.write(file);
                bw.close();
                //sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
