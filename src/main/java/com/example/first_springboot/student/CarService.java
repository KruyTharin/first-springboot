package com.example.first_springboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    public ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> carList = new ArrayList<>();
            carRepo.findAll().forEach(carList::add);

            if (carList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(carList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> carObj = carRepo.findById(id);
        if (carObj.isPresent()) {
            return new ResponseEntity<>(carObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Car> getCarByName(@PathVariable String name) {
        Optional<Car> carObj = carRepo.findCarByName(name);
        if (carObj.isPresent()) {
            return new ResponseEntity<>(carObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Car>> getCarsByWheels(@PathVariable String  wheels) {
        List<Car> cars = carRepo.findAllCarByWheels(wheels);
        if (!cars.isEmpty()) {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Car> AddCar(@RequestBody Car car) {
        try {
            Car carObj = carRepo.save(car);
            return new ResponseEntity<>(carObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        try {
            Optional<Car> carData = carRepo.findById(id);
            if (carData.isPresent()) {
                Car updateCarData = carData.get();
                updateCarData.setWheels(car.getWheels());
                updateCarData.setName(car.getName());

                Car carObj = carRepo.save(updateCarData);
                return new ResponseEntity<>(carObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        try {
            carRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllCar() {
        try {
            carRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
