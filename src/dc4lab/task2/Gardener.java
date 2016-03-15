package dc4lab.task2;

/**
 * Created by Proggeo on 3/14/2016.
 */
class Gardener implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < Garden.size; i++) {
                    for (int j = 0; j < Garden.size; j++) {
                        Garden.rwl.writeLock().lock();
                        System.out.println("Writing locked by gardener on cell:" + i + " " + j);
                        if (!Garden.garden[i][j]) {
                            Garden.garden[i][j] = true;
                        }
                        System.out.println("Writing unlocked by gardener");
                        Garden.rwl.writeLock().unlock();
                        //sleep(100);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
