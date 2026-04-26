package physics;

public  class CircleShape extends Shape {
    public double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public Shape.Type getType() {
        return Shape.Type.CIRCLE;
    }

    @Override
    public double computeMass(double density) {
        return Math.PI * radius * radius * density;
    }

    @Override
    public double computeInertia(double mass) {
        return 0.5 * mass * radius * radius;
    }
    
}
