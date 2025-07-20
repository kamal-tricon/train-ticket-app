package com.example.demo.train.dtos;

import java.time.LocalTime;

import com.example.demo.common.models.enums.Days;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;

public class ScheduleDTO {

    private long id;

    private long trainId;

    private String trainNumber;

    private long stationId;

    private String stationCode;

    @Nullable
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Arrival time must be in HH:MM format")
    private String arrivalTime;

    @Nullable
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Departure time must be in HH:MM format")
    private String departureTime;

    private Days days;

    public ScheduleDTO(long id, long trainId, String trainNumber, long stationId, String stationCode, String arrivalTime, String departureTime,
            Days days) {
        this.id = id;
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.stationId = stationId;
        this.stationCode = stationCode;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.days = days;
    }

    public ScheduleDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    
}
