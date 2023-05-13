package ru.job4j.odd.lsp;

public class LivingRoom extends Room {
    public LivingRoom(DimensionRoom dimensionRoom) {
        super(dimensionRoom);
    }

    @Override
    public void setDimensions(DimensionRoom dimensionRoom) {
        this.dimensionRoom = dimensionRoom;
    }
}
