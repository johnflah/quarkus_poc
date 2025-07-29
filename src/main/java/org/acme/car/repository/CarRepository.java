package org.acme.car.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.car.entity.Car;
@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
}
