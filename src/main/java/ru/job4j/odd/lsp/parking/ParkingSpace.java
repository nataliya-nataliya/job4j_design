package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ParkingSpace implements ParkingManager<Vehicle> {
    private final ArrayList<Vehicle> vehicleArrayList;

    public ParkingSpace(int numberOfCarPlaces) {
        if (numberOfCarPlaces < 0) {
            throw new IllegalArgumentException("The number of parking spaces can't' be a negative number");
        }
        vehicleArrayList = new ArrayList<>();
        vehicleArrayList.addAll(Collections.nCopies(numberOfCarPlaces, null));
    }

    @Override
    public void delete(Vehicle vehicle) {
        for (Vehicle v : vehicleArrayList) {
            if (v.equals(vehicle)) {
                vehicleArrayList.remove(vehicle);
            }
        }
    }

    @Override
    public ArrayList<Vehicle> getList() {
        return vehicleArrayList;
    }

    @Override
    public boolean checkAdd(Vehicle vehicle) {
        if (numberOfEmptyParkingSpaces() < vehicle.getNumberOfPlaces()) {
            return false;

        } else {
            add(vehicle);
            return true;
        }
    }

    @Override
    public long numberOfEmptyParkingSpaces() {
        return vehicleArrayList.stream().filter(Objects::isNull).count();
    }

    @Override
    public void add(Vehicle vehicle) {
        for (int i = 0; i < vehicleArrayList.size(); i++) {
            if (vehicleArrayList.get(i) == null) {
                for (int j = 0; j < vehicle.getNumberOfPlaces(); j++) {
                    vehicleArrayList.set(i, vehicle);
                    i++;
                }
            }
        }
    }
}
