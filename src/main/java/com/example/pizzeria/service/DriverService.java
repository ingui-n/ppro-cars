package com.example.pizzeria.service;

import com.example.pizzeria.model.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {

    List<Driver> getAllDrivers();
    Driver getDriverById(long id);
    void deleteDriverById(long id);
    void saveDriver(Driver car);
}
