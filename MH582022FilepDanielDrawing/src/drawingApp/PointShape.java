package drawingApp;

import java.awt.*;

public class PointShape extends Shape {
    private int x, y;

    public PointShape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(edgeColor);
        g.fillRect(x - 2, y - 2, 4, 4);
        if (selected) {
            g.setColor(Color.RED);
            g.drawRect(x - 4, y - 4, 8, 8);
        }
    }

    @Override
    public boolean contains(int px, int py) {
        return Math.abs(px - x) <= 3 && Math.abs(py - y) <= 3;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}