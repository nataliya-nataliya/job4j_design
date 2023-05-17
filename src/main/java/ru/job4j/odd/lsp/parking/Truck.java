package ru.job4j.odd.lsp.parking;

public class Truck extends Vehicle {
    public Truck(String numberOfVehicle, int numberOfPlaces) {
        super(numberOfVehicle);
        this.numberOfPlaces = numberOfPlaces;
    }
}
