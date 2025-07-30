package ui;

import model.TestResult;
import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private JLabel resultLabel;
    private JLabel percentageLabel;
    private JLabel pointsLabel;

    public ResultPanel() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        resultLabel = new JLabel("Brak wyniku");
        percentageLabel = new JLabel("");
        pointsLabel = new JLabel("");

        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16f));
    }

    private void setupLayout() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("Wynik"));

        add(resultLabel);
        add(percentageLabel);
        add(pointsLabel);
    }

    public void displayResult(TestResult result) {
        // Założenie: test zaliczony jeśli procent >= 75%
        if (result.getPercentage() >= 75.0) {
            resultLabel.setText("ZALICZONY");
            resultLabel.setForeground(Color.GREEN);
        } else {
            resultLabel.setText("NIEZALICZONY");
            resultLabel.setForeground(Color.RED);
        }

        percentageLabel.setText(String.format("Procent: %.1f%%", result.getPercentage()));

        // Oblicz punkty na podstawie procentu i maksymalnych punktów
        int currentPoints = (int) Math.round(result.getMaxPoints() * result.getPercentage() / 100.0);
        pointsLabel.setText(String.format("Punkty: %d/%d",
                currentPoints, result.getMaxPoints()));

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
