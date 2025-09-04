package stackApp;

import javax.swing.*;
import java.awt.*;

class StackFrame extends JFrame {
    private final DefaultListModel<Circle> model = new DefaultListModel<>();
    private final JList<Circle> list = new JList<>(model);

    StackFrame() {
        setTitle("Filep Daniel MH58/2022 — Stack");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(420, 420);
        setLocationByPlatform(true);
        buildUi();
    }

    private void buildUi() {
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(list);

        JButton btnPush = new JButton("Push (Add)");
        JButton btnPop  = new JButton("Pop (Remove)");
        JButton btnExit = new JButton("Exit");

        btnPush.addActionListener(e -> onPush());
        btnPop.addActionListener(e -> onPop());
        btnExit.addActionListener(e -> dispose());

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(btnPush);
        south.add(btnPop);
        south.add(btnExit);

        setLayout(new BorderLayout(8,8));
        add(scroll, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    private void onPush() {
        CircleDialog dlg = new CircleDialog(this, CircleDialog.Mode.CREATE);
        dlg.setVisible(true);
        if (dlg.isConfirmed()) {
            Circle c = dlg.buildCircleOrNull();
            if (c != null) {
                model.add(0, c); 
                list.setSelectedIndex(0);
            }
        }
    }

    private void onPop() {
        if (model.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Stack is empty.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Circle top = model.getElementAt(0);

        CircleDialog view = new CircleDialog(this, CircleDialog.Mode.VIEW);
        view.preset(top);
        view.setVisible(true);

        model.remove(0); 
    }
}