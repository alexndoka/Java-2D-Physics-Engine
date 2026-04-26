package physics;

public abstract class Shape {
    public enum Type { CIRCLE, RECTANGLE }

    public abstract Type getType();

    public abstract double computeMass(double density);

    public abstract double computeInertia(double mass);
}
