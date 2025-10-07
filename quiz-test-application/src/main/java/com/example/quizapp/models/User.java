package com.example.quizapp.models;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int id = 0;
    private String name;
    private String emailId;
    private String password;
    private String mobileNumber;
    private double score;
    private double totalScore;
    private long timeTaken;

    {
        id++;
    }

    public User() {
    }

    public User(String name, String emailId, String password, String mobileNumber) {
        setName(name);
        setEmailId(emailId);
        setPassword(password);
        setMobileNumber(mobileNumber);
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public long getTimeTaken() { return timeTaken; }

    public void setTimeTaken(long timeTaken) { this.timeTaken = timeTaken; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(score, user.score) == 0 && Objects.equals(name, user.name) && Objects.equals(emailId, user.emailId) && Objects.equals(password, user.password) && Objects.equals(mobileNumber, user.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailId, password, mobileNumber, score);
    }
}
