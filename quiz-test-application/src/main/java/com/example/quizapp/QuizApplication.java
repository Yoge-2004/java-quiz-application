import com.example.quizapp.models.User;

import org.mindrot.jbcrypt.BCrypt;

import static com.example.quizapp.services.BackupService.createBackup;
import static com.example.quizapp.services.BackupService.loadBackup;
import static com.example.quizapp.services.UserService.addUser;
import static com.example.quizapp.services.UserService.findUserByEmail;
import static com.example.quizapp.tests.QuestionsLoader.loadQuestions;
import static com.example.quizapp.utils.InputValidation.validateSignup;
import static com.example.quizapp.utils.UserMenu.loadUserMenu;

void main() throws IOException, ClassNotFoundException {
    String menuOptions =
            """
                  1. Signup
                  2. Login
                  3. Load Questions
                  4. Exit  
                    """;

    IO.println("======================= Quiz Application =======================");

    loadBackup();

    while (true) {
        IO.println(menuOptions);

        try {
            switch (Integer.parseInt(IO.readln("Enter your choice: "))) {
                case 1 -> {
                    String name = IO.readln("Enter your name: ");
                    String emailId = IO.readln("Enter your email id: ");
                    String password = IO.readln("Enter your password: ");
                    String mobileNumber = IO.readln("Enter your mobile number: ");

                    User existingUser = findUserByEmail(emailId);
                    if (!validateSignup(name, emailId, password, mobileNumber) || existingUser != null && existingUser.getEmailId().equalsIgnoreCase(emailId)) {
                        System.err.println("User cannot be created with the above credentials ❌\n");
                        continue;
                    }

                    addUser(name, emailId, BCrypt.hashpw(password, BCrypt.gensalt()), mobileNumber);
                }

                case 2 -> {
                    String emailId = IO.readln("Enter your email id: ");
                    String password = IO.readln("Enter your password: ");

                    User user = findUserByEmail(emailId);

                    if (user == null) {
                        System.err.println("User is not found with the given id ❌\n");
                        continue;
                    }

                    if (BCrypt.checkpw(password, user.getPassword())) {
                        IO.println("Welcome, " + user.getName());
                        loadUserMenu(user);
                    }
                }

                case 3 -> {
                    String emailId = IO.readln("Enter your email id: ");
                    String password = IO.readln("Enter your password: ");

                    if (emailId.equals("admin@abc.com") && password.equals("Admin@123"))
                        loadQuestions();
                }

                default -> {
                    IO.println("Thanks for using our application!!!");
                    System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            IO.println();

            main();
        }
    }
}