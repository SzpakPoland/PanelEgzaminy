package model;

import java.util.List;
import java.util.ArrayList;

public class Test {
    private String name;
    private List<Question> questions;
    private int maxPoints;

    public Test() {
        this.questions = new ArrayList<>();
        this.maxPoints = 0;
    }

    public Test(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        calculateMaxPoints();
    }

    public Test(String name, List<Question> questions) {
        this.name = name;
        this.questions = new ArrayList<>(questions);
        calculateMaxPoints();
    }

    private void calculateMaxPoints() {
        this.maxPoints = 0;
        for (Question question : questions) {
            this.maxPoints += question.getMaxPoints();
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
        maxPoints += question.getMaxPoints();
    }

    public void removeQuestion(Question question) {
        if (questions.remove(question)) {
            maxPoints -= question.getMaxPoints();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        calculateMaxPoints();
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public int getQuestionCount() {
        return questions.size();
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public boolean isEmpty() {
        return questions.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("Test{name='%s', questions=%d, maxPoints=%d}",
            name, questions.size(), maxPoints);
    }
}
