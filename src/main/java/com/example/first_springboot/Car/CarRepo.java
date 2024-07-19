package com.example.first_springboot.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Optional<Car> findCarByName(String name);
    List<Car> findAllCarByWheels(String wheels);
}
