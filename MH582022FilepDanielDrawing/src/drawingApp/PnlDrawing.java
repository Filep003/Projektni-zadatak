package drawingApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PnlDrawing extends JPanel {
    private java.util.List<Shape> shapes = new ArrayList<>();
    private String mode = "Point"; 
    private Shape selectedShape = null;

    private int tempX, tempY;
    private boolean lineStart = false;

    public PnlDrawing() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            s.draw(g);
        }
    }

    private void handleClick(int x, int y) {
        if (mode.equals("Select")) {
            handleSelection(x, y);
        } else if (mode.equals("Point")) {
            shapes.add(new PointShape(x, y));
        } else if (mode.equals("Line")) {
            if (!lineStart) {
                tempX = x; tempY = y;
                lineStart = true;
            } else {
                shapes.add(new LineShape(tempX, tempY, x, y));
                lineStart = false;
            }
        } else if (mode.equals("Rectangle")) {
            String sw = JOptionPane.showInputDialog(this, "Enter width:");
            String sh = JOptionPane.showInputDialog(this, "Enter height:");
            try {
                int w = Integer.parseInt(sw);
                int h = Integer.parseInt(sh);
                shapes.add(new RectangleShape(x, y, w, h));
            } catch (Exception ignored) {}
        } else if (mode.equals("Circle")) {
            String sr = JOptionPane.showInputDialog(this, "Enter radius:");
            try {
                int r = Integer.parseInt(sr);
                shapes.add(new CircleShape(x, y, r));
            } catch (Exception ignored) {}
        } else if (mode.equals("Donut")) {
            String so = JOptionPane.showInputDialog(this, "Enter outer radius:");
            String si = JOptionPane.showInputDialog(this, "Enter inner radius:");
            try {
                int ro = Integer.parseInt(so);
                int ri = Integer.parseInt(si);
                if (ri < ro) {
                    shapes.add(new DonutShape(x, y, ro, ri));
                } else {
                    JOptionPane.showMessageDialog(this, "Inner radius must be smaller than outer radius");
                }
            } catch (Exception ignored) {}
        }

        repaint();
    }

    private void handleSelection(int x, int y) {
        for (Shape s : shapes) s.setSelected(false);
        selectedShape = null;

        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape s = shapes.get(i);
            if (s.contains(x, y)) {
                s.setSelected(true);
                selectedShape = s;
                break;
            }
        }
        repaint();
    }

    public void setMode(String mode) {
        this.mode = mode;
        lineStart = false;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void deleteSelected() {
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            selectedShape = null;
            repaint();
        }
    }
}