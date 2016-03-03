package dc1lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main {

    public static JSlider slider = new JSlider(0,100,50);
    public static Double position = 50.0;
    public static Double step = 0.0000001;
    public static int priority1=5;
    public static int priority2=5;


    public static void main(String[] args) {

            final Thread t1 = new Thread(new Incrementer());
            final Thread t2 = new Thread(new Decrementer());


            JFrame frame = new JFrame("JFrame Example");

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            JLabel label1 = new JLabel("5");
            JLabel label2 = new JLabel("5");


            JButton button1 = new JButton();
            JButton button2 = new JButton();
            JButton button3 = new JButton();
            JButton button4 = new JButton();

            button1.setText("+");
            button2.setText("-");
            button3.setText("+");
            button4.setText("-");

            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (priority1 > 9) return;
                    priority1++;
                    label1.setText("" + priority1);
                    t1.setPriority(priority1);
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(priority1<2)return;
                    priority1--;
                    label1.setText("" + priority1);
                    t1.setPriority(priority1);
                }
            });
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(priority2>9)return;
                    priority2++;
                    label2.setText(""+priority2);
                    t2.setPriority(priority2);
                }
            });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(priority2<2)return;
                priority2--;
                label2.setText("" + priority2);
                t2.setPriority(priority2);
            }
        });

        panel.add(label1);

            panel.add(button1);
            panel.add(button2);
            panel.add(slider);

        panel.add(label2);

        panel.add(button3);
        panel.add(button4);

            frame.add(panel);
            frame.setSize(230, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            t1.start();
            t2.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                t1.stop();
                t2.stop();
            }
        });

        }


}
