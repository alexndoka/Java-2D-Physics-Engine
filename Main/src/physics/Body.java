package physics;

import java.awt.Color;

public class Body {

    public Vector2D position;
    public Vector2D velocity;
    public Vector2D force;

    public double angle;
    public double angularVelocity;
    public double torque;

    public double mass;
    public double invMass;
    public double inertia;
    public double invInertia;

    public double restitution;
    public double staticFriction;
    public double dynamicFriction;

    public Shape shape;
    public Color color;
    public boolean isStatic;

}
