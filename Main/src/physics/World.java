package physics;

import java.util.ArrayList;
import java.util.List;

public class World {
    
    public List<Body> bodies;
    public Vector2D gravity;

    public World(Vector2D gravity) {
        this.bodies = new ArrayList<>();
        this.gravity = gravity;
    } 

    public void addBody(Body body) {
        bodies.add(body);
    }

    public void removeBody(Body body) {
        bodies.remove(body);
    }

    public void step(double dt) {

        for (Body body : bodies) {
            if (body.isStatic) continue;
            
            body.velocity = body.velocity.add(gravity.scale(dt));
            Vector2D acceleration = body.force.scale(body.invMass);
            body.velocity = body.velocity.add(acceleration.scale(dt));
            body.angularVelocity += body.torque * body.invInertia * dt;
        }

        for (int i = 0; i < bodies.size(); i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                Body a = bodies.get(i);
                Body b = bodies.get(j);

                if (a.isStatic && b.isStatic) continue;

                Manifold m = CollisionDetector.checkCollision(a, b);

                if (m != null) {
                    Solver.resolveCollision(m);
                }
            }
        }

        for (Body body : bodies) { 
            if (body.isStatic) continue;
            
            body.position = body.position.add(body.velocity.scale(dt));
            body.angle += body.angularVelocity * dt;

            body.force = new Vector2D(0, 0);
            body.torque = 0;
        }
    }
}