package dc6lab.task1;

import java.util.concurrent.BrokenBarrierException;

public class Handler implements Runnable{

    int i1,i2,j1,j2;

    public Handler(int i1, int j1, int i2, int j2){

        this.i1 = i1;
        this.i2 = i2;
        this.j1 = j1;
        this.j2 = j2;
    }

    @Override
    public void run() {

        while (true){
            for (int k = i1; k < i2; k ++){
                for (int l = j1; l < j2; l ++){
                    int neighbors1 = Main.NeighborsCount(k, l, 1);
                    if (neighbors1 < 2) {
                        Main.newMatrix[k][l] = 0;
                    }

                    if (neighbors1 > 3) {
                        Main.newMatrix[k][l] = 0;
                    }

                    if (neighbors1 == 2) {
                        Main.newMatrix[k][l] = Main.oldMatrix[k][l];
                    }

                    if (neighbors1 == 3) {
                        Main.newMatrix[k][l] = 1;
                    }
                }
            }

            try {
                Thread.sleep(5);
                Main.CB.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                //e.printStackTrace();
            }

            if ((i1 == 0) && (j1 == 0)) {
                for (int i = 0; i < Main.N; i++)
                    System.arraycopy(Main.newMatrix[i], 0, Main.oldMatrix[i], 0, Main.M);
                Interface.field.paint(Interface.field.getGraphics());
            }

            try {
                Thread.sleep(100);
                Main.CB2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            Main.CB.reset();
            Main.CB2.reset();
        }
    }
}
