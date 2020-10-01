package ru.muzafarov.test.cardirectory.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.muzafarov.test.cardirectory.dto.CarDto;
import ru.muzafarov.test.cardirectory.exception.ValidationException;
import ru.muzafarov.test.cardirectory.model.Car;
import ru.muzafarov.test.cardirectory.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.muzafarov.test.cardirectory.prototype.CarPrototype.*;

class CarServiceImplTest {
    private CarRepository carRepository;
    private CarConverter carConverter;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        carConverter = new CarConverter();
        carService = new CarServiceImpl(carRepository, carConverter);
    }

    @Test
    void saveUser() throws ValidationException {
        when(carRepository.save(any())).thenReturn(aCar());
        CarDto createdCar = carService.saveCar(aCarDto());
        assertThat(createdCar).isNotNull();
        assertThat(createdCar.getBrand()).isEqualTo(aCarDto().getBrand());
    }

    @Test
    void saveUserThrowsValidationExceptionWhenLoginIsNull() {
        CarDto carDto = aCarDto();
        carDto.setBrand("");
        assertThrows(ValidationException.class,
                () -> carService.saveCar(carDto),
                "Brand is empty");
    }

    @Test
    void deleteUser() {
        CarDto carDto = aCarDto();
        assertThat(carDto).isNotNull();
        carService.delete(1);
        assertThat(carService.getById(1)).isNull();

    }

    @Test
    void getById() {
        when(carRepository.getOne(eq(1))).thenReturn(aCar());
        CarDto foundUser = carService.getById(1);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(1);
    }
}

