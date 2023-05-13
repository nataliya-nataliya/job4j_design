package ru.job4j.odd.lsp;

public class OfficeDelivery extends Delivery {
    public OfficeDelivery(double pricePerKg) {
        super(pricePerKg);
    }

    @Override
    public double getTotalPrice(double weightOfOrder) {
        return weightOfOrder * pricePerKg;
    }
}
