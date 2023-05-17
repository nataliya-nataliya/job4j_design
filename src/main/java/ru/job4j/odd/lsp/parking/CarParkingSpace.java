package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;

public class CarParkingSpace implements ParkingManager {
    private final ArrayList<Vehicle> carArrayList;

    public CarParkingSpace(int numberOfCarPlaces) {
        carArrayList = new ArrayList<>(numberOfCarPlaces);
    }

    @Override
    public void add(Vehicle vehicle) {

    }

    @Override
    public void delete(Vehicle vehicle) {

    }

    @Override
    public ArrayList<Vehicle> getList() {
        return carArrayList;
    }
}
