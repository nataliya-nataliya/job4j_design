package ru.job4j.odd.lsp.parking;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


class ParkingTest {
    @Test
    public void whenCarsParkTheyParksInCarParkingSpace() {
        ParkingSpace carParkingSpace = new ParkingSpace(2);
        ParkingSpace truckParkingSpace = new ParkingSpace(2);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        DistributorVehicles distributorCar1 = new DistributorVehicles(new Car("Car number 1"), parking);
        DistributorVehicles distributorCar2 = new DistributorVehicles(new Car("Car number 2"), parking);
        assertAll(
                () ->
                        assertEquals((new Car("Car number 1")), carParkingSpace.getList().get(0)),
                () ->
                        assertEquals((new Car("Car number 2")), carParkingSpace.getList().get(1)),
                () ->
                        assertEquals(0, truckParkingSpace.getList().stream()
                                .filter(Objects::nonNull)
                                .count()));
    }

    @Test
    public void whenTruckParksItParksInTruckParkingSpace() {
        ParkingSpace carParkingSpace = new ParkingSpace(2);
        ParkingSpace truckParkingSpace = new ParkingSpace(3);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        DistributorVehicles distributorVehicles = new DistributorVehicles(
                new Truck("Truck number 2", 2), parking);
        assertAll(
                () -> assertEquals(truckParkingSpace.getList().get(0),
                        (new Truck("Truck number 2", 2))),
                () -> assertEquals(truckParkingSpace.getList().get(1),
                        (new Truck("Truck number 2", 2))),
                () -> assertNull(truckParkingSpace.getList().get(2)),
                () ->
                        assertEquals(0, carParkingSpace.getList().stream()
                                .filter(Objects::nonNull)
                                .count()));
    }

    @Test
    public void whenThereArentPlacesInTruckParkingSpaceTruckParksInCarParkingSpace() {
        ParkingSpace carParkingSpace = new ParkingSpace(2);
        ParkingSpace truckParkingSpace = new ParkingSpace(0);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        DistributorVehicles distributorVehicles = new DistributorVehicles(
                new Truck("Truck number 2", 2), parking);
        assertAll(
                () -> assertEquals(new Truck("Truck number 2", 2),
                        carParkingSpace.getList().get(0)),
                () -> assertEquals(new Truck("Truck number 2", 2),
                        carParkingSpace.getList().get(1)),
                () ->
                        assertEquals(0, truckParkingSpace.getList().stream()
                                .filter(Objects::nonNull)
                                .count())
        );
    }

    @Test
    public void whenThereArentPlacesInCarParkingSpaceThenUnsupportedOperationException() {
        ParkingSpace parkingSpace = new ParkingSpace(0);
        ParkingSpace truckParkingSpace = new ParkingSpace(0);
        Parking parking = new Parking(parkingSpace, truckParkingSpace);
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                new DistributorVehicles(new Car("Car number 1"), parking));
    }

    @Test
    public void whenThereArentPlacesInTruckParkingSpaceThenUnsupportedOperationException() {
        ParkingSpace carParkingSpace = new ParkingSpace(0);
        ParkingSpace truckParkingSpace = new ParkingSpace(0);
        Parking parking = new Parking(carParkingSpace, truckParkingSpace);
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
                new DistributorVehicles(new Truck("Truck number 1", 2), parking));
    }

    @Test
    public void whenNumberOfPlacesIsNegativeNumberThenIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new ParkingSpace(-1));
    }

    @Test
    public void whenTruckHasLessThan2NumberOfPlacesThenIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new Truck("Truck number 1", 1));
    }

    @Test
    public void whenAddingCarThatIsAlreadyInParkingSpaceThenUnsupportedOperationException() {
        ParkingSpace parkingSpace = new ParkingSpace(2);
        ParkingSpace truckParkingSpace = new ParkingSpace(2);
        Parking parking = new Parking(parkingSpace, truckParkingSpace);
        DistributorVehicles distributorCar = new DistributorVehicles(new Car("Car number 1"), parking);
        Assertions.assertThrows(UnsupportedOperationException.class, () ->
            new DistributorVehicles(new Car("Car number 1"), parking));
    }
}
