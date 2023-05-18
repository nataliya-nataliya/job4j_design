package ru.job4j.odd.lsp.parking;

public class Truck extends Vehicle {
    public Truck(String numberOfVehicle, int numberOfPlaces) {
        super(numberOfVehicle);
        if (numberOfPlaces < 2) {
            throw new IllegalArgumentException("Truck needs at least 2 places");
        }
        this.numberOfPlaces = numberOfPlaces;
    }
}
