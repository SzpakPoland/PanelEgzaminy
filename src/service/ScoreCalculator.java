package service;

import model.Question;
import java.util.Map;

public class ScoreCalculator {

    public int calculatePoints(Map<Question, Boolean> answers) {
        int totalPoints = 0;

        for (Map.Entry<Question, Boolean> entry : answers.entrySet()) {
            if (entry.getValue()) { // Jeśli odpowiedź jest zaznaczona jako poprawna
                totalPoints += entry.getKey().getMaxPoints();
            }
        }

        return totalPoints;
    }
}

