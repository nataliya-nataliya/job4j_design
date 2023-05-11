package ru.job4j.odd.srp;

/*
 * Example of a violation of the Single Responsibility Principle
 * Class is responsible for both calculating the sum of two numbers
 * and printing the result to the console.
 */

public class SumCalculator {
    public static void sumAndPrint(int a, int b) {
        System.out.println(a + b);
    }
}
