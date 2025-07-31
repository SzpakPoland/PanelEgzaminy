package ui;

import model.Test;
import service.FileReader;
import service.TestProcessor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class ExamPanel extends JPanel {
    private TestPanel testPanel;
    private ResultPanel resultPanel;
    private TestProcessor testProcessor;
    private JButton loadFileButton;

    // Kolory aplikacji - uspójnione
    private static final Color PRIMARY_COLOR = new Color(52, 90, 138);
    private static final Color SECONDARY_COLOR = new Color(248, 250, 252);
    private static final Color ACCENT_COLOR = new Color(79, 123, 178);
    private static final Color TEXT_COLOR = Color.BLACK;

    public ExamPanel() {
        testProcessor = new TestProcessor();
        initializeComponents();
        setupLayout();
        styleComponents();
    }

    private void initializeComponents() {
        testPanel = new TestPanel();
        resultPanel = new ResultPanel();
        loadFileButton = new JButton("Wczytaj plik z pytaniami");

        testPanel.setTestProcessor(testProcessor);
        testPanel.setResultPanel(resultPanel);

        loadFileButton.addActionListener(e -> loadTestFile());
    }

    private void setupLayout() {
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        topPanel.setBackground(SECONDARY_COLOR);
        topPanel.add(loadFileButton);

        add(topPanel, BorderLayout.NORTH);
        add(testPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    private void styleComponents() {
        setBackground(SECONDARY_COLOR);

        // Stylizacja przycisku - bardziej wyrazista
        loadFileButton.setBackground(PRIMARY_COLOR);
        loadFileButton.setForeground(Color.BLACK);
        loadFileButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loadFileButton.setFocusPainted(false);
        loadFileButton.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        loadFileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover efekt dla przycisku
        loadFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loadFileButton.setBackground(ACCENT_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loadFileButton.setBackground(PRIMARY_COLOR);
            }
        });
    }

    private void loadTestFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Pliki tekstowe", "txt"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                Test test = new Test(FileReader.readQuestions(filePath));
                testPanel.loadTest(test);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Błąd podczas wczytywania pliku: " + ex.getMessage(),
                        "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
