package com.example.demo.station.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.station.entities.Station;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByCode(String code);
    List<Station> findByCity_Id(long id);
    List<Station> findByCodeOrId(String code, long id);
}
