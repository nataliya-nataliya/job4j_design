package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.currency.Currency;
import ru.job4j.odd.srp.currency.CurrencyConverter;
import ru.job4j.odd.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;

import java.util.function.Predicate;

public class ReportAccountants implements Report {
    private final Store store;
    private final Currency source;
    private final Currency target;

    public ReportAccountants(Store store, Currency source, Currency target) {
        this.store = store;
        this.source = source;
        this.target = target;
    }


    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary; Currency;")
                .append(System.lineSeparator());
        CurrencyConverter currency = new InMemoryCurrencyConverter();
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(currency.convert(source, employee.getSalary(), target)).append(" ")
                    .append(target)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
