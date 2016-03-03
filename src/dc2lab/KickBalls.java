package dc2lab;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Proggeo on 2/27/2016.
 */
public class KickBalls {
    static ArrayList<Monk> monks = new ArrayList<>();
    static int contestantsNumber = 32;

    public static void main(String[] args) {
        Random randomGenerator = new Random();

        for (int i = 0; i < contestantsNumber; i++) {
            monks.add(new Monk(randomGenerator.nextInt(1000), i));
        }

        Match match = new Match();
        match.participants = monks;
        match.run();

        System.out.println("Monk " + match.winner.name + " (power: " + match.winner.power + ") is the winner of KickBalls Cup");

    }
}

class Monk {
    int power;
    String name;
    Monk(int power, Integer id){
        this.power = power;
        this.name = id.toString();
    }
}

class Match extends Thread{

    Monk winner;
    ArrayList<Monk> participants;

    @Override
    public void run() {
        try {
            sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(participants.size()>2){
            Match match1 = new Match();
            Match match2 = new Match();
            match1.participants = new ArrayList<Monk>(participants.subList(0,participants.size()/2));
            match2.participants = new ArrayList<Monk>(participants.subList(participants.size()/2,participants.size()));
            match1.start();
            match2.start();
            while(match1.isAlive()||match2.isAlive()){

            };
            if(match1.winner.power>match2.winner.power) {
                winner = match1.winner;
                System.out.println("Monk " + winner.name + " (power " + winner.power + ") kicked balls of monk " + match2.winner.name + " (power: "+ match2.winner.power + ")");
            }
            else {
                winner = match2.winner;
                System.out.println("Monk " + winner.name + " (power " + winner.power + ") kicked balls of monk " + match1.winner.name + " (power: "+ match1.winner.power + ")");
            }
        }
        else if(participants.size()==2){
            try {
                sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (participants.get(0).power>participants.get(1).power) {
                winner = participants.get(0);
                System.out.println("Monk " + winner.name + " (power " + winner.power + ") kicked balls of monk " + participants.get(1).name + " (power: "+ participants.get(1).power + ")");
            }
            else {
                winner = participants.get(1);
                System.out.println("Monk " + winner.name + " (power " + winner.power + ") kicked balls of monk " + participants.get(0).name + " (power: "+ participants.get(0).power + ")");
            }
        }
        else if (participants.size()==1){
            winner = participants.get(0);
            System.out.println("Monk " + winner.name + " (power " + winner.power + ") automatically moved up.");
        }
        else{
            System.out.println("Empty list");
        }
    }
}
