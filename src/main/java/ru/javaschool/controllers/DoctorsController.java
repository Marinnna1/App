package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.dto.DoctorDto;
import ru.javaschool.services.DoctorsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("doctors/")
public class DoctorsController {

    @Autowired
    private DoctorsService doctorsService;

    @GetMapping("all")
    public List<DoctorDto> getData() {
        return doctorsService.getAll();
    }
}
