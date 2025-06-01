import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import view.MainFrame;

public class App {
    public static void main(String[] args) {
        try {
            // Set Look and Feel giống hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Không thể set Look & Feel: " + ex.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
