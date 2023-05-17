package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;

public class TruckParkingSpace implements ParkingManager {
    private final ArrayList<Vehicle> truckArrayList;

    public TruckParkingSpace(int numberOfCarPlaces) {
        truckArrayList = new ArrayList<>(numberOfCarPlaces);
    }


    @Override
    public void add(Vehicle vehicle) {

    }

    @Override
    public void delete(Vehicle vehicle) {

    }

    @Override
    public ArrayList<Vehicle> getList() {
        return null;
    }
}
