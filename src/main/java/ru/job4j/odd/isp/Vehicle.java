package ru.job4j.odd.isp;

/*
This is a violation of the Interface Segregation Principle.
The abstraction "Vehicle" is not defined correctly.
For example, if implementing an interface for "Car",
the method "sail" needs to be mocked.
*/
public interface Vehicle {
    void start();

    void stop();

    void sail();
}
