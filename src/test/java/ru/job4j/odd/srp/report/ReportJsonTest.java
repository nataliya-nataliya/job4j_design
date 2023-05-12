package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;

import java.util.Calendar;

class ReportJsonTest {
    @Test
    public void whenReportJsonGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Anna", now, now, 100);
        store.add(worker);
        Report reportJson = new ReportJson(store);
        String expect = "[{\"name\":\"Anna\",\"hired\":"
                + "{\"year\":" + now.get(Calendar.YEAR)
                + ",\"month\":" + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":" + now.get(Calendar.MINUTE)
                + ",\"second\":" + now.get(Calendar.SECOND)
                + "},\"fired\":"
                + "{\"year\":" + now.get(Calendar.YEAR)
                + ",\"month\":" + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":" + now.get(Calendar.MINUTE)
                + ",\"second\":" + now.get(Calendar.SECOND)
                + "},\"salary\":100.0}]";
        Assertions.assertEquals(expect, reportJson.generate(em -> true));
    }
}
