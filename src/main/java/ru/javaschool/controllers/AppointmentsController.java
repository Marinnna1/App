package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.dto.AppointmentDto;
import ru.javaschool.entities.Appointment;
import ru.javaschool.entities.Data;
import ru.javaschool.services.AppointmentsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("appointments/")
public class AppointmentsController {

    @Autowired
    private AppointmentsService appointmentsService;

    @GetMapping("all")
    public List<AppointmentDto> getData() {
        return appointmentsService.getAll();
    }
}
