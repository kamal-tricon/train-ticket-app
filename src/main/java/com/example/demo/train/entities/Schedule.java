package com.example.demo.train.entities;

import java.time.LocalTime;

import com.example.demo.common.models.enums.Days;
import com.example.demo.station.entities.Station;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "train_schedule", 
    uniqueConstraints = { @UniqueConstraint(columnNames = { "train_id", "station_id" })},
    indexes = { @Index( name = "idx_train_station_stoppage", columnList = "train_id, station_id, stoppage_number")}
)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable =  false)
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column (name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "days")
    private Days day;

    @Column (name = "stoppage_number")
    private int stoppageNumber;


    public Schedule() {}

    
    public Schedule(Train train, Station station, LocalTime arrivalTime, LocalTime departureTime, Days day, int stoppageNumber) {
        this.train = train;
        this.station = station;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.day = day;
        this.stoppageNumber = stoppageNumber;
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Train getTrain() {
        return train;
    }
    
    public void setTrain(Train train) {
        this.train = train;
    }
    
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public int getStoppageNumber() {
        return stoppageNumber;
    }


    public void setStoppageNumber(int stoppageNumber) {
        this.stoppageNumber = stoppageNumber;
    }
    
    
}
