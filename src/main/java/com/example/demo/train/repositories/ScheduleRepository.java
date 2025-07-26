package com.example.demo.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.train.entities.Schedule;

import java.util.List;
import java.time.LocalTime;



public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByTrain_Id(long id);
    List<Schedule> findAllByTrain_Number(String train_Number);
    boolean existsByArrivalTimeAndTrain_Id(LocalTime arrivalTime, long trainId);
    boolean existsByDepartureTimeAndTrain_Id(LocalTime departureTime, long trainId);

    @Query("SELECT ts1.train.name, ts1.station.code, ts1.departureTime, ts2.station.code, ts2.arrivalTime " +
           "FROM Schedule ts1 " +
           "JOIN Schedule ts2 ON ts1.train.id = ts2.train.id " +
           "WHERE ts1.station.id = :startStation " +
           "AND ts2.station.id = :endStation " +
           "AND ts1.stoppageNumber < ts2.stoppageNumber"
    )
    List<Object[]> findListSchedulesOfConnectingStations(
        @Param("startStation") long startStation,
        @Param("endStation") long endStation);


    // @Query(value = "SELECT t.number, t.name, s.name, s.code, ts1.departure_time, s2.name, s2.code, ts2.arrival_time " + // Use DB column names here
    //                "FROM train_schedule ts1 " + // Use actual table name
    //                "LEFT JOIN train_schedule ts2 ON ts1.train_id = ts2.train_id " + // Use actual table and column names
    //                "LEFT JOIN trains t ON t.id = ts1.train_id " + // Use actual table and column names
    //                "LEFT JOIN stations s ON s.id = ts1.station_id " + // Use actual table and column names
    //                "LEFT JOIN stations s2 ON s2.id = ts2.station_id " +
    //                "WHERE ts1.station_id = :startStation " + // Use actual column name
    //                "AND ts2.station_id = :endStation " +     // Use actual column name
    //                "AND ts1.stoppage_number < ts2.stoppage_number", // Use actual column name
    //        nativeQuery = true)
    // List<Object[]> findListSchedulesOfConnectingStations(
    // @Param("startStation") long startStation,
    // @Param("endStation") long endStation);
}
