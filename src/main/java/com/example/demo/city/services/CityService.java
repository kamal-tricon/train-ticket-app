package com.example.demo.city.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.city.dtos.CityDTO;
import com.example.demo.city.entities.City;
import com.example.demo.city.repositories.CityRepository;

@Service
public class CityService {
    private final CityRepository cRepository;

    public CityService(CityRepository cityRepository) {
        this.cRepository = cityRepository;
    }

    public City create(CityDTO cityDTO) {
        City city = new City();
        city.setName(cityDTO.getName());
        this.cRepository.save(city);
        return city;
    }

    public List<CityDTO> getAll() {
        List<City> cities =  this.cRepository.findAll();
        List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
        cities.forEach(c -> {
            CityDTO dto = new CityDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setWasCreated(true);
            cityDTOs.add(dto);
        });

        return cityDTOs;
    }
}
