package model;

public class TestResult {
    private double totalPoints;
    private double maxPoints;
    private int errors;
    private double percentage;

    public TestResult(double totalPoints, double maxPoints, int errors) {
        this.totalPoints = totalPoints;
        this.maxPoints = maxPoints;
        this.errors = errors;
        calculatePercentage();
    }

    private void calculatePercentage() {
        if (maxPoints > 0) {
            this.percentage = totalPoints / maxPoints * 100;
        } else {
            this.percentage = 0;
        }
    }

    public double getTotalPoints() { return totalPoints; }
    public double getMaxPoints() { return maxPoints; }
    public int getErrors() { return errors; }
    public double getPercentage() { return percentage; }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
        calculatePercentage();
    }

    public void setErrors(int errors) {
        this.errors = errors;
        this.totalPoints = Math.max(0, this.totalPoints - errors);
        calculatePercentage();
    }
}
