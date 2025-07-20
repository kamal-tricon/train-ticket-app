package com.example.demo.train.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TrainDTO {

    private long id;

    @NotBlank(message = "Train number cannot be empty")
    @Size(min = 5, max = 5)
    private String trainNumber;

    @NotBlank(message = "Train name cannot be empty")
    @Size(min = 5, max = 100)
    private String trainName;

    private int coachCountAC1 = 0;
    private int coachCountAC2 = 0;
    private int coachCountAC3 = 0;
    private int coachCountSleeper = 0;
    private int coachCountGS = 0;
    private int coachCountCC = 0;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTrainNumber() {
        return trainNumber;
    }
    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
    public String getTrainName() {
        return trainName;
    }
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    public int getCoachCountAC1() {
        return coachCountAC1;
    }
    public void setCoachCountAC1(int coachCountAC1) {
        this.coachCountAC1 = coachCountAC1;
    }
    public int getCoachCountAC2() {
        return coachCountAC2;
    }
    public void setCoachCountAC2(int coachCountAC2) {
        this.coachCountAC2 = coachCountAC2;
    }
    public int getCoachCountAC3() {
        return coachCountAC3;
    }
    public void setCoachCountAC3(int coachCountAC3) {
        this.coachCountAC3 = coachCountAC3;
    }
    public int getCoachCountSleeper() {
        return coachCountSleeper;
    }
    public void setCoachCountSleeper(int coachCountSleeper) {
        this.coachCountSleeper = coachCountSleeper;
    }
    public int getCoachCountGS() {
        return coachCountGS;
    }
    public void setCoachCountGS(int coachCountGS) {
        this.coachCountGS = coachCountGS;
    }
    public int getCoachCountCC() {
        return coachCountCC;
    }
    public void setCoachCountCC(int coachCountCC) {
        this.coachCountCC = coachCountCC;
    }

    
}
