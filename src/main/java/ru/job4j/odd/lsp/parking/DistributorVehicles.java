package ru.job4j.odd.lsp.parking;

public class DistributorVehicles {
    private final Vehicle vehicle;

    public DistributorVehicles(Vehicle vehicle, Parking parking) {
        this.vehicle = vehicle;
        distribute(parking);

    }

    /**
     * This method distributes vehicles.
     * A car can only be added to a space for cars.
     * If you add a truck to the space for trucks, and there are no places,
     * then the truck will be added to the spaces for cars
     *
     * @throws UnsupportedOperationException if you are trying to add a car
     *                                       with a number already added
     */

    public void distribute(Parking parking) {
        ParkingSpace carParkingSpace = parking.getCarParkingSpace();
        ParkingSpace truckParkingSpace = parking.getTruckParkingSpace();
        if (vehicle instanceof Truck) {
            if (!truckParkingSpace.checkAdd(vehicle)) {
                if (truckParkingSpace.getList().contains(vehicle)) {
                    throw new UnsupportedOperationException(
                            String.format("This %s is already in the parking lot", vehicle.getNumberOfVehicle()));
                }
                if (!carParkingSpace.checkAdd(vehicle)) {
                    if (!carParkingSpace.checkAdd(vehicle)) {
                        throw new UnsupportedOperationException("No places. You can't' add a vehicle");
                    }
                }
            }
        } else {
            if (carParkingSpace.getList().contains(vehicle)) {
                throw new UnsupportedOperationException(
                        String.format("This %s is already in the parking lot", vehicle.getNumberOfVehicle()));
            }
            if (!carParkingSpace.checkAdd(vehicle)) {
                throw new UnsupportedOperationException("No places. You can't' add a vehicle");
            }
        }
    }
}
