package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.formatter.ReportDateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;

import java.util.Calendar;


class ReportProgrammersTest {
    @Test
    public void whenProgrammersReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anna", now, now, 150);
        Employee worker3 = new Employee("Alex", now, now, 80);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report programmers = new ReportProgrammers(store, parser, "; ");
        String expect = "Name; Hired; Fired; Salary"
                + System.lineSeparator()
                + worker1.getName() + "; " + parser.parse(worker1.getHired()) + "; "
                + parser.parse(worker1.getFired()) + "; " + worker1.getSalary()
                + System.lineSeparator()
                + worker2.getName() + "; " + parser.parse(worker2.getHired()) + "; "
                + parser.parse(worker2.getFired()) + "; " + worker2.getSalary()
                + System.lineSeparator()
                + worker3.getName() + "; " + parser.parse(worker3.getHired()) + "; "
                + parser.parse(worker3.getFired()) + "; " + worker3.getSalary()
                + System.lineSeparator();
        Assertions.assertEquals(expect, programmers.generate(em -> true));
    }
}
