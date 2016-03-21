package dc5lab.task1;

import java.util.ArrayList;

/**
 * Created by Proggeo on 3/21/2016.
 */
public class Prapor implements Runnable{

    ArrayList<Recruits> recruits;
    boolean stable;

    Prapor(ArrayList<Recruits> recruits){
        this.recruits = recruits;
    }

    @Override
    public void run() {
        try {
            while(!MainClass.stable){
                MainClass.cb.await();
                stable = true;
                for (int i = 0; i < recruits.size(); i++) {
                    if(!recruits.get(i).stable)stable=false;
                }
                System.out.println(stable);
                if(stable) MainClass.stable=true;
                MainClass.cb.await();
            }
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }
}
