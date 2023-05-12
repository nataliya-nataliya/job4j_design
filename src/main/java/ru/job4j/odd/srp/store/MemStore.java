package ru.job4j.odd.srp.store;

import ru.job4j.odd.srp.model.Employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class MemStore implements Store {
    @XmlElement
    private List<Employee> employees = new ArrayList<>();

    public MemStore(List<Employee> employees) {
        this.employees = employees;
    }

    public MemStore() {
    }

    @Override
    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}
