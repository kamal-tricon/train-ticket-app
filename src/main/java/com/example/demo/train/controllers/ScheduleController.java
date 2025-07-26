package com.example.demo.train.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.train.dtos.ScheduleDTO;
import com.example.demo.train.entities.Schedule;
import com.example.demo.train.services.ScheduleService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

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

    @GetMapping("")
    public ResponseEntity<List<ScheduleDTO>> findAll(@RequestParam("trainNumber") String trainNumber) {
        List<Schedule> schedules = this.scheduleService.findAll(trainNumber);
        List<ScheduleDTO> dtos = schedules.stream().map(schedule -> {
            LocalTime arrivalTime = schedule.getArrivalTime();
            LocalTime departureTime = schedule.getDepartureTime();
            return new ScheduleDTO(
                schedule.getId(),
                schedule.getTrain().getId(), 
                schedule.getTrain().getNumber(),
                schedule.getStation().getId(), 
                schedule.getStation().getCode(),
                arrivalTime == null ? null : arrivalTime.toString(),
                departureTime == null ? null : departureTime.toString(),
                schedule.getDay()
            );
        }).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/trains-between")
    public ResponseEntity<List<Object[]>> getMethodName(@RequestParam("fromStation") long startId, @RequestParam("toStation") long toId) {
        List<Object[]> trains = this.scheduleService.getTrainsBetweenTwoStations(startId, toId);
        return ResponseEntity.status(HttpStatus.OK).body(trains);
    }
    

}
