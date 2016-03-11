package dc3lab;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by Proggeo on 3/11/2016.
 */
public class SmokerProblem {
    static boolean [] items = new boolean[3];

    static Semaphore uncleSam = new Semaphore(1);
    final static MiddleMan littleFinger = new MiddleMan();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            items[i]=true;
        }
        Smoker tobacco = new Smoker(true, false, false);
        Smoker paper = new Smoker(false,true,false);
        Smoker matches = new Smoker(false, false, true);

        littleFinger.start();
        tobacco.start();
        paper.start();
        matches.start();
    }

    public static void putOnTable (int index){
        for (int i = 0; i <3 ; i++) {
            if(i==index) items[i]=false;
            else items[i]=true;
        }
    }

    public static void clearTable(){
        for (int i = 0; i <3 ; i++) {
            items[i]=true;
        }
    }

    public static String print(boolean tobacco, boolean paper, boolean matches) {
        String result = "";
        if(tobacco){
            result = result.concat("tobacco ");
        }
        if(paper){
            result = result.concat("paper ");
        }
        if(matches){
            result = result.concat("matches ");
        }
        return result;
    }

}

class Smoker extends Thread{
    boolean [] items = new boolean[3];

    public Smoker(boolean tobacco, boolean paper, boolean matches) {
        items[0] = tobacco;
        items[1] = paper;
        items[2] = matches;
    }

    @Override
    public void run(){
        int index = 0;
        for (int i = 0; i <3 ; i++) {
            if(items[i])index = i;
        }
        while (true){
            try {
                SmokerProblem.uncleSam.acquire();
                if (!SmokerProblem.items[index]){
                    System.out.println("Smoker(" + SmokerProblem.print(items[0], items[1], items[2]) + ") gets to smoke");
                    sleep(1000);
                    System.out.println("Smoker(" + SmokerProblem.print(items[0], items[1], items[2]) + ") finished smoking and notifies middleman");
                    SmokerProblem.clearTable();
                    synchronized (SmokerProblem.littleFinger) {
                        SmokerProblem.littleFinger.notify();
                    }
                }
                SmokerProblem.uncleSam.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MiddleMan extends Thread {

    @Override
    public void run(){
        int index;
        while(true){
            try {
                SmokerProblem.uncleSam.acquire();
                Random randomGenerator = new Random();
                index = randomGenerator.nextInt(3);
                SmokerProblem.putOnTable(index);
                System.out.println("MiddleMan put on the table " + SmokerProblem.print(SmokerProblem.items[0],SmokerProblem.items[1], SmokerProblem.items[2]));
                SmokerProblem.uncleSam.release();
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}