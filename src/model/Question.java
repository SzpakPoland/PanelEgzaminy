package model;

import java.util.List;
import java.util.ArrayList;

public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;
    private int maxPoints;
    private String category;

    public Question() {
        this.options = new ArrayList<>();
        this.maxPoints = 1;
    }

    public Question(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = new ArrayList<>(options);
        this.correctAnswer = correctAnswer;
        this.maxPoints = 1;
    }

    public Question(String questionText, List<String> options, String correctAnswer, int maxPoints) {
        this.questionText = questionText;
        this.options = new ArrayList<>(options);
        this.correctAnswer = correctAnswer;
        this.maxPoints = maxPoints;
    }

    // Gettery i settery
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return new ArrayList<>(options);
    }

    public void setOptions(List<String> options) {
        this.options = new ArrayList<>(options);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAnswerCorrect(String answer) {
        return correctAnswer != null && correctAnswer.equals(answer);
    }

    @Override
    public String toString() {
        return String.format("Question{text='%s', options=%d, maxPoints=%d}",
                questionText, options.size(), maxPoints);
    }
}