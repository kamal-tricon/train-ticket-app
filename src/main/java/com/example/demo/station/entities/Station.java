package com.example.demo.station.entities;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.city.entities.City;
import com.example.demo.train.entities.Schedule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules = new HashSet<>();

    public Station() {}

    public Station(String name, String code, City city) {
        this.name = name;
        this.code = code;
        this.city = city;
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

    public Station setCity(City city) {
        this.city = city;
        return this;
    }

    public City getCity() {
        return this.city;
    }

    @Override
  public String toString() {
    return String.format(
        "Station[id=%d, name='%s', code='%s']",
        id, name, code);
  }

}
