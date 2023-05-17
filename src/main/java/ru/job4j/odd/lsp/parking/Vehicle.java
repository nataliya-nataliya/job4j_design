package ru.job4j.odd.lsp.parking;

public abstract class Vehicle {
    int numberOfPlaces;
    String numberOfVehicle;

    public Vehicle(String numberOfVehicle) {
        this.numberOfVehicle = numberOfVehicle;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public String getNumberOfVehicle() {
        return numberOfVehicle;
    }
}
