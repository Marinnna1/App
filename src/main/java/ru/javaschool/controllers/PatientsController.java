package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public PatientDto addPatient(@RequestBody PatientDto patientDto) {
        return patientsService.addPatient(patientDto);
    }


    @PostMapping("delete")
    public PatientDto deletePatient(@RequestBody PatientDto patientDto) {
        return patientsService.deletePatient(patientDto);
    }

}
