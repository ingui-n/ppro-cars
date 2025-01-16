package com.example.pizzeria;

import com.example.pizzeria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzeriaApplication {

    private final UserService userService;

    @Autowired
    public PizzeriaApplication(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            userService.addUser("admin",
                    "heslo",
                    "heslo",
                    "ADMIN",
                    "John",
                    "Adminovan",
                    "tin@ten.com",
                    "741258963"
            );
            //todo add pizzas, ingredients and other stuff
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }
}
