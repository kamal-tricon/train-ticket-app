package com.example.demo.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.train.entities.Train;
import java.util.List;


@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findByName(String name);
    List<Train> findByNumber(String number);
    List<Train> findByNumberOrId(String number, long id);
    boolean existsByNumber(String number);
}
