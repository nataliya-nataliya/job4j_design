package ru.job4j.odd.ocp;


/* This is an example of a Java class that violates
 * the Open Closed Principle
 * If we wanted to add a new figure, we would have to modify the area() method to add
 * another conditional statement for the other figure.
 */
public class Figure {
    public String name;

    public Figure(String name) {
        this.name = name;
    }

    public double area() {
        double area = 0;
        if (name.equals("triangle")) {
            double base = 2;
            double height = 1;
            area = 0.5 * base * height;
        } else if (name.equals("rectangle")) {
            double length = 2;
            double width = 3;
            area = length * width;
        }
        return area;
    }
}
