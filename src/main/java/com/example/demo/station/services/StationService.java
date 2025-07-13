package com.example.demo.station.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.city.entities.City;
import com.example.demo.city.repositories.CityRepository;
import com.example.demo.common.controllers.InvalidInputException;
import com.example.demo.common.controllers.InvalidInputsException;
import com.example.demo.station.dtos.StationDTO;
import com.example.demo.station.entities.Station;
import com.example.demo.station.repositories.StationRepository;

@Service
public class StationService {

    private final StationRepository stationRepository;
    private final CityRepository cityRepository;

    @Autowired
    public StationService(StationRepository sRepository, CityRepository cRepository) {
        this.stationRepository = sRepository;
        this.cityRepository = cRepository;
    }

    public Station createStation(StationDTO station) {
        String stationCode = station.getCode();
        Map<String, String> errors = new HashMap<>();

        // Below commented throw new <Exception> are to emit the error on individual field
        // With Current setup, all the errors in the API request will be thown at the same time
        // Always free to change this behaviour

        if(this.stationRepository.findByCode(stationCode).isPresent()) {
            errors.put("code", "Station with code '" + stationCode + "' already exists");
            // throw new InvalidInputException("Station with code '" + stationCode + "' already exists", "INVALID_FIELDS" + "_STATION_CODE");
        }

        for (char ch: stationCode.toCharArray()) {
            if (!Character.isLetter(ch)) {
                errors.put("name", "All characters of station code must be alphabets only");
                // throw new IllegalArgumentException("All characters of station code must be alphabets only");
            }
        }

        City city = null;

        if (station.getCityId() == 0) {
            errors.put("cityId", "'cityId' field is mandatory");
            // throw new IllegalArgumentException("'cityId' is mandatory");
        } else {
            city = this.cityRepository.findById(station.getCityId()).orElse(null);
            if (city == null) {
                errors.put("cityId", "'cityId' field is invalid");
                // throw new IllegalArgumentException("'cityId' field is invalid");
            }
        }

        if (!errors.isEmpty()) {
            throw new InvalidInputsException(errors, "INVALID_FIELDS");
        }

        Station entity = new Station();
        entity.setName(station.getName());
        entity.setCode(stationCode.toUpperCase());
        entity.setCity(city);
        return this.stationRepository.save(entity);
    }

    public List<StationDTO> getAll() {
        List<Station> stations =  this.stationRepository.findAll();
        return stations.stream().map(station -> {
            StationDTO dto = this.convertToFullStationResponse(station);
            return dto;
        }).collect(Collectors.toList());
    }

    public StationDTO getById(long id) {
        Station station = this.stationRepository.findById(id).orElse(null);
        if (station == null) {
            throw new IllegalArgumentException("Station with id '" + id + "' was not found");
        }
        return this.convertToFullStationResponse(station);
    }

    public List<StationDTO> getByCity(long id) {
        List<Station> stations =  this.stationRepository.findByCity_Id(id);
        return stations.stream().map(station -> {
            StationDTO dto = this.convertToFullStationResponse(station);
            return dto;
        }).collect(Collectors.toList());
    }

    public StationDTO convertToFullStationResponse(Station station) {
        StationDTO dto = new StationDTO();
        dto.setId(station.getId());
        dto.setName(station.getName());
        dto.setCode(station.getCode());
        dto.setCityId(station.getCity().getId());
        dto.setCityName(station.getCity().getName());
        return dto;
    }
}
