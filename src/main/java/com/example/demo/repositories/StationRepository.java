package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Station;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByCode(String code);
}
