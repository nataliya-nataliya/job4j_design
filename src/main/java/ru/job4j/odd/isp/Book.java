package ru.job4j.odd.isp;

/*
This is a violation of the Interface Segregation Principle.
The abstraction "Book" is not defined correctly.
For example, if implementing an interface for "PaperBook",
the methods "turnOnBook" and "turnOffBook" need to be mocked.
*/
public interface Book {
    void read();

    void turnOnBook();

    void turnOffBook();
}
