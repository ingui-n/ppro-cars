package com.example.auta.service;

import com.example.auta.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarServiceImpl  implements CarService {
    ArrayList<Car> cars = new ArrayList<>();

    @Override
    public ArrayList<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        if (id < 0 || id >= cars.size()) {
            return null;
        }

        return cars.get(id);
    }

    @Override
    public void deleteCarById(int id) {
        if (id < 0 || id >= cars.size()) {
            return;
        }

        cars.remove(id);
    }

    @Override
    public void saveCar(Car car) {
        if(car.getId() > -1 && car.getId() < cars.size()){
            cars.remove(car.getId());
        }
        cars.add(car);
    }

}
