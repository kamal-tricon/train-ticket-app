package com.example.demo.station.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.station.dtos.StationDTO;
import com.example.demo.station.entities.Station;
import com.example.demo.station.services.StationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping("")
    public ResponseEntity<StationDTO> create(@Valid @RequestBody StationDTO station) {
        Station createdStation = stationService.createStation(station);
        StationDTO dto = new StationDTO();
        dto.setId(createdStation.getId());
        dto.setName(createdStation.getName());
        dto.setCode(createdStation.getCode());
        dto.setCityId(createdStation.getCity().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("")
    public List<StationDTO> getAll() {
        return stationService.getAll();
    }

    @GetMapping("/{id}")
    public StationDTO getOne(@PathVariable long id) {
        return stationService.getById(id);
    }

    @GetMapping("/by-city/{id}")
    public List<StationDTO> getOneByCity(@PathVariable long id) {
        return stationService.getByCity(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
