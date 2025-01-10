package com.example.pizzeria.service;

import com.example.pizzeria.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<Car> getAllCars();
    Car getCarById(long id);
    void deleteCarById(long id);
    void saveCar(Car car);
}
