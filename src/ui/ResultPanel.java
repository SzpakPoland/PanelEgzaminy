package ui;

import model.TestResult;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ResultPanel extends JPanel {
    private JLabel resultLabel;
    private JLabel percentageLabel;
    private JLabel pointsLabel;

    // Kolory aplikacji - uspójnione i przejrzyste
    private static final Color PRIMARY_COLOR = new Color(52, 90, 138);
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69);
    private static final Color DANGER_COLOR = new Color(220, 53, 69);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(33, 37, 41);
    private static final Color SECONDARY_COLOR = new Color(248, 250, 252);
    private static final Color BORDER_COLOR = new Color(206, 212, 218);

    public ResultPanel() {
        initializeComponents();
        setupLayout();
        styleComponents();
    }

    private void initializeComponents() {
        resultLabel = new JLabel("Brak wyniku", JLabel.CENTER);
        percentageLabel = new JLabel("", JLabel.CENTER);
        pointsLabel = new JLabel("", JLabel.CENTER);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            "Wynik egzaminu",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), PRIMARY_COLOR));

        JPanel resultCard = new JPanel(new GridLayout(3, 1, 5, 5));
        resultCard.setBackground(CARD_COLOR);
        resultCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(15, 20, 15, 20)
        ));

        resultCard.add(resultLabel);
        resultCard.add(percentageLabel);
        resultCard.add(pointsLabel);

        add(resultCard, BorderLayout.CENTER);

        // Ustaw preferowaną wysokość
        setPreferredSize(new Dimension(0, 120));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
    }

    private void styleComponents() {
        setBackground(SECONDARY_COLOR);

        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        percentageLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pointsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        resultLabel.setForeground(Color.BLACK);
        percentageLabel.setForeground(Color.BLACK);
        pointsLabel.setForeground(Color.BLACK);
    }

    public void displayResult(TestResult result) {
        // Test zaliczony jeśli procent >= 75%
        if (result.getPercentage() >= 75.0) {
            resultLabel.setText("ZALICZONY");
            resultLabel.setForeground(SUCCESS_COLOR);
        } else {
            resultLabel.setText("NIEZALICZONY");
            resultLabel.setForeground(DANGER_COLOR);
        }

        percentageLabel.setText(String.format("Procent: %.1f%%", result.getPercentage()));
        percentageLabel.setForeground(Color.BLACK);

        // Wyświetl punkty z uwzględnieniem połówek
        pointsLabel.setText(String.format("Punkty: %.1f/%.1f", result.getTotalPoints(), result.getMaxPoints()));
        pointsLabel.setForeground(Color.BLACK);

        repaint();
    }

    public void reset() {
        resultLabel.setText("Brak wyniku");
        resultLabel.setForeground(Color.BLACK);
        percentageLabel.setText("");
        pointsLabel.setText("");
        repaint();
    }
}
