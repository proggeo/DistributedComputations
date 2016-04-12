package dc6lab.task2;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Main {

    final static int N = 40;
    final static int M = 40;

    static Random random = new Random();

    static int [][] oldMatrix = new int[N][M];
    static int [][] newMatrix = new int[N][M];
    static int[] power = {0, 100, 150, 50};

    static CyclicBarrier CB = new CyclicBarrier(16);
    static CyclicBarrier CB2 = new CyclicBarrier(16);

    public static void main(String[] args) {

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                oldMatrix[i][j] = 0;
                newMatrix[i][j] = 0;
            }
        Interface Iface = new Interface();

    }

    static int NeighborsCount(int i, int j, int type) {
        int neighbors = 0;
        if (i == 0 && j == 0) {
            if (oldMatrix[i + 1][j] == type) neighbors += 1;
            if (oldMatrix[i + 1][j + 1] == type) neighbors += 1;
            if (oldMatrix[i][j + 1] == type) neighbors += 1;
        }
        else if (i == 0 && j == oldMatrix.length-1) {
            if (oldMatrix[i + 1][j] == type) neighbors += 1;
            if (oldMatrix[i + 1][j - 1] == type) neighbors += 1;
            if (oldMatrix[i][j - 1] == type) neighbors += 1;
        }
        else if (i== oldMatrix.length-1 && j == 0) {
            if (oldMatrix[i - 1][j] == type) neighbors += 1;
            if (oldMatrix[i - 1][j + 1] == type) neighbors += 1;
            if (oldMatrix[i][j + 1] == type) neighbors += 1;
        }
        else if(i == oldMatrix.length-1 && j == oldMatrix.length-1) {
            if (oldMatrix[i - 1][j] == type) neighbors += 1;
            if (oldMatrix[i - 1][j - 1] == type) neighbors += 1;
            if (oldMatrix[i][j - 1] == type) neighbors += 1;
        }
        else if (i == 0) {
            if (oldMatrix[i + 1][j] == type) neighbors += 1;
            if (oldMatrix[i + 1][j + 1] == type) neighbors += 1;
            if (oldMatrix[i][j + 1] == type) neighbors += 1;
            if (oldMatrix[i + 1][j - 1] == type) neighbors += 1;
            if (oldMatrix[i][j - 1] == type) neighbors += 1;
        }
        else if (i == oldMatrix.length-1) {
            if (oldMatrix[i - 1][j] == type) neighbors += 1;
            if (oldMatrix[i - 1][j + 1] == type) neighbors += 1;
            if (oldMatrix[i][j + 1] == type) neighbors += 1;
            if (oldMatrix[i - 1][j - 1] == type) neighbors += 1;
            if (oldMatrix[i][j - 1] == type) neighbors += 1;
        }
        else if (j == 0) {
            if (oldMatrix[i + 1][j] == type) neighbors += 1;
            if (oldMatrix[i + 1][j + 1] == type) neighbors += 1;
            if (oldMatrix[i][j + 1] == type) neighbors += 1;
            if (oldMatrix[i - 1][j] == type) neighbors += 1;
            if (oldMatrix[i - 1][j + 1] == type) neighbors += 1;
        }
        else if (j == oldMatrix.length-1) {
            if (oldMatrix[i + 1][j] == type) neighbors += 1;
            if (oldMatrix[i + 1][j - 1] == type) neighbors += 1;
            if (oldMatrix[i][j - 1] == type) neighbors += 1;
            if (oldMatrix[i - 1][j] == type) neighbors += 1;
            if (oldMatrix[i - 1][j - 1] == type) neighbors += 1;
        }
        else {
            if (oldMatrix[i + 1][j] == type) neighbors += 1;
            if (oldMatrix[i + 1][j - 1] == type) neighbors += 1;
            if (oldMatrix[i][j - 1] == type) neighbors += 1;
            if (oldMatrix[i - 1][j] == type) neighbors += 1;
            if (oldMatrix[i - 1][j - 1] == type) neighbors += 1;
            if (oldMatrix[i - 1][j + 1] == type) neighbors += 1;
            if (oldMatrix[i + 1][j + 1] == type) neighbors += 1;
            if (oldMatrix[i][j + 1] == type) neighbors += 1;
        }
        return neighbors;
    }
}
