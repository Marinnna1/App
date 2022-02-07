package ru.javaschool.entities;


import ru.javaschool.enums.Position;

import javax.persistence.*;


@Entity
@lombok.Data
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient")
    private String patient;

    public Data(String patient) {
        this.patient = patient;
    }

    public Data() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }


}
