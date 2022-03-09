package ru.javaschool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.dto.AppointmentDto;
import ru.javaschool.services.AppointmentsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("appointments/")
public class AppointmentsController {

    @Autowired
    private AppointmentsService appointmentsService;



    @PostMapping("all")
    public List<AppointmentDto> getData(@RequestBody AppointmentDto appointmentDto) {
        System.out.println("wrote request");
        return appointmentsService.getAll(appointmentDto.getPageNumber());
    }

    @GetMapping("appointments_count")
    public Long getAppointmentsCount() {
        return appointmentsService.getAppointmentsCount();
    }

    @PostMapping("add")
    public AppointmentDto addAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentsService.addAppointment(appointmentDto);
    }


    @PostMapping("delete")
    public void deleteAppointment(@RequestBody AppointmentDto appointmentDto) {
        appointmentsService.deleteAppointment(appointmentDto.getId());
    }

    @PostMapping("edit_time_pattern")
    public AppointmentDto editTimePattern(@RequestBody AppointmentDto appointmentDto) {
        return appointmentsService.editTimePattern(appointmentDto);
    }

    @PostMapping("edit_dose")
    public AppointmentDto editDose(@RequestBody AppointmentDto appointmentDto) {
        return appointmentsService.editDose(appointmentDto);
    }

    @PostMapping("edit_period")
    public AppointmentDto editPeriod(@RequestBody AppointmentDto appointmentDto) {
        return appointmentsService.editPeriod(appointmentDto);
    }
}
