package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;

public interface ParkingManager<T extends Vehicle> {
    void delete(T vehicle);

    ArrayList<T> getList();

    boolean checkAdd(T vehicle);

    long numberOfEmptyParkingSpaces();

    void add(T vehicle);
}
