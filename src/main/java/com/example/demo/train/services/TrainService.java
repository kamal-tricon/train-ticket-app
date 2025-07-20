package com.example.demo.train.services;

import org.springframework.stereotype.Service;

import com.example.demo.common.controllers.InvalidInputException;
import com.example.demo.train.dtos.TrainDTO;
import com.example.demo.train.entities.Train;
import com.example.demo.train.repositories.TrainRepository;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    public TrainService(TrainRepository tRepository) {
        this.trainRepository = tRepository;
    }

    public Train saveTrain(TrainDTO train) {

        String trainNumber = train.getTrainNumber();

        for (char ch: trainNumber.toCharArray()) {
            if (!Character.isDigit(ch)) {
                throw new InvalidInputException("Train number is invalid, it must contain only digits");
            }
        }

        Train entity = new Train(
            train.getTrainNumber(),
            train.getTrainName(),
            train.getCoachCountAC1(),
            train.getCoachCountAC2(),
            train.getCoachCountAC3(),
            train.getCoachCountSleeper(),
            train.getCoachCountGS(),
            train.getCoachCountCC()
        );

        return this.trainRepository.save(entity);
    }

}
