package dc4lab.task2;

/**
 * Created by Proggeo on 3/14/2016.
 */
class Monitor2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                String output = "";
                Garden.rwl.readLock().lock();
                System.out.println("Reading locked by monitor2");
                for (int i = 0; i < Garden.size; i++) {
                    output += (i + ": ");
                    for (int j = 0; j < Garden.size; j++) {
                        if (Garden.garden[i][j]) output += 1;
                        else output += 0;
                    }
                    output += "\n";
                }
                System.out.println(output);
                System.out.println("Reading unlocked by monitor2");
                Garden.rwl.readLock().unlock();

                //sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
