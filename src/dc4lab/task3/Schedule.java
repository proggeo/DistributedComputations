package dc4lab.task3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Proggeo on 3/14/2016.
 */
public class Schedule {
    static ArrayList<City> cities = new ArrayList<>();
    static ArrayList<City> availableCities = new ArrayList<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);

    public static void main(String[] args) {
        //initialization start
        cities.add(new City(0, "Chicago"));
        cities.add(new City(1, "New York"));
        cities.add(new City(2, "Denver"));
        cities.add(new City(3, "Los Angeles"));
        cities.add(new City(4, "San Francisco"));
        cities.add(new City(5, "Dallas"));
        cities.add(new City(6, "Miami"));
        cities.add(new City(7, "Seattle"));
        cities.add(new City(8, "Washington DC"));
        cities.add(new City(9, "Anchorage"));
        cities.add(new City(10, "Detroit"));
        cities.add(new City(11, "Las Vegas"));
        cities.add(new City(12, "Cleveland"));

        availableCities.add(new City(13, "Portland"));
        availableCities.add(new City(14, "Phoenix"));
        availableCities.add(new City(15, "San Diego"));
        availableCities.add(new City(16, "Houston"));
        availableCities.add(new City(17, "Atlanta"));
        availableCities.add(new City(18, "Minneapolis"));
        availableCities.add(new City(19, "Cincinnati"));



        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int origin = random.nextInt(cities.size());
            int destination = random.nextInt(cities.size());
            while (destination == origin) destination = random.nextInt(cities.size());
            cities.get(origin).addConnection(cities.get(destination), random.nextInt(50));
        }
        for (int i = 0; i < cities.size(); i++) {
            cities.get(i).print();
        }

        // initialization end
        Thread priceChanger = new Thread(new PriceChanger());
        Thread connectionsChanger = new Thread(new ConnectionsChanger());
        Thread citiesChanger = new Thread(new CitiesChanger());
        Thread passenger1 = new Thread(new Passenger());
        Thread passenger2 = new Thread(new Passenger());
        Thread passenger3 = new Thread(new Passenger());
        Thread passenger4 = new Thread(new Passenger());
        Thread passenger5 = new Thread(new Passenger());

        priceChanger.start();
        connectionsChanger.start();
        citiesChanger.start();
        passenger1.start();
        passenger2.start();
        passenger3.start();
        passenger4.start();
        passenger5.start();


    }
}

