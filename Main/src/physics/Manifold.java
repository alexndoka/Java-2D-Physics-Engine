package physics;

public class Manifold {
    public Body bodyA;
    public Body bodyB;

    public Vector2D normal; 

    public double penetration;

    public Vector2D contactPoint;

    public Manifold(Body bodyA, Body bodyB, Vector2D normal, double penetration, Vector2D contactPoint) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.normal = normal;
        this.penetration = penetration;
        this.contactPoint = contactPoint;
    }
}
