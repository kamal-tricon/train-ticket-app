package com.example.demo.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.train.entities.Schedule;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTrain_Id(long id);
}
