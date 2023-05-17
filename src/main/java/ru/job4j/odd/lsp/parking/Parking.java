package ru.job4j.odd.lsp.parking;

public class Parking {
    private final CarParkingSpace carParkingSpace;
    private final TruckParkingSpace truckParkingSpace;

    public Parking(CarParkingSpace carParkingSpace, TruckParkingSpace truckParkingSpace) {
        this.carParkingSpace = carParkingSpace;
        this.truckParkingSpace = truckParkingSpace;
    }

    public CarParkingSpace getCarParkingSpace() {
        return carParkingSpace;
    }

    public TruckParkingSpace getTruckParkingSpace() {
        return truckParkingSpace;
    }
}
