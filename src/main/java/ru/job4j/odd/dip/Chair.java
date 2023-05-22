package ru.job4j.odd.dip;


/*
This violates the Dependency Inversion Principle.
The "sit" method in the Chair class has a direct dependency
on the specific implementation of printing to the standard output
using System.out.println.
*/
public class Chair {
    String color;

    public Chair(String color) {
        this.color = color;
    }

    public void sit() {
        System.out.println("Sit on a " + color + " chair.");
    }
}
