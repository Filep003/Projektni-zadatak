package drawingApp;

import java.awt.*;

public abstract class Shape {
    protected Color edgeColor = Color.BLACK;
    protected Color fillColor = Color.WHITE;
    protected boolean selected = false;

    public abstract void draw(Graphics g);
    public abstract boolean contains(int x, int y);

    public void setEdgeColor(Color edgeColor) { this.edgeColor = edgeColor; }
    public void setFillColor(Color fillColor) { this.fillColor = fillColor; }

    public Color getEdgeColor() { return edgeColor; }
    public Color getFillColor() { return fillColor; }

    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}