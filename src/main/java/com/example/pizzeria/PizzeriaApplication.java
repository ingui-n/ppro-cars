package com.example.pizzeria;

import com.example.pizzeria.model.User;
import com.example.pizzeria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PizzeriaApplication {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public PizzeriaApplication(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

           // addUser("admin", "heslo123456", "ADMIN", "man", "pan", "tin@ten.com", "741258963");
//            addUser("user", "heslo123456", "USER");
        };
    }

    private void addUser(String username, String password, String role, String firstName, String lastName, String email, String phoneNumber) {
        if (userService.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userService.signUp(user);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }

}
