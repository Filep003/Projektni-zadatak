package sortApp;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SortFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DefaultListModel<Circle> model = new DefaultListModel<>();
    private final JList<Circle> list = new JList<>(model);

    SortFrame() {
        setTitle("Filep Daniel MH58/2022 — Sort"); 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationByPlatform(true);
        buildUi();
    }

    private void buildUi() {
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(list);

        JButton btnAdd = new JButton("Add Circle");
        JButton btnSort = new JButton("Sort by Area (desc)");
        JButton btnExit = new JButton("Exit");

        btnAdd.addActionListener(e -> onAdd());
        btnSort.addActionListener(e -> onSort());
        btnExit.addActionListener(e -> dispose());

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(btnAdd);
        south.add(btnSort);
        south.add(btnExit);

        setLayout(new BorderLayout(8,8));
        add(scroll, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    private void onAdd() {
        try {
            String sx = JOptionPane.showInputDialog(this, "Enter X coordinate (>0):");
            if (sx == null) return;
            String sy = JOptionPane.showInputDialog(this, "Enter Y coordinate (>0):");
            if (sy == null) return;
            String sr = JOptionPane.showInputDialog(this, "Enter Radius (>0):");
            if (sr == null) return;

            int x = Integer.parseInt(sx.trim());
            int y = Integer.parseInt(sy.trim());
            int r = Integer.parseInt(sr.trim());
            Circle c = new Circle(x, y, r);

            model.addElement(c);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input. Please enter positive integers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onSort() {
        if (model.isEmpty()) {
            JOptionPane.showMessageDialog(this, "List is empty.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        List<Circle> circles = Collections.list(model.elements());
        circles.sort(Comparator.comparingDouble(Circle::area).reversed());

        model.clear();
        for (Circle c : circles) {
            model.addElement(c);
        }
    }
}