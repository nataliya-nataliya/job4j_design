package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;

public interface ParkingManager {
    void add(Vehicle vehicle);

    void delete(Vehicle vehicle);

    ArrayList<Vehicle> getList();
}
