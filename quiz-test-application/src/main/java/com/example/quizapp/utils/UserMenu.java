package com.example.quizapp.utils;

import com.example.quizapp.exceptions.QuestionException;
import com.example.quizapp.exceptions.UserException;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.User;
import com.example.quizapp.services.QuestionService;
import com.example.quizapp.services.UserService;

import java.io.IOException;

import static com.example.quizapp.services.BackupService.createBackup;

public class UserMenu {
    public static void loadUserMenu(User user) throws UserException, QuestionException, IOException {
        String userMenu =
                """
                        ====================================
                        1. Modify username
                        2. Modify password
                        3. Modify mobile number
                        4. Start Quiz
                        5. Check Score
                        6. Delete Account
                        7. Exit
                        =====================================
                        """;

        while (true) {
            IO.println(userMenu);

            switch (Integer.parseInt(IO.readln("Enter your choice: "))) {
                case 1 -> UserService.modifyUserName(user.getEmailId(), IO.readln("Enter your new username: "));

                case 2 -> UserService.modifyPassword(user.getEmailId(), IO.readln("Enter your new password: "));

                case 3 ->
                        UserService.modifyMobileNumber(user.getEmailId(), IO.readln("Enter your new mobile number: "));

                case 4 -> {

                    long start = System.currentTimeMillis();
                    for (int i = 1; i <= 5; i++) {
                        Question question = QuestionService.getQuestion();

                        IO.println("Question " + i + ": ");
                        IO.println(QuestionService.presentQuestion(question));

                        user.setTotalScore(user.getTotalScore() + question.getMarks());

                        if (QuestionService.validateAnswerOption(question, IO.readln("Enter your choice: ").toLowerCase().charAt(0))) {
                            user.setScore(user.getScore() + question.getMarks());
                        }
                    }
                    long end = System.currentTimeMillis();
                    user.setTimeTaken(end - start);

                    System.out.println("Time Taken: " + (user.getTimeTaken() / 60000) + " mins");

                    IO.println("Quiz is completed successfully ✅");
                }

                case 5 -> {
                    IO.println("Your score: " + UserService.getScore(user) + " out of " + user.getTotalScore());
                    double percentage = UserService.getScore(user) / user.getTotalScore() * 100.0;

                    System.out.println("Time Taken: " + (user.getTimeTaken() / 60000) + " mins");

                    IO.println("Percentage: " + percentage + "%\n");
                    IO.println("Pass/Fail: " + ((percentage >= 50.0) ? "Pass ✅" : "Fail ❌"));
                }

                case 6 -> {
                    UserService.deleteUser(user);
                    return;
                }

                default -> {
                    return;
                }
            }
            createBackup();
        }
    }
}