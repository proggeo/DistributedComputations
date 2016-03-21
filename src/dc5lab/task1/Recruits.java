package dc5lab.task1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class Recruits extends Thread {
    int size;
    int id;
    ArrayList<Boolean> soldiers;
    ArrayList<Boolean> newlist;
    Boolean stable = false;


    Recruits left = null;
    Recruits right = null;

    Recruits(int count, int id) {
        this.id = id;
        size = count;
        Random random = new Random();
        soldiers = new ArrayList<>(size);
        newlist = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            soldiers.add(random.nextBoolean());//true left, false right
            newlist.add(false);
        }
        print();
    }

    @Override
    public void run() {
        try {
            while (!MainClass.stable) {
                stable = true;

                if (left != null && soldiers.get(0) && !left.soldiers.get(left.soldiers.size() - 1)) {
                    stable = false;
                    newlist.set(0, false);
                } else
                    newlist.set(0, soldiers.get(0));

                if (right != null && !soldiers.get(size - 1) && right.soldiers.get(0)) {
                    stable = false;
                    newlist.set(size - 1, true);
                } else newlist.set(size - 1, soldiers.get(size - 1));


                for (int i = 1; i < size - 1; i++) {
                    if (soldiers.get(i) && !soldiers.get(i - 1)) {
                        stable = false;
                        newlist.set(i, false);
                        newlist.set(i - 1, true);
                    } else if (!soldiers.get(i) && soldiers.get(i + 1)) {
                        stable = false;
                        newlist.set(i, true);
                        newlist.set(i + 1, false);
                    } else {
                        newlist.set(i, soldiers.get(i));
                    }

                }
                MainClass.cb.await();
                soldiers = new ArrayList<>(newlist);
                MainClass.cb.await();
                print();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setRight(Recruits right) {
        this.right = right;
        right.left = this;
    }

    public void setLeft(Recruits left) {
        this.left = left;
        left.right = this;
    }

    void print() {
        String out = "" + id + ": ";
        for (int i = 0; i < size; i++) {
            if (soldiers.get(i)) out += "<";
            else out += ">";
        }
        System.out.println(out);
    }
}
