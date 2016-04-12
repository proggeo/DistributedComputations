package dc6lab.task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Interface extends JFrame {
    public JPanel panel;
    public static JPanel field;
    public JButton start;

    public Interface(){
        super("Life");
        createGUI();
    }

   public void createGUI(){

       panel = new JPanel();
       this.setSize(700, 550);
       this.setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       panel.setLayout(null);
       panel.setBackground(Color.WHITE);
       this.add(panel);

       field = new JPanel(){
           @Override
           public void paint(Graphics g) {

               for (int i = 0; i < 41; i ++){
                   g.setColor(Color.GRAY);
                   g.drawLine(0, 10 * i, 400, 10* i);
                   g.drawLine(10 * i, 0, 10 * i, 400);
               }



               for (int i = 0; i < Main.oldMatrix.length; i++){
                   for (int j = 0; j < Main.oldMatrix.length; j++){
                       if (Main.oldMatrix[i][j] == 0){
                           g.setColor(Color.WHITE);
                           g.fillOval(i*10 + 2, j*10+2, 6, 6);
                       }
                       else if (Main.oldMatrix[i][j] == 1){
                           g.setColor(Color.BLACK);
                           g.fillOval(i*10 + 3, j*10+3, 4, 4);
                       }
                       else if (Main.oldMatrix[i][j] == 2){
                           g.setColor(Color.YELLOW);
                           g.fillOval(i*10 + 3, j*10+3, 4, 4);
                       }
                       else if (Main.oldMatrix[i][j] == 3){
                           g.setColor(Color.MAGENTA);
                           g.fillOval(i*10 + 3, j*10+3, 4, 4);
                       }
                   }
               }

           }
       };
       field.setBounds(150, 20, 400, 400);
       //field.setBackground(Color.WHITE);
       panel.add(field);
       start = new JButton("Start");

       start.setBounds(20, 100, 80, 30);
       panel.add(start);

       Random random = new Random();
       for (int i = 0; i < Main.N; i++)
           for (int j = 0; j < Main.M; j++)
               Main.oldMatrix[i][j] = Math.abs(random.nextInt() % 4);
       field.paint(field.getGraphics());

       start.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Thread h1 = new Thread(new Handler(0, 0, 10, 10));
               Thread h2 = new Thread(new Handler(0, 10, 10, 20));
               Thread h3 = new Thread(new Handler(0, 20, 10, 30));
               Thread h4 = new Thread(new Handler(0, 30, 10, 40));

               Thread h5 = new Thread(new Handler(10, 0, 20, 10));
               Thread h6 = new Thread(new Handler(10, 10, 20, 20));
               Thread h7 = new Thread(new Handler(10, 20, 20, 30));
               Thread h8 = new Thread(new Handler(10, 30, 20, 40));

               Thread h9 = new Thread(new Handler(20, 0, 30, 10));
               Thread h10 = new Thread(new Handler(20, 10, 30, 20));
               Thread h11 = new Thread(new Handler(20, 20, 30, 30));
               Thread h12 = new Thread(new Handler(20, 30, 30, 40));

               Thread h13 = new Thread(new Handler(30, 0, 40, 10));
               Thread h14 = new Thread(new Handler(30, 10, 40, 20));
               Thread h15 = new Thread(new Handler(30, 20, 40, 30));
               Thread h16 = new Thread(new Handler(30, 30, 40, 40));

               h1.start();
               h2.start();
               h3.start();
               h4.start();
               h5.start();
               h6.start();
               h7.start();
               h8.start();
               h9.start();
               h10.start();
               h11.start();
               h12.start();
               h13.start();
               h14.start();
               h15.start();
               h16.start();
           }
       });
    }
}
