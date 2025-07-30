package ui;

import model.TestResult;
import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private JLabel pointsLabel;
    private JLabel percentageLabel;
    private JLabel errorsLabel;

    public ResultPanel() {
        initializeComponents();
        setupLayout();
        reset();
    }

    private void initializeComponents() {
        pointsLabel = new JLabel();
        percentageLabel = new JLabel();
        errorsLabel = new JLabel();

        Font boldFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        pointsLabel.setFont(boldFont);
        percentageLabel.setFont(boldFont);
        errorsLabel.setFont(boldFont);
    }

    private void setupLayout() {
        setLayout(new GridLayout(3, 1, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Wyniki"));

        add(pointsLabel);
        add(errorsLabel);
        add(percentageLabel);
    }

    public void displayResult(TestResult result) {
        pointsLabel.setText(String.format("Punkty: %d / %d",
            result.getTotalPoints(), result.getMaxPoints()));

        errorsLabel.setText(String.format("Błędy: %d", result.getErrors()));

        percentageLabel.setText(String.format("Wynik procentowy: %.1f%%",
            result.getPercentage()));

        // Kolorowanie wyniku
        if (result.getPercentage() >= 75) {
            percentageLabel.setForeground(Color.GREEN.darker());
        } else if (result.getPercentage() >= 50) {
            percentageLabel.setForeground(Color.ORANGE.darker());
        } else {
            percentageLabel.setForeground(Color.RED);
        }
    }

    public void reset() {
        pointsLabel.setText("Punkty: - / -");
        errorsLabel.setText("Błędy: -");
        percentageLabel.setText("Wynik procentowy: -%");
        percentageLabel.setForeground(Color.BLACK);
    }
}

