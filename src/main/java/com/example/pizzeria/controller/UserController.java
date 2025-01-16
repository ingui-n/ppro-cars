package com.example.pizzeria.controller;

import com.example.pizzeria.model.User;
import com.example.pizzeria.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping({"/signup", "/sign-up"})
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/sign_up";
    }

    @PostMapping({"/signup", "/sign-up"})
    public String signUp(@Valid User user, BindingResult bindingResult, Model model) {
        user.setRole("USER");

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "auth/sign_up";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword.invalid");
            model.addAttribute("user", user);
            return "auth/sign_up";
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "username.exists");
            model.addAttribute("user", user);
            return "auth/sign_up";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));

        userService.signUp(user);
        return "redirect:/";
    }

    @GetMapping({"/signin", "/sign-in"})
    public String signInPage(Model model) {
        return "auth/sign_in";
    }
}
