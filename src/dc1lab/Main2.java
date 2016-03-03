package dc1lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Proggeo on 2/21/2016.
 */
public class Main2 {
    public static JSlider slider = new JSlider(0,100,50);
    public static Integer semaphore = new Integer(0);  //0- free, 1- occupied with thread
    static Thread t1 = new Thread(new Incrementer());
    static Thread t2 = new Thread(new Decrementer());

    public static void main(String[] args) {

        JFrame frame = new JFrame("JFrame Example");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton buttonStart1 = new JButton();
        JButton buttonStop1 = new JButton();
        JButton buttonStart2 = new JButton();
        JButton buttonStop2 = new JButton();

        buttonStart1.setText("Start 1");
        buttonStop1.setText("Stop 1");
        buttonStart2.setText("Start 2");
        buttonStop2.setText("Stop 2");

        buttonStart1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaphore == 0) {
                    semaphore = 1;
                    t1 = new Thread(new Incrementer());
                    t1.start();
                    t1.setPriority(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Occupied by thread");
                }
            }
        });

        buttonStart2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaphore == 0) {
                    semaphore = 1;
                    t2 = new Thread(new Decrementer());
                    t2.start();
                    t2.setPriority(10);
                } else {
                    JOptionPane.showMessageDialog(null, "Occupied by thread");
                }
            }
        });

        buttonStop1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaphore == 1&&t1.isAlive()){
                    t1.stop();
                    semaphore = 0 ;
                }
            }
        });

        buttonStop2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semaphore == 1&&t2.isAlive()){
                    t2.stop();
                    semaphore = 0;
                }
            }
        });

        panel.add(buttonStart1);
        panel.add(buttonStop1);
        panel.add(slider);
        panel.add(buttonStart2);
        panel.add(buttonStop2);

        frame.add(panel);
        frame.setSize(240, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                t1.stop();
                t2.stop();
            }
        });

    }

}
