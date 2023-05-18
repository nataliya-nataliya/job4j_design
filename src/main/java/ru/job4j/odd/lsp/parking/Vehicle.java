package ru.job4j.odd.lsp.parking;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(numberOfVehicle, vehicle.numberOfVehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfVehicle);
    }

    @Override
    public String toString() {
        return "Vehicle{"
                + "numberOfPlaces=" + numberOfPlaces
                + ", numberOfVehicle='" + numberOfVehicle + '\'' + '}';
    }
}
