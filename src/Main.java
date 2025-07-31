import ui.ExamPanel;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Ustawienia dla lepszego renderowania i przejrzystości
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        System.setProperty("sun.java2d.xrender", "true");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    // Ustawienia dla lepszej przejrzystości
                    UIManager.put("Panel.background", new Color(248, 250, 252));
                    UIManager.put("Button.background", new Color(52, 90, 138));
                    UIManager.put("Button.foreground", Color.WHITE);
                    UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JFrame frame = new JFrame("Panel Egzaminów - System Oceniania");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(950, 750);
                frame.setLocationRelativeTo(null);
                frame.setMinimumSize(new Dimension(750, 550));
                frame.getContentPane().setBackground(new Color(248, 250, 252));

                // Ikona aplikacji (opcjonalnie)
                try {
                    frame.setIconImage(Toolkit.getDefaultToolkit().createImage("icon.png"));
                } catch (Exception e) {
                    // Ignoruj jeśli brak ikony
                }

                ExamPanel examPanel = new ExamPanel();
                frame.add(examPanel);

                frame.setVisible(true);
            }
        });
    }
}