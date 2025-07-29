package org.acme.car.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import org.acme.car.repository.CarRepository;
import org.acme.car.entity.Car;
@ApplicationScoped
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> index(){
        return carRepository.findAll().list();
    }

}
