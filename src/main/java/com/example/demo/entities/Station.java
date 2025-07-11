package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Station name cannot be empty")
    private String name;

    @Column(name = "code", nullable = false)
    @NotBlank(message = "Station Code cannot be empty")
    @Size(min = 3, max = 5, message = "Station code must be 3-5 characters long")
    private String code;

    protected Station() {}

    public Station(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public long getId() {
        return this.id;
    }

    public Station setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Station setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    @Override
  public String toString() {
    return String.format(
        "Station[id=%d, name='%s', code='%s']",
        id, name, code);
  }

}
