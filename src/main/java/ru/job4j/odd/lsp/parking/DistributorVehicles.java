package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;

public class DistributorVehicles {
    private Vehicle vehicle;

    public DistributorVehicles(Vehicle vehicle, Parking parking) {
        this.vehicle = vehicle;
        distribute(parking);

    }

    public void distribute(Parking parking) {
        ArrayList<Vehicle> cars = parking.getCarParkingSpace().getList();
        ArrayList<Vehicle> truckArrayList = parking.getTruckParkingSpace().getList();
    }
}
