package com.example.auta.service;

import com.example.auta.model.Driver;
import com.example.auta.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService{

    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDriverById(long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            driverRepository.delete(driver.get());
        }
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }
}
