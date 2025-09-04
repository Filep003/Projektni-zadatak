package drawingApp;

import java.awt.*;

public class CircleShape extends Shape {
    private int x, y, r;

    public CircleShape(int x, int y, int r) {
        this.x = x; this.y = y; this.r = r;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
        g.setColor(edgeColor);
        g.drawOval(x - r, y - r, 2 * r, 2 * r);

        if (selected) {
            g.setColor(Color.RED);
            g.drawRect(x - r - 3, y - r - 3, 2 * r + 6, 2 * r + 6);
        }
    }

    @Override
    public boolean contains(int px, int py) {
        int dx = px - x;
        int dy = py - y;
        return dx * dx + dy * dy <= r * r;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getR() { return r; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setR(int r) { this.r = r; }
}