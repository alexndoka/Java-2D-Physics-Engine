package physics;


public class Vector2D {
    public double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0, 0); 
    }

    public Vector2D add ( Vector2D other ) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract ( Vector2D other ) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D scale ( double scalar ) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D negate() {
        return this.scale(-1);
    }

    public double dot ( Vector2D other ) {
        return this.x * other.x + this.y * other.y;
    }

    public double cross ( Vector2D other ) {
        return x * other.y - y * other.x;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double len = length();
        return len < 1e-10 ? new Vector2D() : scale(1.0 / len);
    }

    public Vector2D perp() {
        return new Vector2D(-y, x);
    }
}

