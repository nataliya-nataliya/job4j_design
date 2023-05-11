package ru.job4j.odd.srp;

/*
 * Example of a violation of the Single Responsibility Principle
 * Payment interface has more than one responsibility:
 * it processes payments and refunds payments using a debit card.
 * This violates the Single Responsibility Principle.
 */
public class DebitCard implements Payment {
    @Override
    public void processPayment(double amount) {

    }

    @Override
    public void refundPayment(double amount) {

    }
}
