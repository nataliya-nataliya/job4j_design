package ru.job4j.odd.lsp;

public class ShopBuilding extends Building {
    public ShopBuilding(int numberOfBuildingEntrances) {
        super(numberOfBuildingEntrances);
    }

    @Override
    public void enterTheBuilding() {
        if (numberOfBuildingEntrances < 2) {
            throw new IllegalArgumentException("The store cannot have less than 2 exits: main and emergency exit");
        }
    }
}
