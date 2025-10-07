package com.example.quizapp.models;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int id = 0;
    String question;
    List<String> options;
    String correctOption;
    String category;
    byte marks;

    {
        id++;
    }

    public Question(String question, List<String> options, String correctOption, String category, byte marks) {
        setQuestion(question);
        setOptions(options);
        this.correctOption = correctOption;
        setCategory(category);
        setMarks(marks);
    }

    public static int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public byte getMarks() {
        return marks;
    }

    public void setMarks(byte marks) {
        this.marks = marks;
    }

}
