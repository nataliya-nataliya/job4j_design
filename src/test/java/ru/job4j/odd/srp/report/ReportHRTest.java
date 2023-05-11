package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;

import java.util.Calendar;

class ReportHRTest {
    @Test
    public void whenHrReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anna", now, now, 150);
        Employee worker3 = new Employee("Alex", now, now, 80);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report hr = new ReportHR(store);
        String expect = "Name; Salary;"
                + System.lineSeparator()
                + worker2.getName() + " " + worker2.getSalary()
                + System.lineSeparator()
                + worker1.getName() + " " + worker1.getSalary()
                + System.lineSeparator()
                + worker3.getName() + " " + worker3.getSalary()
                + System.lineSeparator();
        Assertions.assertEquals(expect, hr.generate(em -> true));
    }
}
