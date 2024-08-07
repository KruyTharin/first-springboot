package com.example.first_springboot.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cars")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class CarController {
    private final CarService carService;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return carService.getAllCars();
    }


    @GetMapping("/{name}")
    public ResponseEntity<Car> getCarByName(@PathVariable String name) {
        return  carService.getCarByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<Car> AddCar(@RequestBody Car car) {
        return  carService.AddCar(car);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
       return  carService.updateCar(id, car);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        return  carService.deleteCar(id);
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllCars() {
       return  carService.deleteAllCar();
    }


    @GetMapping("/wheels/{wheels}")
    public ResponseEntity<List<Car>> getCarsByWheels(@PathVariable String  wheels) {
     return  carService.getCarsByWheels(wheels);
    }
}
