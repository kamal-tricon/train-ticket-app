package com.example.demo.station.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class StationDTO {
    private long id;

    @NotBlank(message = "Station name cannot be empty")
    private String name;

    @NotBlank(message = "Station Code cannot be empty")
    @Size(min = 3, max = 5, message = "Station code must be 3-5 characters long")
    private String code;

    @NotNull(message = "City cannot be empty")
    private long cityId;

    private String cityName;

    public StationDTO() {}

    public StationDTO(String name, String code, long cityId, String cityName) {
        this.name = name;
        this.code = code;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public StationDTO setId(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return this.id;
    }

    public StationDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public StationDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public StationDTO setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }

    public long getCityId() {
        return this.cityId;
    }

    public StationDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getCityName() {
        return this.cityName;
    }
}
