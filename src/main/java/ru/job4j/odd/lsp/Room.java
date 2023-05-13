package ru.job4j.odd.lsp;

public class Room {
    protected DimensionRoom dimensionRoom;

    public Room(DimensionRoom dimensionRoom) {
        validate(dimensionRoom);
        this.dimensionRoom = dimensionRoom;
    }

    public DimensionRoom getDimensions() {
        return dimensionRoom;
    }

    public void setDimensions(DimensionRoom dimensionRoom) {
        validate(dimensionRoom);
        this.dimensionRoom = dimensionRoom;
    }
    protected void validate(DimensionRoom dimensionRoom) {
        if (dimensionRoom.getHeight() <= 0 || dimensionRoom.getLength() <= 0 || dimensionRoom.getWidth() <= 0) {
            throw new IllegalArgumentException("Dimension cannot be less than or equal to 0");
        }
    }
}
