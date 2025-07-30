package src.utils;

public class ValidationUtils {

    public static boolean isValidPoints(int points) {
        return points >= 0;
    }

    public static boolean isValidPercentage(double percentage) {
        return percentage >= 0 && percentage <= 100;
    }

    public static boolean isNonEmptyString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isValidQuestionFormat(String line) {
        return line != null && line.matches("^.+\\s+PMAX:\\s*\\d+\\s*$");
    }
}

