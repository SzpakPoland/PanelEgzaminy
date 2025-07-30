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
}

