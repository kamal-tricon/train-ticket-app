package com.example.demo.train.services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.common.controllers.InvalidInputException;
import com.example.demo.common.models.enums.Days;
import com.example.demo.station.entities.Station;
import com.example.demo.station.repositories.StationRepository;
import com.example.demo.train.dtos.ScheduleDTO;
import com.example.demo.train.entities.Schedule;
import com.example.demo.train.entities.Train;
import com.example.demo.train.repositories.ScheduleRepository;
import com.example.demo.train.repositories.TrainRepository;

import jakarta.transaction.Transactional;

@Service
public class ScheduleService {
    private final TrainRepository trainRepository;
    private final ScheduleRepository scheduleRepository;
    private final StationRepository stationRepository;

    public ScheduleService(
        TrainRepository trainRepository,
        ScheduleRepository scheduleRepository,
        StationRepository stationRepository
    ) {
        this.trainRepository = trainRepository;
        this.scheduleRepository = scheduleRepository;
        this.stationRepository = stationRepository;
    }

    @Transactional
    public Schedule save(ScheduleDTO scheduleDTO) {

        long trainId = scheduleDTO.getTrainId();
        String trainNumber = scheduleDTO.getTrainNumber();
        long stationId = scheduleDTO.getStationId();
        String stationCode = scheduleDTO.getStationCode();

        List<Train> trains = null;

        if (trainId == 0L && trainNumber == null) {
            throw new InvalidInputException("Please provide the train id OR train number", "INVALID_TRAIN_FIELD");
        } else {
            trains = this.trainRepository.findByNumberOrId(trainNumber, trainId);
            if (trains.size() == 0) {
                throw new InvalidInputException("No train was found with provided train id OR train number", "INVALID_TRAIN_FIELD");
            }
        }

        List<Station> stations = null;

        if (stationId == 0L && stationCode == null) {
            throw new InvalidInputException("Please provide the station id OR station code", "INVALID_STATION_FIELD");
        } else {
            stations = this.stationRepository.findByCodeOrId(stationCode, stationId);
            if (stations.size() == 0) {
                throw new InvalidInputException("No station was found with provided station id OR station code", "INVALID_STATION_FIELD");
            }
        }

        LocalTime arrivalTime = null;
        LocalTime departurTime = null;

        arrivalTime = scheduleDTO.getArrivalTime() == null ? null : LocalTime.parse(scheduleDTO.getArrivalTime());
        departurTime = scheduleDTO.getDepartureTime() == null ? null : LocalTime.parse(scheduleDTO.getDepartureTime());

        Schedule schedule = new Schedule(trains.get(0), stations.get(0), arrivalTime, departurTime, Days.ALL);

        return this.scheduleRepository.save(schedule);
    }


}
