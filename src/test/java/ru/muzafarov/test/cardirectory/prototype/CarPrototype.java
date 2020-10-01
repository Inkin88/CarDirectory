package ru.muzafarov.test.cardirectory.prototype;

import ru.muzafarov.test.cardirectory.dto.CarDto;
import ru.muzafarov.test.cardirectory.model.Car;

public class CarPrototype {
    public static Car aCar() {
        Car car = new Car();
        car.setId(1);
        car.setNumber("A777AA116");
        car.setBrand("KIA RIO");
        car.setColor("Silver");
        car.setYear(2015);
        return car;
    }

    public static CarDto aCarDto() {
        return CarDto.builder().
                id(1).
                number("A777AA116").
                brand("KIA RIO").
                color("Silver").
                year(2015).
                build();
    }
    public static CarDto aCarDto2() {
        return CarDto.builder().
                id(2).
                number("A111AA116").
                brand("KIA RIO").
                color("Black").
                year(2016).
                build();
    }
}
