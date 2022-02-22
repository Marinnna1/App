package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.dto.PatientDto;
import ru.javaschool.dto.TreatmentDto;
import ru.javaschool.services.TreatmentsService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("treatments/")
public class TreatmentsController {

    @Autowired
    private TreatmentsService treatmentsService;

    @GetMapping("all")
    public List<TreatmentDto> getData() {
        return treatmentsService.getAll();
    }

}
