package ru.job4j.odd.ocp;


/* This is an example of a Java class that violates
 * the Open Closed Principle
 * We cannot use inheritance here. Because when inherited,
 * the state of the superclass object is inherited.
 * Class Cat cannot have the same properties as the Class Dog,
 * because the Cat "is not" a Dog (the "is A" inheritance relation),
 * but they can both make a sound
 */
public class Dog {
    public String sound() {
        return "Woof woof";
    }
}
