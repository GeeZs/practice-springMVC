package com.cheshire;

import com.cheshire.dao.UserDAO;
import com.cheshire.model.User;
import com.cheshire.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name, Model model) {
        model.addAttribute("msg", "Привет " + name);
        return "/index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw() {
        return "Raw data";
    }

    @GetMapping("/users")
    public String getUsers(Model model) throws SQLException {
        model.addAttribute("users", userDAO.getAll());
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model){
        model.addAttribute("user", new User());
        return "/signUp";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) throws SQLException {
        userValidator.validate(user, result);
        if (result.hasErrors()){
            return "/signUp";
        }
        userDAO.addUser(user);
        return "redirect:/users";
    }
}
