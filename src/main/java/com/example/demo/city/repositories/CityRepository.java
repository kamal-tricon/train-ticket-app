package com.example.demo.city.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.city.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
