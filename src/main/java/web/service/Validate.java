package web.service;

import org.springframework.stereotype.Component;
import web.model.User;

@Component
public class Validate {

    private String upperCaseChars = "(.*[A-Z].*)";
    private String lowerCaseChars = "(.*[a-z].*)";
    private String numbers = "(.*[0-9].*)";

    public boolean checkName(User user) throws Exception {

        if (user.getName().length() > 1 && user.getName().matches(upperCaseChars) && user.getName().matches(lowerCaseChars)) {
            return true;
        } else {
            throw new Exception("Name doesn't contain a capital letter or too short!");
        }
    }

    public boolean checkEmail(User user) throws Exception {
        if (user.getEmail().length() > 3 && user.getEmail().contains("@") && user.getEmail().matches(lowerCaseChars)) {
            return true;
        } else {
            throw new Exception("Email doesn't contain the sign - @ or too short!");
        }
    }

    public boolean checkPassword(User user) throws Exception {
        if (user.getPassword().length() > 5 && user.getPassword().matches(lowerCaseChars) && user.getPassword().matches(numbers)) {
            return true;
        } else {
            throw new Exception("Password doesn't contain letters or numbers or too short!");
        }
    }
}
