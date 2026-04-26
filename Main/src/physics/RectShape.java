package physics;

public class RectShape extends Shape {
    
    public double width, height;

    public RectShape(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public Shape.Type getType() {
        return Shape.Type.RECTANGLE;
    }

    @Override
    public double computeMass(double density) {
        return width * height * density;
    }

    @Override
    public double computeInertia(double mass) {
        return mass * (width * width + height * height) / 12.0;
    }

}
