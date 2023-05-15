package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class AbstractStore implements Store {

    @Override
    public double remainingShelfLifeCoefficient(Food product) {
        return (double) ChronoUnit.DAYS.between(LocalDate.now(), product.getExpiryDate())
                / (double) ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
    }
}
