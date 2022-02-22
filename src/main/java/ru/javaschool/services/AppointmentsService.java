package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.controllers.AppointmentsController;
import ru.javaschool.dao.AppointmentsDao;
import ru.javaschool.dto.AppointmentDto;
import ru.javaschool.entities.Appointment;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentsService {

    @Autowired
    AppointmentsDao appointmentsDao;

    public List<AppointmentDto> getAll() {
        List<Appointment> appointments = appointmentsDao.findAll();
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        AppointmentDto currentAppointmentDto = new AppointmentDto();
        for(int i = 0; i < appointments.size(); i++) {
            currentAppointmentDto.setId(i);
            currentAppointmentDto.setPatientName(appointments.get(i).getPatient().getName());
            currentAppointmentDto.setTreatmentType(appointments.get(i).getTreatment().getType());
            currentAppointmentDto.setTimePattern(appointments.get(i).getTimePattern());
            currentAppointmentDto.setPeriod(appointments.get(i).getPeriod());
            currentAppointmentDto.setDose(appointments.get(i).getDose());
            appointmentDtos.add(currentAppointmentDto);
            currentAppointmentDto = new AppointmentDto();
        }
        return appointmentDtos;
    }
}
