package model;

public class TestResult {
    private int totalPoints;
    private int maxPoints;
    private int errors;
    private double percentage;

    public TestResult(int totalPoints, int maxPoints, int errors) {
        this.totalPoints = totalPoints;
        this.maxPoints = maxPoints;
        this.errors = errors;
        calculatePercentage();
    }

    private void calculatePercentage() {
        if (maxPoints > 0) {
            this.percentage = (double) totalPoints / maxPoints * 100;
        } else {
            this.percentage = 0;
        }
    }

    public int getTotalPoints() { return totalPoints; }
    public int getMaxPoints() { return maxPoints; }
    public int getErrors() { return errors; }
    public double getPercentage() { return percentage; }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
        calculatePercentage();
    }

    public void setErrors(int errors) {
        this.errors = errors;
        this.totalPoints = Math.max(0, this.totalPoints - errors);
        calculatePercentage();
    }
}

