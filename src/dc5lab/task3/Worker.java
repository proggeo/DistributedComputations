package dc5lab.task3;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class Worker implements Runnable {

    final int size=2;
    Worker neighbour1;
    Worker neighbour2;
    ArrayList<Integer> array = new ArrayList<>();
    Integer sum;
    Random random = new Random();

    Worker(){
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(2);
            System.out.println(number);
            array.add(number);
        }
    }

    @Override
    public void run() {
        try {
            boolean finished = false;
            while (!finished) {
                sum = 0;
                for (int i = 0; i < size; i++) {
                    sum+=array.get(i);
                }
                MainClass.cb.await();
                print();
                if((neighbour1.sum.equals(neighbour2.sum))&&(neighbour1.sum.equals(sum))){
                    finished=true;
                }
                System.out.println(sum);
                MainClass.cb.await();
                if(!finished){
                    int position = random.nextInt(size);
                    int incremented = array.get(position) + random.nextInt(2)*2-1;
                    array.set(position,incremented);
                }
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }


    void print(){
        String out = "";
        for (int i = 0; i < size; i++) {
            out+=" "+array.get(i);
        }
        System.out.println(out);
    }
}
