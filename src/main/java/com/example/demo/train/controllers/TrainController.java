package com.example.demo.train.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.train.dtos.TrainDTO;
import com.example.demo.train.entities.Train;
import com.example.demo.train.services.TrainService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("")
    public ResponseEntity<TrainDTO> save(@Valid @RequestBody TrainDTO trainDTO) {
        
        Train savedTrain = this.trainService.saveTrain(trainDTO);

        TrainDTO dto = new TrainDTO();
        dto.setId(savedTrain.getId());
        dto.setTrainNumber(savedTrain.getNumber());
        dto.setTrainName(savedTrain.getName());
        dto.setCoachCountAC1(savedTrain.getAc1Coaches());
        dto.setCoachCountAC2(savedTrain.getAc2Coaches());
        dto.setCoachCountAC3(savedTrain.getAc3Coaches());
        dto.setCoachCountSleeper(savedTrain.getSleeperCoaches());
        dto.setCoachCountCC(savedTrain.getCcCoaches());
        dto.setCoachCountGS(savedTrain.getGsCoaches());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
