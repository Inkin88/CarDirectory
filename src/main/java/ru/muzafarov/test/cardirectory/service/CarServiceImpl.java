package ru.muzafarov.test.cardirectory.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.muzafarov.test.cardirectory.dto.CarDto;
import ru.muzafarov.test.cardirectory.exception.ValidationException;
import ru.muzafarov.test.cardirectory.model.Car;
import ru.muzafarov.test.cardirectory.repository.CarRepository;


import static java.util.Objects.isNull;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarServiceImpl.class);


    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Override
    public CarDto getById(Integer id) {
        Car car = carRepository.getOne(id);
        if (car != null) {
            return carConverter.fromCarToCarDto(car);
        }
        LOG.info("IN CarServiceImpl getById {}", id);
        return null;
    }

    @Override
    public List<CarDto> getAll() {
        LOG.info("IN CarServiceImpl getAll");
        return carRepository.
                findAll().
                stream().
                map(carConverter::fromCarToCarDto).
                collect(Collectors.toList());
    }

    @Override
    public CarDto saveCar(CarDto car) throws ValidationException {
        validateCarDto(car);
        Car savedCar = carRepository.save(carConverter.fromCarDtoToCar(car));
        LOG.info("IN CarServiceImpl saveCar {}", car);
        return carConverter.fromCarToCarDto(savedCar);
    }

    @Override
    public void delete(Integer id) {
        LOG.info("IN CarServiceImpl delete {}", id);;
        carRepository.deleteById(id);
    }

    private void validateCarDto(CarDto carDto) throws ValidationException {
        if (isNull(carDto)) {
            throw new ValidationException("Car is null");
        }
        if (isNull(carDto.getBrand()) || carDto.getBrand().isEmpty()) {
            throw new ValidationException("Car brand is null");
        }
        if (isNull(carDto.getColor()) || carDto.getColor().isEmpty()) {
            throw new ValidationException("Car color is null");
        }
        if (isNull(carDto.getNumber()) || carDto.getNumber().isEmpty()) {
            throw new ValidationException("Car number is null");
        }
        if (carDto.getYear() == 0) {
            throw new ValidationException("Car year is null");
        }
    }
}
