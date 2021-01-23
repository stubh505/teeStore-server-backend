package com.teestore.backend.validator;

import com.teestore.backend.model.Contact;

public class ContactValidator {

    public static void validateContact(Contact u) throws Exception {
        if (!validateContactNumber(u.getPhoneNo()))
            throw new Exception("ContactValidator.INVALID_CONTACT_NUMBER_FORMAT");
        if (!validateEmail(u.getContactEmail()))
            throw new Exception("ContactValidator.INVALID_EMAIL_ID_FORMAT");
        if (!validateSubject(u.getSubject()))
            throw new Exception("ContactValidator.INVALID_SUBJECT_FORMAT");
        if (!validateMessage(u.getMessage()))
            throw new Exception("ContactValidator.INVALID_MESSAGE_FORMAT");
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

    private static Boolean validateSubject(String name) {
        if (name == null || name.length() > 50)
            return false;
        if (!name.equals("")) {
            String reg = "([A-Za-z]+)+( [A-Za-z]+){0,15}";
            return name.matches(reg);
        }
        return false;
    }

    private static Boolean validateMessage(String message) {
        return message != null && message.length() <= 200;
    }
}