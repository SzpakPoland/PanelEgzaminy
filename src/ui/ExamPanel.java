package ui;

import model.Test;
import service.FileReader;
import service.TestProcessor;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ExamPanel extends JPanel {
    private TestPanel testPanel;
    private ResultPanel resultPanel;
    private TestProcessor testProcessor;
    private JButton loadFileButton;

    public ExamPanel() {
        testProcessor = new TestProcessor();
        initializeComponents();
        setupLayout();
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
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(loadFileButton);

        add(topPanel, BorderLayout.NORTH);
        add(testPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
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

