package ru.job4j.odd.isp;


/*
This is a violation of the Interface Segregation Principle.
The abstraction "Food" is not defined correctly.
For example, if implementing an interface for "Salad",
the method "bake" needs to be mocked.
*/
public interface Food {
    void eat();

    void cook();

    void bake();
}
