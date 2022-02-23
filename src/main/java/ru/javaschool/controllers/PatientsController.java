package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.dto.EventDto;
import ru.javaschool.dto.PatientDto;
import ru.javaschool.services.EventsService;
import ru.javaschool.services.PatientsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patients/")
public class PatientsController {

    @Autowired
    private PatientsService patientsService;

    @GetMapping("all")
    public List<PatientDto> getData() {
        return patientsService.getAll();
    }


    @PostMapping("add")
    public void addPatient(@RequestBody PatientDto patientDto) {
        patientsService.addPatient(patientDto);
    }


    @PostMapping("delete")
    public void deletePatient(@RequestBody PatientDto patientDto) {
        System.out.println("delete patient");
        patientsService.deletePatient(patientDto);
    }

}
