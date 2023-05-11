package ru.job4j.odd.srp;

public interface Payment {
    public void processPayment(double amount);
    public void refundPayment(double amount);
}
