package com.example.quizapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
    public static boolean validateSignup(String name, String emailId, String password, String mobileNumber) {

        final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9_]{2,30}");
        final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@_#&*])[A-Za-z\\d@_#&*]{8,15}$");
        final Pattern MOBILENUMBER_PATTERN = Pattern.compile("^\\+?[0-9]{1,3}[- ]?[0-9]{10}$");

        Matcher nameMatcher = NAME_PATTERN.matcher(name);
        Matcher emailMatcher = EMAIL_PATTERN.matcher(emailId);
        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
        Matcher mobileNumberMatcher = MOBILENUMBER_PATTERN.matcher(mobileNumber);

        if (nameMatcher.matches()) IO.println("Name is valid ✅");
        if (emailMatcher.matches()) IO.println("Email is valid ✅");
        if (passwordMatcher.matches()) IO.println("Password is valid ✅");
        if (mobileNumberMatcher.matches()) IO.println("Mobile number is valid ✅");

        return nameMatcher.matches() && emailMatcher.matches() && passwordMatcher.matches() && mobileNumberMatcher.matches();
    }
}