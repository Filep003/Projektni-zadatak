package drawingApp;

import java.awt.*;

public class RectangleShape extends Shape {
    private int x, y, width, height;

    public RectangleShape(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillRect(x, y, width, height);
        g.setColor(edgeColor);
        g.drawRect(x, y, width, height);

        if (selected) {
            g.setColor(Color.RED);
            g.drawRect(x - 3, y - 3, width + 6, height + 6);
        }
    }

    @Override
    public boolean contains(int px, int py) {
        return (px >= x && px <= x + width && py >= y && py <= y + height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setWidth(int w) { this.width = w; }
    public void setHeight(int h) { this.height = h; }
}