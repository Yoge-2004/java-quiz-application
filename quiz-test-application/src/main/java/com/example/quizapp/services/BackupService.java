package com.example.quizapp.services;

import com.example.quizapp.models.Question;
import com.example.quizapp.models.User;

import java.io.*;
import java.util.List;

public class BackupService {
    static {

        File folder = new File("backup");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File userFile = new File("backup/users.dat");
        File questionFile = new File("backup/questions.dat");

        if (!userFile.exists()) {
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                System.err.println("File cannot be created ❌");
            }
        }

        if (!questionFile.exists()) {
            try {
                questionFile.createNewFile();
            } catch (IOException e) {
                System.err.println("File cannot be created ❌");
            }
        }
    }

    public static void createBackup() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("backup/users.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(UserService.getAllUsers());
        }

        try (FileOutputStream fos = new FileOutputStream("backup/questions.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(QuestionService.getAllQuestions());
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadBackup() throws IOException, ClassNotFoundException {
        if (!(new File("backup/users.dat").length() > 0)) return;

        try (FileInputStream fis = new FileInputStream("backup/users.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            UserService.setAllUsers((List<User>) ois.readObject());
        }

        if (!(new File("backup/questions.dat").length() > 0)) return;

        try (FileInputStream fis = new FileInputStream("backup/questions.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            QuestionService.setAllQuestions((List<Question>) ois.readObject());
        }
    }
}