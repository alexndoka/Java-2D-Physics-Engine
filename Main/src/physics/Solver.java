package physics;

public class Solver {

    public static void resolveCollision(Manifold m) {
        Body a = m.bodyA;
        Body b = m.bodyB;
        Vector2D normal = m.normal;

        Vector2D rv = b.velocity.subtract(a.velocity);

        double velAlongNormal = rv.dot(normal);

        if (velAlongNormal > 0) {
            return;
        }

        double e = Math.min(a.restitution, b.restitution);

        double j = -(1.0 + e) * velAlongNormal;
        j /= (a.invMass + b.invMass);

        Vector2D impulse = normal.scale(j);

        a.velocity = a.velocity.subtract(impulse.scale(a.invMass));
        b.velocity = b.velocity.add(impulse.scale(b.invMass));

        positionalCorrection(m);
    }

    private static void positionalCorrection(Manifold m) {
        Body a = m.bodyA;
        Body b = m.bodyB;

        double slop = 0.01; 
        double percent = 0.2; 

        double correctionScalar = Math.max(m.penetration - slop, 0.0) / (a.invMass + b.invMass) * percent;
        Vector2D correction = m.normal.scale(correctionScalar);

        a.position = a.position.subtract(correction.scale(a.invMass));
        b.position = b.position.add(correction.scale(b.invMass));
    }
}