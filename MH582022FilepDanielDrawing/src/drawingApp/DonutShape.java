package drawingApp;

import java.awt.*;

public class DonutShape extends Shape {
    private int x, y, outerR, innerR;

    public DonutShape(int x, int y, int outerR, int innerR) {
        this.x = x; this.y = y;
        this.outerR = outerR; this.innerR = innerR;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x - outerR, y - outerR, 2 * outerR, 2 * outerR);

        g.setColor(Color.WHITE);
        g.fillOval(x - innerR, y - innerR, 2 * innerR, 2 * innerR);

        g.setColor(edgeColor);
        g.drawOval(x - outerR, y - outerR, 2 * outerR, 2 * outerR);
        g.drawOval(x - innerR, y - innerR, 2 * innerR, 2 * innerR);

        if (selected) {
            g.setColor(Color.RED);
            g.drawRect(x - outerR - 3, y - outerR - 3, 2 * outerR + 6, 2 * outerR + 6);
        }
    }

    @Override
    public boolean contains(int px, int py) {
        int dx = px - x;
        int dy = py - y;
        int dist2 = dx * dx + dy * dy;
        return dist2 <= outerR * outerR && dist2 >= innerR * innerR;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getOuterR() { return outerR; }
    public int getInnerR() { return innerR; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setOuterR(int r) { this.outerR = r; }
    public void setInnerR(int r) { this.innerR = r; }
}