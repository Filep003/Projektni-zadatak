package drawingApp;

import java.awt.*;

public class LineShape extends Shape {
    private int x1, y1, x2, y2;

    public LineShape(int x1, int y1, int x2, int y2) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(edgeColor);
        g.drawLine(x1, y1, x2, y2);
        if (selected) {
            g.setColor(Color.RED);
            g.drawRect(x1 - 3, y1 - 3, 6, 6);
            g.drawRect(x2 - 3, y2 - 3, 6, 6);
        }
    }

    @Override
    public boolean contains(int px, int py) {
        double dist = ptLineDist(x1, y1, x2, y2, px, py);
        return dist <= 3;
    }

    private double ptLineDist(int x1, int y1, int x2, int y2, int px, int py) {
        double A = px - x1;
        double B = py - y1;
        double C = x2 - x1;
        double D = y2 - y1;

        double dot = A * C + B * D;
        double lenSq = C * C + D * D;
        double param = -1;
        if (lenSq != 0) param = dot / lenSq;

        double xx, yy;
        if (param < 0) { xx = x1; yy = y1; }
        else if (param > 1) { xx = x2; yy = y2; }
        else { xx = x1 + param * C; yy = y1 + param * D; }

        double dx = px - xx;
        double dy = py - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }
    public void setX1(int x1) { this.x1 = x1; }
    public void setY1(int y1) { this.y1 = y1; }
    public void setX2(int x2) { this.x2 = x2; }
    public void setY2(int y2) { this.y2 = y2; }
}