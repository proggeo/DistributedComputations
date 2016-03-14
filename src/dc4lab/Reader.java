package dc4lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static dc4lab.ReaderWriter.path;
import static dc4lab.ReaderWriter.rwl;
import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 3/12/2016.
 */
class Reader implements Runnable {

    private String number = "";
    private String firstName = "";
    private String lastName = "";

    Reader(String number) {
        this.number = number;
    }

    Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void run() {
        boolean byNumber;
        if (number.isEmpty()) byNumber = false;
        else byNumber = true;

        rwl.readLock().lock();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line;
            Data entry;
            while ((line = br.readLine()) != null) {
                entry = new Data(line);
                if(byNumber) {
                    if(process(entry,number))break;
                }
                else {
                    if(process(entry, firstName, lastName))break;
                }
            }
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        finally {
            rwl.readLock().unlock();
        }
    }

    private boolean process(Data entry, String number) {
        if (number.equals(entry.number)) {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Reader by number: " + entry.print());
            return true;
        }
        return false;
    }

    private boolean process(Data entry, String firstName, String lastName) {
        if ((firstName.equals(entry.firstName)) && (lastName.equals(entry.lastName))) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Reader by name: \n" + entry.print());
            return true;
        }
        return false;
    }
}


