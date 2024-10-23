package com.example.auta.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Driver {
    private int id = -1;

    @NotBlank
    private String name;

    @Size(min = 18, max = 99, message = "Enter a valid age")
    private int age;

    @Size(min = 10, max = 999999, message = "Enter a valid salary")
    private int salary;


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
