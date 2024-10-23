package com.example.auta.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Car {
    private int id = -1;

    @Size(min = 7, max = 7, message = "Enter a valid spz")
    private String spz;

    @NotBlank(message = "Enter a color")
    private String color;

    @Min(value = 1, message = "Enter a valid tank volume")
    @Max(value = 100, message = "Enter a valid tank volume")
    private float tankVolume;

    @Min(value = 1, message = "Enter a valid number of seats")
    @Max(value = 60, message = "Enter a valid number of seats")
    private int numberOfSeats;

    public Car(){}

    public Car(String spz, String color, float tankVolume, int numberOfSeats) {
        this.spz = spz;
        this.color = color;
        this.tankVolume = tankVolume;
        this.numberOfSeats = numberOfSeats;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(float tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
