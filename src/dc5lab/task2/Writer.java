package dc5lab.task2;

import java.util.Random;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class Writer extends Thread {

    String letters = new String();
    int size = 10;
    int id;
    Random random = new Random();

    Writer(int id) {
        this.id = id;
        for (int i = 0; i < size; i++) {
            letters += randomizer();
        }
    }

    @Override
    public void run() {
        try {
            while (!MainClass52.finished) {
                letters = replace(randomizer());
                MainClass52.cb.await();
                MainClass52.cb.await();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    String randomizer() {

        switch (random.nextInt(4)) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            default:
                return "A";
        }
    }

    String replace(String old) {
        String neo = "";
        switch (old) {
            case "A":
                neo = "C";
                break;
            case "B":
                neo = "D";
                break;
            case "C":
                neo = "A";
                break;
            case "D":
                neo = "B";
                break;
            default:
                neo = "";
        }
        String output = letters.replaceFirst(old, neo);
        System.out.println(output);
        return output;
    }

    Integer getACount (){
        int count=0;
        for (int i = 0; i < letters.length(); i++) {
            if(letters.charAt(i)=='A'||letters.charAt(i)=='B'){count++;}
        }
        System.out.println("Internal count:" + count);
        return count;
    }

}
