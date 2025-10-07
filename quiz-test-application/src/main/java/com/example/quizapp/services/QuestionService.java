package com.example.quizapp.services;

import com.example.quizapp.exceptions.QuestionException;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionService {
    private static List<Question> questionList = new ArrayList<>();
    private static Random random = new Random();

    public static boolean validateAnswerOption(Question question, char c) {
        if (c == ' ') return false;

        return question.getOptions().get(c - 'a').equals(question.getCorrectOption());
    }

    public static List<Question> getAllQuestions() {
        return questionList;
    }

    public static void setAllQuestions(List<Question> questionListData) {
        questionList = questionListData;
    }

    public static void addQuestion(String question, List<String> options, String correctOption, String category, byte marks) {
        questionList.add(new Question(question, options, correctOption, category, marks));
    }

    public static Question getQuestion() throws QuestionException {
        if (questionList.isEmpty()) throw new QuestionException("Question list is empty ❌");

        Collections.shuffle(questionList);
        return questionList.get(random.nextInt(questionList.size()));
    }

    public static String presentQuestion(Question question) throws QuestionException {

        if (question == null) return "";

        List<String> options = question.getOptions();
        Collections.shuffle(options);

        StringBuilder formattedQuestion = new StringBuilder();
        formattedQuestion.append("--------------------------------------------------\n");
        formattedQuestion.append(question.getQuestion() + "\nCategory: " + question.getCategory() + "\nMarks: " + question.getMarks() + "\n");

        for (int i = 0; i < options.size(); i++) {
            char ch = (char) ('a' + i);

            formattedQuestion.append(ch + ". " + options.get(i));
            formattedQuestion.append("\n");
        }

        formattedQuestion.append("--------------------------------------------------\n");
        return formattedQuestion.toString();
    }
}
