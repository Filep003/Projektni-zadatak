package drawingApp;

import javax.swing.UIManager;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        EventQueue.invokeLater(() -> new DrawingFrame().setVisible(true));
    }
}
