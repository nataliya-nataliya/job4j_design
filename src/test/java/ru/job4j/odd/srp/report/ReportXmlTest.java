package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

class ReportXmlTest {
    @Test
    public void whenReportXmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String nowFormattedDate = simpleDateFormat.format(now.getTime());
        System.out.println(nowFormattedDate);
        Employee worker = new Employee("Anna", now, now, 100);
        store.add(worker);
        Report reportXml = new ReportXml<>(store, MemStore.class);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><employees><employees>"
                + "<name>Anna</name><hired>" + nowFormattedDate + "</hired><fired>"
                + nowFormattedDate + "</fired><salary>100.0</salary></employees></employees>";
        Assertions.assertEquals(expect, reportXml.generate(em -> true));
    }
}
