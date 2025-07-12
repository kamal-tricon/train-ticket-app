package com.example.demo.city.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.city.dtos.CityDTO;
import com.example.demo.city.entities.City;
import com.example.demo.city.services.CityService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("")
    public ResponseEntity<CityDTO> create(@Valid @RequestBody CityDTO cityDTO) {
        City city = this.cityService.create(cityDTO);
        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setWasCreated(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<CityDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.cityService.getAll());
    }
    
    
}
