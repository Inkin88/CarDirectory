package ru.muzafarov.test.cardirectory.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.muzafarov.test.cardirectory.service.CarService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.muzafarov.test.cardirectory.prototype.CarPrototype.aCarDto;

class CarRestControllerTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    CarService carService;

    @BeforeEach
    void setUp() {
        carService = mock(CarService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CarRestController(carService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void saveCar() throws Exception {
        when(carService.saveCar(any())).thenReturn(aCarDto());
        mockMvc.perform(post("/api/cars/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aCarDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(aCarDto())));
    }

    @Test
    void getAllCars() throws Exception {
        when(carService.getAll()).thenReturn(Collections.singletonList(aCarDto()));
        mockMvc.perform(get("/api/cars/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(aCarDto()))))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        when(carService.getById(any())).thenReturn(aCarDto());
        mockMvc.perform(get("/api/cars/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString((aCarDto()))))
                .andExpect(status().isOk());

    }

    @Test
    void deleteUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cars/1"))
                .andExpect(status().isNoContent());
    }
}

