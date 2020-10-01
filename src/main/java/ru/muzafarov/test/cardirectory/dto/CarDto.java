package ru.muzafarov.test.cardirectory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Integer id;
    private String number;
    private String brand;
    private String color;
    private int year;
}
