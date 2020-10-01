package ru.muzafarov.test.cardirectory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.muzafarov.test.cardirectory.model.Car;


public interface CarRepository extends JpaRepository<Car, Integer> {
}
