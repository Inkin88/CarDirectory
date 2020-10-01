package ru.muzafarov.test.cardirectory.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.muzafarov.test.cardirectory.dto.CarDto;
import ru.muzafarov.test.cardirectory.exception.ValidationException;
import ru.muzafarov.test.cardirectory.service.CarService;

import java.util.List;


@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarRestController {

    public final CarService carService;

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable("id") Integer carId) {
        return carService.getById(carId);
    }

    @PostMapping("/save")
    public CarDto saveCar(@RequestBody CarDto car) throws ValidationException {
        return carService.saveCar(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") Integer id) {
        carService.delete(id);
    }

    @GetMapping
    public List<CarDto> getAll() {
        return carService.getAll();
    }

}
