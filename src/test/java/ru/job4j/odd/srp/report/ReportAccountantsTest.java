package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.currency.Currency;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;

import java.util.Calendar;


class ReportAccountantsTest {
    @Test
    public void whenAccountantReportGeneratedUsdSalaryConvertToEur() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anna", now, now, 150);
        Employee worker3 = new Employee("Alex", now, now, 80);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportAccountants reportAccountants = new ReportAccountants(store, Currency.USD, Currency.EUR);
        String expect = "Name; Salary; Currency;"
                + System.lineSeparator()
                + worker1.getName() + " "
                + 102.18 + " " + Currency.EUR
                + System.lineSeparator()
                + worker2.getName() + " "
                + 153.27 + " " + Currency.EUR
                + System.lineSeparator()
                + worker3.getName() + " "
                + 81.744 + " " + Currency.EUR
                + System.lineSeparator();
        Assertions.assertEquals(expect, reportAccountants.generate(em -> true));
    }
}
