package ru.job4j.odd.lsp;

public class Building {
    protected int numberOfBuildingEntrances;

    public Building(int numberOfBuildingEntrances) {
        this.numberOfBuildingEntrances = numberOfBuildingEntrances;
    }
    public void enterTheBuilding() {
        if (numberOfBuildingEntrances < 1) {
            throw new IllegalArgumentException("the number of building entrances cannot be less than 1");
        }
    }
}
