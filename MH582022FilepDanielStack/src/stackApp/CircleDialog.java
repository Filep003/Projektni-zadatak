package stackApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CircleDialog extends JDialog {
    enum Mode { CREATE, VIEW }

    private JTextField tfX;
    private JTextField tfY;
    private JTextField tfR;
    private boolean confirmed = false;

    CircleDialog(Frame owner, Mode mode) {
        super(owner, true); 
        setTitle(mode == Mode.CREATE ? "Add Circle" : "View Circle");
        buildUi(mode);
        pack();
        setLocationRelativeTo(owner);
    }

    private void buildUi(Mode mode) {
        JPanel fields = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        fields.add(new JLabel("X:"), gbc);
        tfX = new JTextField(10);
        gbc.gridx = 1;
        fields.add(tfX, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        fields.add(new JLabel("Y:"), gbc);
        tfY = new JTextField(10);
        gbc.gridx = 1;
        fields.add(tfY, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        fields.add(new JLabel("Radius:"), gbc);
        tfR = new JTextField(10);
        gbc.gridx = 1;
        fields.add(tfR, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton(mode == Mode.CREATE ? "Cancel" : "Close");
        buttons.add(btnOk);
        buttons.add(btnCancel);

        btnOk.addActionListener(e -> {
            if (getTitle().startsWith("Add")) {
                try {
                    int x = Integer.parseInt(tfX.getText().trim());
                    int y = Integer.parseInt(tfY.getText().trim());
                    int r = Integer.parseInt(tfR.getText().trim());
                    if (x <= 0 || y <= 0 || r <= 0) {
                        throw new NumberFormatException();
                    }
                    confirmed = true;
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter positive integers for X, Y and Radius.",
                            "Invalid input", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                dispose();
            }
        });

        btnCancel.addActionListener(e -> dispose());

        getContentPane().setLayout(new BorderLayout(8,8));
        getContentPane().add(fields, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override public void windowOpened(WindowEvent e) {
                if (!getTitle().startsWith("Add")) {
                    tfX.setEditable(false);
                    tfY.setEditable(false);
                    tfR.setEditable(false);
                }
            }
        });
    }

    public void preset(Circle c) {
        tfX.setText(String.valueOf(c.getX()));
        tfY.setText(String.valueOf(c.getY()));
        tfR.setText(String.valueOf(c.getRadius()));
    }

    public boolean isConfirmed() { return confirmed; }

    public Circle buildCircleOrNull() {
        if (!confirmed) return null;
        int x = Integer.parseInt(tfX.getText().trim());
        int y = Integer.parseInt(tfY.getText().trim());
        int r = Integer.parseInt(tfR.getText().trim());
        return new Circle(x, y, r);
    }
}