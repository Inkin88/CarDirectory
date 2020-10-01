package ru.muzafarov.test.cardirectory.service;

import ru.muzafarov.test.cardirectory.dto.CarDto;
import ru.muzafarov.test.cardirectory.exception.ValidationException;

import java.util.List;

public interface CarService {

    CarDto getById(Integer id);

    List<CarDto> getAll();

    CarDto saveCar(CarDto car) throws ValidationException;

    void delete(Integer id);
}
