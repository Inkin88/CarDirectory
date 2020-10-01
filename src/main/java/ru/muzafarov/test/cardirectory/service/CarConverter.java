package ru.muzafarov.test.cardirectory.service;

import org.springframework.stereotype.Component;
import ru.muzafarov.test.cardirectory.dto.CarDto;
import ru.muzafarov.test.cardirectory.model.Car;

@Component
public class CarConverter {

    public Car fromCarDtoToCar(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setBrand(carDto.getBrand());
        car.setColor(carDto.getColor());
        car.setNumber(carDto.getNumber());
        car.setYear(carDto.getYear());
        return car;
    }

    public CarDto fromCarToCarDto(Car car) {
        return CarDto.builder().
                id(car.getId()).
                brand(car.getBrand()).
                color(car.getColor()).
                number(car.getNumber()).
                year(car.getYear()).
                build();
    }
}
