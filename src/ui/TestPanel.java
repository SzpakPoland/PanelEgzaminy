package ui;

import model.Question;
import model.Test;
import model.TestResult;
import service.TestProcessor;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TestPanel extends JPanel {
    private Test currentTest;
    private Map<Question, JCheckBox> questionCheckBoxes;
    private JSpinner errorsSpinner;
    private JSpinner extraPointsSpinner;
    private JButton calculateButton;
    private TestProcessor testProcessor;
    private ResultPanel resultPanel;

    public TestPanel() {
        questionCheckBoxes = new HashMap<>();
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        errorsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        extraPointsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        calculateButton = new JButton("Oblicz wynik");

        calculateButton.addActionListener(e -> calculateResults());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Pytania testowe"));
    }

    public void loadTest(Test test) {
        this.currentTest = test;
        questionCheckBoxes.clear();
        removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel z pytaniami
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

        for (Question question : test.getQuestions()) {
            JCheckBox checkBox = new JCheckBox(
                    String.format("%s (Punkty: %d)",
                            question.getQuestionText(), question.getMaxPoints()));
            checkBox.setSelected(true); // Domyślnie zaznaczone jako poprawne
            questionCheckBoxes.put(question, checkBox);
            questionsPanel.add(checkBox);
            questionsPanel.add(Box.createVerticalStrut(5));
        }

        JScrollPane scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Panel z błędami, dodatkowymi punktami i przyciskiem
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(new JLabel("Liczba błędów:"));
        bottomPanel.add(errorsSpinner);
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(new JLabel("Dodatkowe punkty:"));
        bottomPanel.add(extraPointsSpinner);
        bottomPanel.add(calculateButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void calculateResults() {
        if (currentTest == null || testProcessor == null || resultPanel == null) {
            return;
        }

        Map<Question, Boolean> answers = new HashMap<>();
        for (Map.Entry<Question, JCheckBox> entry : questionCheckBoxes.entrySet()) {
            answers.put(entry.getKey(), entry.getValue().isSelected());
        }

        int errors = (Integer) errorsSpinner.getValue();
        int extraPoints = (Integer) extraPointsSpinner.getValue();
        TestResult result = testProcessor.calculateResultWithExtraPoints(currentTest, answers, errors, extraPoints);
        resultPanel.displayResult(result);
    }

    public void setTestProcessor(TestProcessor testProcessor) {
        this.testProcessor = testProcessor;
    }

    public void setResultPanel(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }
}