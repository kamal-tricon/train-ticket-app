package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Station;
import com.example.demo.repositories.StationRepository;

@Service
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station createStation(Station station) {
        String stationCode = station.getCode();
        if(this.stationRepository.findByCode(stationCode).isPresent()) {
            throw new IllegalArgumentException("Station with code '" + stationCode + "' already exists");
        }

        for (char ch: stationCode.toCharArray()) {
            if (!Character.isLetter(ch)) {
                throw new IllegalArgumentException("All characters of station code must be alphabets only");
            }
        }
        station.setCode(stationCode.toUpperCase());
        return this.stationRepository.save(station);
    }

    public List<Station> getAll() {
        return this.stationRepository.findAll();
    }
}
