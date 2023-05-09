package ru.job4j.odd.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Assertions.assertEquals(ticket, new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        Assertions.assertTrue(sessions.contains(session));
    }
    @Test
    public void whenDoesNotAddSessionThenItDoesNotExistBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        List<Session> sessions = cinema.find(ses -> true);
        Assertions.assertFalse(sessions.contains(session));
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Assertions.assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, -1, 1, date));

    }
    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Assertions.assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 1, -1, date));

    }
    @Test
    public void whenBuyOnInvalidDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -1);
        date.set(Calendar.DAY_OF_MONTH, Math.max(date.get(Calendar.DAY_OF_MONTH), 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> cinema.buy(account, 1, 1, date));
    }
}
