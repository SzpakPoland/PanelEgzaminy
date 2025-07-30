package service;

import model.Question;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    private static final Pattern QUESTION_PATTERN =
            Pattern.compile("^(.+?)\\s+PMAX:\\s*(\\d+)\\s*$");

    public static List<Question> readQuestions(String filePath) throws IOException {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                Matcher matcher = QUESTION_PATTERN.matcher(line);
                if (matcher.matches()) {
                    String questionText = matcher.group(1).trim();
                    int maxPoints = Integer.parseInt(matcher.group(2));

                    Question question = new Question();
                    question.setQuestionText(questionText);
                    question.setMaxPoints(maxPoints);

                    questions.add(question);
                } else {
                    throw new IllegalArgumentException(
                            "Nieprawidłowy format pytania: " + line);
                }
            }
        }

        if (questions.isEmpty()) {
            throw new IllegalArgumentException("Plik nie zawiera żadnych pytań");
        }

        return questions;
    }
}