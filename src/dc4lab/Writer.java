package dc4lab;

import java.io.*;

import static dc4lab.ReaderWriter.*;
import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/12/2016.
 */
class Writer implements Runnable {

    String entry = "";

    Writer(String entry) {
        this.entry = entry;
    }

    //TODO: add deletion

    @Override
    public void run() {

        rwl.writeLock().lock();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String file="";
            String line;
            while ((line = br.readLine()) != null) {
                file = file.concat(line+"\n");
            }
            file = file.concat(entry);
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(file);
            bw.close();
            br.close();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Writer: " + entry);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
