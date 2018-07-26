package com.cheshire.util;


import com.cheshire.dao.UserDAO;
import com.cheshire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {
        User user = (User) target;
        if (userDAO.getUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "", "This mail is already registered!");
        }
    }
}
