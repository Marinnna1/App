package ru.javaschool.dto;

public class PatientDto {

    private int id;
    private String name;
    private String diagnosis;
    private int insurance;
    private String status;
    private String doctorName;


    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
