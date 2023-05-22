package ru.job4j.odd.dip;

import java.util.HashMap;

/* This violates the Dependency Inversion Principle.
The class TicketStore directly depends on a particular HashMap implementation,
making it related to that particular data structure.
To fix this violation, implement an abstraction for storing tickets
and implement it through the interface.
*/

public class TicketStore {
    private HashMap<Integer, Concert> ticketStore;

    public boolean add(Concert concert) {
        /* some code */
        return false;
    }
}
