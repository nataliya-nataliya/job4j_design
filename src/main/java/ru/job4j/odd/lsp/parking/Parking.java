package ru.job4j.odd.lsp.parking;

public class Parking {
    private final ParkingSpace carParkingSpace;
    private final ParkingSpace truckParkingSpace;

    public Parking(ParkingSpace carParkingSpace, ParkingSpace truckParkingSpace) {
        this.carParkingSpace = carParkingSpace;
        this.truckParkingSpace = truckParkingSpace;
    }

    public ParkingSpace getCarParkingSpace() {
        return carParkingSpace;
    }

    public ParkingSpace getTruckParkingSpace() {
        return truckParkingSpace;
    }
}
