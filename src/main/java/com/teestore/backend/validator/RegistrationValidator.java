package com.teestore.backend.validator;

import com.teestore.backend.model.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RegistrationValidator {

    public static void validateRegistration(User u) throws Exception {
        if (!validateContactNumber(u.getContactNumber()))
            throw new Exception("RegistrationValidator.INVALID_CONTACT_NUMBER_FORMAT");
        if (!validatePassword(u.getPassword()))
            throw new Exception("RegistrationValidator.INVALID_PASSWORD_FORMAT");
        if (!validateEmail(u.getEmailId()))
            throw new Exception("RegistrationValidator.INVALID_EMAIL_ID_FORMAT");
        if (!validateName(u.getUserName()))
            throw new Exception("RegistrationValidator.INVALID_USERNAME_FORMAT");
        if (!validateDateOfBirth(u.getDateOfBirth()))
            throw new Exception("RegistrationValidator.INVALID_DATE_OF_BIRTH_FORMAT");

    }

    private static Boolean validatePassword(String password) {
        if (password == null)
            return false;
        if (password.length() >= 7 && password.length() <= 20)
            if (password.matches(".*[A-Z]+.*"))
                if (password.matches(".*[a-z]+.*"))
                    if (password.matches(".*[0-9]+.*"))
                        return password.matches(".*[!@#$%^&*].*");
        return false;
    }

    private static Boolean validateContactNumber(String contactNumber) {
        if (contactNumber == null)
            return false;
        boolean flag = false;
        if (contactNumber.matches("[6-9][0-9]{9}"))
            flag = true;
        return flag;
    }

    private static Boolean validateEmail(String email) {
        if (email == null || email.length() > 70)
            return false;
        String reg = "^[a-zA-z]+[A-Za-z0-9_.-]+[A-Za-z0-9]+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]+$";
        return email.matches(reg);
    }

    private static Boolean validateName(String name) {
        if (name == null || name.length() > 50)
            return false;
        if (!name.equals("")) {
            String reg = "([A-Za-z]{2,})+( [A-Za-z]{2,}){0,2}";
            return name.matches(reg);
        }
        return false;
    }

    private static Boolean validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null)
            return false;
        return dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS) > 16;
    }
}
