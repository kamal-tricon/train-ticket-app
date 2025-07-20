package com.example.demo.train.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.common.controllers.InvalidInputException;
import com.example.demo.common.models.CustomErrorResponse;
import com.example.demo.common.services.ErrorConverterService;
import com.example.demo.train.dtos.ScheduleDTO;
import com.example.demo.train.entities.Schedule;
import com.example.demo.train.services.ScheduleService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ErrorConverterService errorConverterService;

    @PostMapping("")
    public ResponseEntity<ScheduleDTO> save(@Valid @RequestBody ScheduleDTO request) {

        Schedule schedule = scheduleService.save(request);

        LocalTime arrivalTime = schedule.getArrivalTime();
        LocalTime departureTime = schedule.getDepartureTime();

        ScheduleDTO entity = new ScheduleDTO(
            schedule.getId(),
            schedule.getTrain().getId(), 
            schedule.getTrain().getNumber(),
            schedule.getStation().getId(), 
            schedule.getStation().getCode(), 
            arrivalTime == null ? null : arrivalTime.toString(),
            departureTime == null ? null : departureTime.toString(),
            schedule.getDay()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
    

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidInput(InvalidInputException ex, WebRequest req) {
        CustomErrorResponse cer = this.errorConverterService.convertToGlobalErrorResponse(ex, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cer);
    } 

}
