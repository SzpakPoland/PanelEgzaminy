package service;

import model.Question;
import model.Test;
import model.TestResult;
import java.util.Map;

public class TestProcessor {
    private ScoreCalculator scoreCalculator;

    public TestProcessor() {
        this.scoreCalculator = new ScoreCalculator();
    }

    public TestResult calculateResult(Test test, Map<Question, Boolean> answers, int errors) {
        int totalPoints = scoreCalculator.calculatePoints(answers);
        int maxPoints = test.getMaxPoints();

        // Odejmij błędy od wyniku
        totalPoints = Math.max(0, totalPoints - errors);

        return new TestResult(totalPoints, maxPoints, errors);
    }

    public TestResult calculateResultWithExtraPoints(Test test, Map<Question, Boolean> answers, int errors, int extraPoints) {
        TestResult baseResult = calculateResult(test, answers, errors);

        // Oblicz bazowe punkty
        int basePoints = (int) Math.round(baseResult.getPercentage() * test.getMaxPoints() / 100.0);
        int newTotalPoints = basePoints + extraPoints;

        // Nowe maksimum to oryginalne maksimum + dodatkowe punkty
        int newMaxPoints = test.getMaxPoints() + extraPoints;

        return new TestResult(newTotalPoints, newMaxPoints, errors);
    }
}
