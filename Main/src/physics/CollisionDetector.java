package physics;

public class CollisionDetector {
    public static Manifold checkCollision (Body a, Body b) {
        Shape.Type typeA = a.shape.getType();
        Shape.Type typeB = b.shape.getType();

        if (typeA == Shape.Type.CIRCLE && typeB == Shape.Type.CIRCLE) {
            return checkCircleVsCircle(a, b);
        } 
        return null;
    }

    private static Manifold checkCircleVsCircle (Body a, Body b) {
        CircleShape circleA = (CircleShape) a.shape;
        CircleShape circleB = (CircleShape) b.shape;
        
        Vector2D normal = b.position.subtract(a.position);

        double distSq = normal.dot(normal);
        double radiusSum = circleA.radius + circleB.radius;

        if (distSq >= radiusSum * radiusSum) {
            return null; 
        }

        double distance = Math.sqrt(distSq);
        double penetration;
        Vector2D contactPoint;

        if (distance == 0.0) {
            penetration = circleA.radius;
            normal = new Vector2D(1, 0); 
            contactPoint = a.position; 
        } else {
            penetration = radiusSum - distance;
            normal = normal.scale(1.0 / distance); 
            contactPoint = a.position.add(normal.scale(circleA.radius)); 
        }
        return new Manifold(a, b, normal, penetration, contactPoint);
    }
}
