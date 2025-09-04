package drawingApp;

import javax.swing.*;
import java.awt.*;

public class DrawingFrame extends JFrame {
    private PnlDrawing pnlDrawing = new PnlDrawing();

    public DrawingFrame() {
        setTitle("Daniel Filep MH58/2022 — Drawing"); 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationByPlatform(true);

        buildUi();
    }

    private void buildUi() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton btnPoint = new JButton("Point");
        JButton btnLine = new JButton("Line");
        JButton btnRect = new JButton("Rectangle");
        JButton btnCircle = new JButton("Circle");
        JButton btnDonut = new JButton("Donut");
        JButton btnSelect = new JButton("Select");
        JButton btnModify = new JButton("Modify");
        JButton btnDelete = new JButton("Delete");

        btnPoint.addActionListener(e -> pnlDrawing.setMode("Point"));
        btnLine.addActionListener(e -> pnlDrawing.setMode("Line"));
        btnRect.addActionListener(e -> pnlDrawing.setMode("Rectangle"));
        btnCircle.addActionListener(e -> pnlDrawing.setMode("Circle"));
        btnDonut.addActionListener(e -> pnlDrawing.setMode("Donut"));
        btnSelect.addActionListener(e -> pnlDrawing.setMode("Select"));

        btnModify.addActionListener(e -> {
            Shape s = pnlDrawing.getSelectedShape();
            if (s == null) {
                JOptionPane.showMessageDialog(this, "No shape selected.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            ModifyDialog dlg = new ModifyDialog(this, s);
            dlg.setVisible(true);
            if (dlg.isConfirmed()) {
                dlg.applyChanges();
                pnlDrawing.repaint();
            }
        });

        btnDelete.addActionListener(e -> {
            Shape s = pnlDrawing.getSelectedShape();
            if (s == null) {
                JOptionPane.showMessageDialog(this, "No shape selected.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int opt = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete selected shape?",
                    "Confirm delete", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                pnlDrawing.deleteSelected();
            }
        });

        toolbar.add(btnPoint);
        toolbar.add(btnLine);
        toolbar.add(btnRect);
        toolbar.add(btnCircle);
        toolbar.add(btnDonut);
        toolbar.add(btnSelect);
        toolbar.addSeparator();
        toolbar.add(btnModify);
        toolbar.add(btnDelete);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(pnlDrawing, BorderLayout.CENTER);
    }
}