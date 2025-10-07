package com.example.quizapp.tests;

import com.example.quizapp.services.QuestionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.quizapp.services.BackupService.createBackup;

public class QuestionsLoader {
    public static void loadQuestions() throws IOException {
        do {
            String question = IO.readln("Enter the question: ");

            List<String> options = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                options.add(IO.readln("Enter the option " + i + ": "));
            }

            options.stream().forEach(IO::println);

            String correctOption = options.get(Integer.parseInt(IO.readln("Enter the option number: ")) - 1);
            String category = IO.readln("Enter the category: ");

            byte marks = Byte.parseByte(IO.readln("Enter marks: "));

            QuestionService.addQuestion(question, options, correctOption, category, marks);
            IO.println("Question Loaded ✅");
            IO.println();
            createBackup();

        } while (IO.readln("Do you want to continue?\n").toLowerCase().startsWith("y"));

        IO.println();
    }
}
