package ru.job4j.odd.lsp;


/*
 * Violation of the Liskov substitution principle:
 * The OfficeDelivery subclass does not throw an exception for orders
 * that exceed the maximum weight, unlike the Delivery superclass.
 */
public class MainDelivery {
    public static void main(String[] args) {
        Delivery officeDelivery = new OfficeDelivery(10.0);
        officeDelivery.getTotalPrice(20);
    }
}
