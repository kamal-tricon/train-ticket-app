package com.example.demo.station.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.common.controllers.InvalidInputException;
import com.example.demo.common.controllers.InvalidInputsException;
import com.example.demo.common.models.CustomErrorResponse;
import com.example.demo.common.models.CustomErrorsResponse;
import com.example.demo.common.services.ErrorConverterService;
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

    @Autowired
    private ErrorConverterService errorConverterService;

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
    public ResponseEntity<List<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> list = errorConverterService.getAllFieldsErrorsAndConvertToArray(ex.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // List<Map<String, String>> list = errorConverterService.getAllFieldsErrorsAndConvertToArray(ex.);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidInput(InvalidInputException ex, WebRequest req) {
        CustomErrorResponse cer = this.errorConverterService.convertToGlobalErrorResponse(ex, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cer);
    }

    @ExceptionHandler(value = InvalidInputsException.class)
    public ResponseEntity<CustomErrorsResponse> handleInvalidInputs(InvalidInputsException ex, WebRequest req) {
        CustomErrorsResponse cer = this.errorConverterService.convertToGlobalErrorsResponse(ex, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cer);
    }

}
