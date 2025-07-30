package ui;

import service.FileReader;
import service.TestProcessor;
import model.Question;
import model.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainWindow extends JFrame {
    private TestPanel testPanel;
    private ResultPanel resultPanel;
    private JButton loadFileButton;
    private Test currentTest;
    private TestProcessor testProcessor;

    public MainWindow() {
        this.testProcessor = new TestProcessor();
        initializeComponents();
        setupLayout();
        setupEventListeners();
    }

    private void initializeComponents() {
        setTitle("Panel Sprawdzania Egzaminów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        loadFileButton = new JButton("Wczytaj plik z pytaniami");
        testPanel = new TestPanel();
        resultPanel = new ResultPanel();

        testPanel.setTestProcessor(testProcessor);
        testPanel.setResultPanel(resultPanel);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(loadFileButton);

        add(topPanel, BorderLayout.NORTH);
        add(testPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        loadFileButton.addActionListener(this::loadFile);
    }

    private void loadFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Pliki tekstowe", "txt"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                List<Question> questions = FileReader.readQuestions(
                        fileChooser.getSelectedFile().getAbsolutePath());
                currentTest = new Test(questions);
                testPanel.loadTest(currentTest);
                resultPanel.reset();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Błąd podczas wczytywania pliku: " + ex.getMessage(),
                        "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}