package dc1lab;

/**
 * Created by Proggeo on 2/17/2016.
 */
public class Incrementer implements Runnable {

    @Override
    public void run() {
        while (Main.position<90&&Main.position>10) {
            Main.position+=Main.step;
            Main.slider.setValue(Main.position.intValue());
            Main2.slider.setValue(Main.position.intValue());

        }
    }
}
