package ru.job4j.odd.lsp;

/*
 * Violation of the Liskov substitution principle:
 * If you forget to do validation when overriding
 * setDimensions(DimensionRoom dimensionRoom),
 * then the code will run successfully.
 */

public class MainRoom {

    public static void main(String[] args) {
        Room livingRoom = new LivingRoom(new DimensionRoom(2, 4, 5));
        livingRoom.setDimensions(new DimensionRoom(-1, 5, 4));
    }
}
