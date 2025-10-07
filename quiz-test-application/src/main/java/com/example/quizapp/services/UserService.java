package com.example.quizapp.services;

import com.example.quizapp.exceptions.UserException;
import com.example.quizapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<User> users = new ArrayList<>();

    public static void addUser(String name, String emailId, String password, String mobileNumber) {
        users.add(new User(name, emailId, password, mobileNumber));

        IO.println("User created successfully ✅");
        IO.println();
    }

    public static void modifyUserName(String emailId, String userName) throws UserException {
        User user = findUserByEmail(emailId);

        if (user == null) throw new UserException("User is not found ❌");

        String oldName = user.getName();
        user.setName(userName);

        IO.println("Username is modified from " + oldName + " -> " + user.getName() + " ✅");
        IO.println();
    }

    public static void modifyMobileNumber(String emailId, String mobileNumber) throws UserException {
        User user = findUserByEmail(emailId);

        if (user == null) throw new UserException("User is not found ❌");

        String oldMobileNumber = user.getMobileNumber();
        user.setMobileNumber(mobileNumber);

        IO.println("Mobile number is modified from " + oldMobileNumber + " -> " + user.getMobileNumber() + "  ✅");
        IO.println();
    }

    public static void modifyPassword(String emailId, String password) throws UserException {
        User user = findUserByEmail(emailId);

        if (user == null) throw new UserException("User is not found ❌");

        user.setName(password);

        IO.println("Password is updated successfully ✅");
        IO.println();
    }

    public static User findUserByEmail(String emailId) {
        return users.parallelStream().filter(user -> user.getEmailId().equals(emailId)).findFirst().orElse(null);
    }

    public static double getScore(User user) throws UserException {
        if (user == null) throw new UserException("User is not found ❌");
        return user.getScore();
    }

    public static void deleteUser(User user) throws UserException {
        if (user == null) throw new UserException("User is not found ❌");

        users.remove(user);

        IO.println("Your account has been removed successfully ✅");
        IO.println();
    }

    public static List<User> getAllUsers() {
        return users;
    }

    public static void setAllUsers(List<User> userList) {
        users = userList;
    }
}