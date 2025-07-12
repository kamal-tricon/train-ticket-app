package com.example.demo.station.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.city.entities.City;
import com.example.demo.city.repositories.CityRepository;
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

        if(this.stationRepository.findByCode(stationCode).isPresent()) {
            throw new IllegalArgumentException("Station with code '" + stationCode + "' already exists");
        }

        for (char ch: stationCode.toCharArray()) {
            if (!Character.isLetter(ch)) {
                throw new IllegalArgumentException("All characters of station code must be alphabets only");
            }
        }

        if (station.getCityId() == 0) {
            throw new IllegalArgumentException("'cityId' is mandatory");
        }

        City city = this.cityRepository.findById(station.getCityId()).orElse(null);

        if (city == null) {
            throw new IllegalArgumentException("'cityId' field is invalid");
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
