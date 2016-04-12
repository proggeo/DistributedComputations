package dc6lab;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class Processor implements Runnable {

    int beginX;
    int beginY;
    int endX;
    int endY;

    Processor(int x1, int y1, int x2, int y2) {
        beginX = x1;
        beginY = y1;
        endX = x2;
        endY = y2;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = beginX; i < endX; i++) {
                    for (int j = beginY; j < endY; j++) {
                        process(i, j);
                    }
                }
                GameLife.cb.await();
                write();
                GameLife.cb.await();

            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    void process(int i, int j) {
        int count = 0;

        if (i > 0 && j > 0 && GameLife.field[i - 1][j - 1] > 0) count++;
        if (i > 0 && GameLife.field[i - 1][j] > 0) count++;
        if (j < GameLife.size - 1 && i > 0 && GameLife.field[i - 1][j + 1] > 0) count++;
        if (j > 0 && GameLife.field[i][j - 1] > 0) count++;
        if (j < GameLife.size - 1 && GameLife.field[i][j + 1] > 0) count++;
        if (i < GameLife.size - 1 && j > 0 && GameLife.field[i + 1][j - 1] > 0) count++;
        if (i < GameLife.size - 1 && GameLife.field[i + 1][j] > 0) count++;
        if (i < GameLife.size - 1 && j < GameLife.size - 1 && GameLife.field[i + 1][j + 1] > 0) count++;

        if (GameLife.field[i][j] > 0) {
            if (count < 2 || count > 3) GameLife.nextField[i][j] = 0;
            else GameLife.nextField[i][j] = GameLife.field[i][j];
        } else if (count == 3) {
            GameLife.nextField[i][j] = 1;
        } else {
            GameLife.nextField[i][j] = GameLife.field[i][j];
        }
    }

    void write() {
        for (int i = beginX; i < endX; i++) {
            for (int j = beginY; j < endY; j++) {
                GameLife.field[i][j] = GameLife.nextField[i][j];
            }
        }
    }
}
