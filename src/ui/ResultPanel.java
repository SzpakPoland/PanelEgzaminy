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
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            "Wynik egzaminu",
            0, 0, new Font("Segoe UI", Font.BOLD, 15), PRIMARY_COLOR));

        JPanel resultCard = new JPanel(new GridLayout(3, 1, 8, 8));
        resultCard.setBackground(CARD_COLOR);
        resultCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(25, 25, 25, 25)
        ));

        resultCard.add(resultLabel);
        resultCard.add(percentageLabel);
        resultCard.add(pointsLabel);

        add(resultCard, BorderLayout.CENTER);
    }

    private void styleComponents() {
        setBackground(SECONDARY_COLOR);

        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        percentageLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pointsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

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

        // Oblicz punkty na podstawie procentu i maksymalnych punktów
        int currentPoints = (int) Math.round(result.getMaxPoints() * result.getPercentage() / 100.0);
        pointsLabel.setText(String.format("Punkty: %d/%d", currentPoints, result.getMaxPoints()));
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
