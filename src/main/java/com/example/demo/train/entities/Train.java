package com.example.demo.train.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "trains")

public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String number;

    @Column
    private String name;

    @Column
    private int ac1Coaches;
    
    @Column
    private int ac2Coaches;

    @Column
    private int ac3Coaches;

    @Column
    private int sleeperCoaches;

    @Column
    private int gsCoaches;

    @Column
    private int ccCoaches;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules = new HashSet<>();

    public Train() {}

    public Train(String number, String name, int ac1Coaches, int ac2Coaches, int ac3Coaches,
            int sleeperCoaches, int gsCoaches, int ccCoaches) {
        this.number = number;
        this.name = name;
        this.ac1Coaches = ac1Coaches;
        this.ac2Coaches = ac2Coaches;
        this.ac3Coaches = ac3Coaches;
        this.sleeperCoaches = sleeperCoaches;
        this.gsCoaches = gsCoaches;
        this.ccCoaches = ccCoaches;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAc1Coaches() {
        return ac1Coaches;
    }

    public void setAc1Coaches(int ac1Coaches) {
        this.ac1Coaches = ac1Coaches;
    }

    public int getAc2Coaches() {
        return ac2Coaches;
    }

    public void setAc2Coaches(int ac2Coaches) {
        this.ac2Coaches = ac2Coaches;
    }

    public int getAc3Coaches() {
        return ac3Coaches;
    }

    public void setAc3Coaches(int ac3Coaches) {
        this.ac3Coaches = ac3Coaches;
    }

    public int getSleeperCoaches() {
        return sleeperCoaches;
    }

    public void setSleeperCoaches(int sleeperCoaches) {
        this.sleeperCoaches = sleeperCoaches;
    }

    public int getGsCoaches() {
        return gsCoaches;
    }

    public void setGsCoaches(int gsCoaches) {
        this.gsCoaches = gsCoaches;
    }

    public int getCcCoaches() {
        return ccCoaches;
    }

    public void setCcCoaches(int ccCoaches) {
        this.ccCoaches = ccCoaches;
    }


}
