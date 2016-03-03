package dc2lab;

import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 2/27/2016.
 */
class Bees implements Runnable {

    List<Boolean> task;
    int id;
    int row = 0;

    public Bees(int id){
        this.id = id;
    }

    @Override
    public void run() {

        while (!WinnieBees.found&&!WinnieBees.tasks.isEmpty()) {
            try {
                WinnieBees.uncleSam.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            task = WinnieBees.tasks.remove(0);
            row = ++WinnieBees.rowsChecked;
            WinnieBees.uncleSam.release();

            for (int i = 0; i < task.size()&&!WinnieBees.found; i++) {
                if (task.get(i)) {
                    WinnieBees.found = true;
                    Date end = new Date();
                    System.out.println("Dear Queen, our division #" + this.id + " found and mercilessly executed f*cking bear on row " + row + ". Long live the Queen!");
                    System.out.println(end.getTime()- WinnieBees.begin.getTime());
                    return;
                }
            }
            System.out.println("Dear Queen, unfortunately our division #" + this.id + " failed to find f*cking bear on row " + row + ". We beg your mercy.");
            try {
                sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

