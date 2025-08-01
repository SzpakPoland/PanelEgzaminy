package ui;

import model.Question;
import model.Test;
import model.TestResult;
import service.TestProcessor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TestPanel extends JPanel {
    private Test currentTest;
    private Map<Question, JSpinner> questionSpinners;
    private JSpinner errorsSpinner;
    private JSpinner extraPointsSpinner;
    private JButton calculateButton;
    private TestProcessor testProcessor;
    private ResultPanel resultPanel;

    // Kolory aplikacji - uspójnione i przejrzyste
    private static final Color PRIMARY_COLOR = new Color(52, 90, 138);
    private static final Color SECONDARY_COLOR = new Color(248, 250, 252);
    private static final Color ACCENT_COLOR = new Color(79, 123, 178);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);
    private static final Color BORDER_COLOR = new Color(206, 212, 218);
    private static final Color SUCCESS_COLOR = new Color(34, 139, 34);

    public TestPanel() {
        questionSpinners = new HashMap<>();
        initializeComponents();
        setupLayout();
        styleComponents();
    }

    private void initializeComponents() {
        errorsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        extraPointsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        calculateButton = new JButton("Oblicz wynik");

        calculateButton.addActionListener(e -> calculateResults());
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            "Pytania testowe",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), PRIMARY_COLOR));
        setBackground(SECONDARY_COLOR);
    }

    private void styleComponents() {
        setBackground(SECONDARY_COLOR);

        // Stylizacja spinnerów - lepszy kontrast
        styleSpinner(errorsSpinner);
        styleSpinner(extraPointsSpinner);

        // Stylizacja przycisku oblicz - bardziej wyrazisty
        calculateButton.setBackground(SUCCESS_COLOR);
        calculateButton.setForeground(Color.BLACK);
        calculateButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        calculateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover efekt
        calculateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                calculateButton.setBackground(new Color(28, 115, 28));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                calculateButton.setBackground(SUCCESS_COLOR);
            }
        });
    }

    private void styleSpinner(JSpinner spinner) {
        spinner.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        spinner.setPreferredSize(new Dimension(80, 32));
        spinner.setBackground(CARD_COLOR);
        spinner.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setBackground(CARD_COLOR);
            textField.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        }
    }

    public void loadTest(Test test) {
        this.currentTest = test;
        questionSpinners.clear();
        removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBackground(SECONDARY_COLOR);

        // Panel z pytaniami - większy
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBackground(SECONDARY_COLOR);

        for (Question question : test.getQuestions()) {
            JPanel questionCard = createQuestionCard(question);
            questionsPanel.add(questionCard);
            questionsPanel.add(Box.createVerticalStrut(6));
        }

        JScrollPane scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(SECONDARY_COLOR);

        // Panel kontrolny - mniejszy
        JPanel controlPanel = createControlPanel();
        controlPanel.setPreferredSize(new Dimension(0, 80));

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private JPanel createQuestionCard(Question question) {
        JPanel card = new JPanel(new BorderLayout(15, 10));
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(18, 20, 18, 20)
        ));

        JLabel questionLabel = new JLabel(
            String.format("<html><div style='width:500px; font-family: Segoe UI; color: #000000;'>%s</div></html>",
            question.getQuestionText()));
        questionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        questionLabel.setForeground(Color.BLACK);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightPanel.setBackground(CARD_COLOR);

        JLabel maxLabel = new JLabel(String.format("Max: %d", question.getMaxPoints()));
        maxLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        maxLabel.setForeground(Color.BLACK);

        JSpinner pointsSpinner = new JSpinner(
            new SpinnerNumberModel(question.getMaxPoints(), 0, question.getMaxPoints(), 0.5));
        styleSpinner(pointsSpinner);

        JLabel pktLabel = new JLabel("pkt");
        pktLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        pktLabel.setForeground(Color.BLACK);

        rightPanel.add(maxLabel);
        rightPanel.add(Box.createHorizontalStrut(12));
        rightPanel.add(pointsSpinner);
        rightPanel.add(pktLabel);

        card.add(questionLabel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);

        questionSpinners.put(question, pointsSpinner);
        return card;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(CARD_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(10, 20, 10, 20)
        ));

        JLabel errorsLabel = new JLabel("Błędy:");
        errorsLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        errorsLabel.setForeground(Color.BLACK);

        JLabel extraLabel = new JLabel("Dodatkowe punkty:");
        extraLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        extraLabel.setForeground(Color.BLACK);

        panel.add(errorsLabel);
        panel.add(errorsSpinner);
        panel.add(Box.createHorizontalStrut(25));
        panel.add(extraLabel);
        panel.add(extraPointsSpinner);
        panel.add(Box.createHorizontalStrut(25));
        panel.add(calculateButton);

        return panel;
    }

    private void calculateResults() {
        if (currentTest == null || testProcessor == null || resultPanel == null) {
            return;
        }

        Map<Question, Double> partialAnswers = new HashMap<>();
        for (Map.Entry<Question, JSpinner> entry : questionSpinners.entrySet()) {
            double points = ((Number) entry.getValue().getValue()).doubleValue();
            partialAnswers.put(entry.getKey(), points);
        }

        int errors = (Integer) errorsSpinner.getValue();
        int extraPoints = (Integer) extraPointsSpinner.getValue();
        TestResult result = testProcessor.calculateResultWithPartialPoints(currentTest, partialAnswers, errors, extraPoints);
        resultPanel.displayResult(result);
    }

    public void setTestProcessor(TestProcessor testProcessor) {
        this.testProcessor = testProcessor;
    }

    public void setResultPanel(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }
}
