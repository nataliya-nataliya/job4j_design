package ru.job4j.odd.lsp.parking;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class ParkingTest {
    @Test
    public void whenCarParksItParksInCarParkingSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(2);
        TruckParkingSpace truckParkingSpace = new TruckParkingSpace(2);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        DistributorVehicles distributorVehicles = new DistributorVehicles(new Car("Car number 1"), parking);
        Assertions.assertEquals(carParkingSpace.getList().get(0), (new Car("Car number 1")));

    }

    @Test
    public void whenTruckParksItParksInTruckParkingSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(2);
        TruckParkingSpace truckParkingSpace = new TruckParkingSpace(2);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        DistributorVehicles distributorVehicles = new DistributorVehicles(
                new Truck("Truck number 2", 2), parking);
        Assertions.assertEquals(truckParkingSpace.getList().get(0),
                (new Truck("Truck number 2", 2)));
    }

    @Test
    public void whenThereArentPlacesInTruckParkingSpaceTruckParksInCarParkingSpace() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(2);
        TruckParkingSpace truckParkingSpace = new TruckParkingSpace(0);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        DistributorVehicles distributorVehicles = new DistributorVehicles(
                new Truck("Truck number 2", 2), parking);
        Assertions.assertAll(
                () -> Assertions.assertEquals(new Truck("Truck number 2", 2),
                        carParkingSpace.getList().get(0)),
                () -> Assertions.assertEquals(new Truck("Truck number 2", 2),
                        carParkingSpace.getList().get(1))
        );
    }

    @Test
    public void whenThereArentPlacesInCarParking() {
        CarParkingSpace carParkingSpace = new CarParkingSpace(0);
        TruckParkingSpace truckParkingSpace = new TruckParkingSpace(0);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            new DistributorVehicles(new Car("Car number 1"), parking);
        });
    }
}
