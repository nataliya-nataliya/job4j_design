package ru.job4j.odd.lsp;

public class DimensionRoom {

    private double length;
    private double width;
    private double height;

    public DimensionRoom(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
