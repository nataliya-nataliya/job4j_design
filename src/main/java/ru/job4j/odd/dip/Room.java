package ru.job4j.odd.dip;

/*
This violates the Dependency Inversion Principle because Room depends on low-level modules.
To fix this violation of the DIP, an abstraction should be introduced
between the Room class and its dependencies, Table and Chair.
For example, interface Furniture
*/
public class Room {
    Table table;
    Chair chair;

    public Room(Table table, Chair chair) {
        this.table = table;
        this.chair = chair;
    }
}
