package ru.job4j.odd.srd;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Example of a violation of the Single Responsibility Principle
 * Here formatBookingDetails() returns a string with formatted dates.
 * If the date formatting changes, then the method will also have to be changed.
 */

public class HotelBooking {
    private String guestName;
    private int roomNumber;
    private Date checkInDate;
    private Date checkOutDate;

    public String formatBookingDetails() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedCheckInDate = formatter.format(checkInDate);
        String formattedCheckOutDate = formatter.format(checkOutDate);
        return String.format("Name %s in room %d. Check-in date %s. Check-out date %s",
                guestName, roomNumber, formattedCheckInDate, formattedCheckOutDate);
    }
}
