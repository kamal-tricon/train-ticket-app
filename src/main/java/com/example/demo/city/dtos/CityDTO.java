package com.example.demo.city.dtos;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.station.entities.Station;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CityDTO {

    private long id;

    @NotBlank(message = "City name cannot be empty")
    @Size(max = 100)
    private String name;


    private Set<Station> stations = new HashSet<>();

    private Boolean wasCreated = false;

    public CityDTO() {}

    public CityDTO(String name, Set<Station> stations) {
        this.name = name;
        this.stations = stations;
    }

    public CityDTO setId(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return this.id;
    }

    public CityDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public CityDTO setWasCreated(Boolean wasCreated) {
        this.wasCreated = wasCreated;
        return this;
    }

    public Boolean getWasCreated() {
        return this.wasCreated;
    }
}
