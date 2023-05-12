package ru.job4j.odd.ocp;

/* This is an example of a Java class that violates
 * the Open Closed Principle.
 * If I want to create a WallMirror class,
 * then I cannot inherit from the Class Mirror,
 * since the wall mirror may not have a frame and,
 * accordingly, the colorOfFrame property,
 * which is in the Mirror superclass and
 * is used in the constructor
 */
public class Mirror {
    private String shape;
    private String colorOfFrame;

    public Mirror(String shape, String colorOfFrame) {
        this.shape = shape;
        this.colorOfFrame = colorOfFrame;
    }
}
