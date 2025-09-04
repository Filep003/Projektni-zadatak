package stackApp;

public class Circle {
    private final int x;       
    private final int y;       
    private final int radius;  

    public Circle(int x, int y, int radius) {
        if (x <= 0 || y <= 0 || radius <= 0) {
            throw new IllegalArgumentException("Values must be greater than zero");
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadius() { return radius; }

    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle [x=%d, y=%d, r=%d]", x, y, radius);
    }
}