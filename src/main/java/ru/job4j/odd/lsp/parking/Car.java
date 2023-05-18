package ru.job4j.odd.lsp.parking;

public class Car extends Vehicle {
    public Car(String number) {
        super(number);
        numberOfPlaces = 1;
    }

    @Override
    public String toString() {
        return "Car{" + "numberOfPlaces=" + numberOfPlaces
                + ", numberOfVehicle='" + numberOfVehicle + '\'' + '}';
    }
}
