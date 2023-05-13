package ru.job4j.odd.lsp;

public class Delivery {
    protected double pricePerKg;
    protected final double maxWeightOfOrder = 20;

    public Delivery(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public double getTotalPrice(double weightOfOrder) {
        if (weightOfOrder >= maxWeightOfOrder) {
            throw new IllegalArgumentException(
                    String.format("Order weight must be less than %f kg", maxWeightOfOrder));
        }
        return weightOfOrder * pricePerKg;
    }
}
