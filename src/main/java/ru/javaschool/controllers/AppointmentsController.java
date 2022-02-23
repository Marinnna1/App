package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("add")
    public void addAppointment(@RequestBody AppointmentDto appointmentDto) {
        appointmentsService.addAppointment(appointmentDto);
    }


    @PostMapping("delete")
    public void deleteAppointment(@RequestBody AppointmentDto appointmentDto) {
        System.out.println("delete " + appointmentDto.getId());
        appointmentsService.deleteAppointment(appointmentDto.getId());
    }
}
