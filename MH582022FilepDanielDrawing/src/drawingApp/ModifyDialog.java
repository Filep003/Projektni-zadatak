package drawingApp;

import javax.swing.*;
import java.awt.*;

public class ModifyDialog extends JDialog {
    private JTextField tfX, tfY, tfW, tfH, tfR, tfRin, tfRout;
    private JButton btnEdgeColor, btnFillColor;
    private Color edgeColor, fillColor;
    private boolean confirmed = false;

    private Shape shape;

    public ModifyDialog(Frame owner, Shape shape) {
        super(owner, "Modify Shape", true);
        this.shape = shape;
        this.edgeColor = shape.getEdgeColor();
        this.fillColor = shape.getFillColor();
        buildUi();
        pack();
        setLocationRelativeTo(owner);
        loadValues();
    }

    private void buildUi() {
        JPanel panel = new JPanel(new GridLayout(0,2,5,5));

        tfX = new JTextField(10);
        tfY = new JTextField(10);
        tfW = new JTextField(10);
        tfH = new JTextField(10);
        tfR = new JTextField(10);
        tfRin = new JTextField(10);
        tfRout = new JTextField(10);

        btnEdgeColor = new JButton("Edge Color");
        btnFillColor = new JButton("Fill Color");

        btnEdgeColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Edge Color", edgeColor);
            if (c != null) edgeColor = c;
        });
        btnFillColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Fill Color", fillColor);
            if (c != null) fillColor = c;
        });

        panel.add(new JLabel("X:")); panel.add(tfX);
        panel.add(new JLabel("Y:")); panel.add(tfY);
        panel.add(new JLabel("Width:")); panel.add(tfW);
        panel.add(new JLabel("Height:")); panel.add(tfH);
        panel.add(new JLabel("Radius:")); panel.add(tfR);
        panel.add(new JLabel("Inner Radius:")); panel.add(tfRin);
        panel.add(new JLabel("Outer Radius:")); panel.add(tfRout);
        panel.add(btnEdgeColor); panel.add(btnFillColor);

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        btnOk.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        btnCancel.addActionListener(e -> dispose());

        JPanel south = new JPanel();
        south.add(btnOk);
        south.add(btnCancel);

        getContentPane().setLayout(new BorderLayout(8,8));
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(south, BorderLayout.SOUTH);
    }

    private void loadValues() {
        if (shape instanceof PointShape p) {
            tfX.setText(String.valueOf(p.getX()));
            tfY.setText(String.valueOf(p.getY()));
        } else if (shape instanceof LineShape l) {
            tfX.setText(String.valueOf(l.getX1()));
            tfY.setText(String.valueOf(l.getY1()));
            tfW.setText(String.valueOf(l.getX2()));
            tfH.setText(String.valueOf(l.getY2()));
        } else if (shape instanceof RectangleShape r) {
            tfX.setText(String.valueOf(r.getX()));
            tfY.setText(String.valueOf(r.getY()));
            tfW.setText(String.valueOf(r.getWidth()));
            tfH.setText(String.valueOf(r.getHeight()));
        } else if (shape instanceof CircleShape c) {
            tfX.setText(String.valueOf(c.getX()));
            tfY.setText(String.valueOf(c.getY()));
            tfR.setText(String.valueOf(c.getR()));
        } else if (shape instanceof DonutShape d) {
            tfX.setText(String.valueOf(d.getX()));
            tfY.setText(String.valueOf(d.getY()));
            tfRout.setText(String.valueOf(d.getOuterR()));
            tfRin.setText(String.valueOf(d.getInnerR()));
        }
    }

    public void applyChanges() {
        if (!confirmed) return;
        shape.setEdgeColor(edgeColor);
        shape.setFillColor(fillColor);

        try {
            if (shape instanceof PointShape p) {
                p.setX(Integer.parseInt(tfX.getText().trim()));
                p.setY(Integer.parseInt(tfY.getText().trim()));
            } else if (shape instanceof LineShape l) {
                l.setX1(Integer.parseInt(tfX.getText().trim()));
                l.setY1(Integer.parseInt(tfY.getText().trim()));
                l.setX2(Integer.parseInt(tfW.getText().trim()));
                l.setY2(Integer.parseInt(tfH.getText().trim()));
            } else if (shape instanceof RectangleShape r) {
                r.setX(Integer.parseInt(tfX.getText().trim()));
                r.setY(Integer.parseInt(tfY.getText().trim()));
                r.setWidth(Integer.parseInt(tfW.getText().trim()));
                r.setHeight(Integer.parseInt(tfH.getText().trim()));
            } else if (shape instanceof CircleShape c) {
                c.setX(Integer.parseInt(tfX.getText().trim()));
                c.setY(Integer.parseInt(tfY.getText().trim()));
                c.setR(Integer.parseInt(tfR.getText().trim()));
            } else if (shape instanceof DonutShape d) {
                d.setX(Integer.parseInt(tfX.getText().trim()));
                d.setY(Integer.parseInt(tfY.getText().trim()));
                d.setOuterR(Integer.parseInt(tfRout.getText().trim()));
                d.setInnerR(Integer.parseInt(tfRin.getText().trim()));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConfirmed() { return confirmed; }
}