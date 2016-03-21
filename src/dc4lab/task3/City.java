package dc4lab.task3;

import java.util.ArrayList;

/**
 * Created by Proggeo on 3/15/2016.
 */
public class City {
    private int id;
    private String name;
    private ArrayList<City> connections = new ArrayList<>();
    private ArrayList<Integer> prices = new ArrayList<>();

    City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addConnection(City city, int price) {
        connections.add(city);
        prices.add(price);
        city.connections.add(this);
        city.prices.add(price);
    }

    public void deleteConnection(City city) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).id == city.id) {
                connections.remove(i);
                prices.remove(i);
            }
        }
        for (int i = 0; i < city.connections.size(); i++) {
            if (city.connections.get(i).id == this.id) {
                city.connections.remove(i);
                city.prices.remove(i);
            }
        }

    }

    public void changePrice(City city, int price) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).id == city.id) {
                prices.set(i, price);
                break;
            }
        }
        for (int i = 0; i < city.connections.size(); i++) {
            if (city.connections.get(i).id == this.id) {
                city.prices.set(i, price);
                break;
            }
        }
    }

    public void print() {
        System.out.println("Name: " + name);
        if (connections.size() == 0) {
            System.out.println("No connections");
            System.out.println();
            return;
        }
        System.out.println("Connections: \n-------------------");
        for (int i = 0; i < connections.size(); i++) {
            System.out.println(connections.get(i).name + ": " + prices.get(i));
        }
        System.out.println("-------------------\n");
    }

    public void findRoute(City destination) {
        ArrayList<City> route = new ArrayList<>();
        int price = findRoute(destination, route, 0);
        if (price == -1) {
            System.out.println("Route not found");
            return;
        }
        System.out.println("Route from " + this.name + " to " + destination.name + " found");
        System.out.println("Price: " + price);
        if (route.size() > 1) {
            System.out.println("Switches required:");
            for (int i = 1; i < route.size(); i++) {
                System.out.println(route.get(i).name);
            }
        }
    }

    public int findRoute(City destination, ArrayList<City> route, int price) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i) == destination) {
                return price + prices.get(i);
            }
        }
        for (int i = 0; i < connections.size(); i++) {
            boolean used = false;
            for (int j = 0; j < route.size(); j++) {
                if (route.get(j) == connections.get(i)) used = true;
            }
            if (used) continue;
            route.add(this);
            int result = connections.get(i).findRoute(destination, route, price + prices.get(i));
            if (result != -1) return result;
            route.remove(this);
        }
        return -1;
    }
}
