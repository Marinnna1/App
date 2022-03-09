package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.controllers.AppointmentsController;
import ru.javaschool.dao.AppointmentsDao;
import ru.javaschool.dao.PatientsDao;
import ru.javaschool.dao.TreatmentsDao;
import ru.javaschool.dto.AppointmentDto;
import ru.javaschool.entities.Appointment;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentsService {

    @Autowired
    AppointmentsDao appointmentsDao;

    @Autowired
    PatientsDao patientsDao;

    @Autowired
    TreatmentsDao treatmentsDao;

    public List<AppointmentDto> getAll(Integer pageNumber) {
        List<Appointment> appointments = appointmentsDao.findAll(pageNumber);
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        AppointmentDto currentAppointmentDto = new AppointmentDto();
        for(int i = 0; i < appointments.size(); i++) {
            currentAppointmentDto.setId(i);
            currentAppointmentDto.setPatientName(appointments.get(i).getPatient().getName());
            currentAppointmentDto.setTreatmentName(appointments.get(i).getTreatment().getName());
            currentAppointmentDto.setTreatmentType(appointments.get(i).getTreatment().getType());
            currentAppointmentDto.setTimePattern(appointments.get(i).getTimePattern());
            currentAppointmentDto.setStartDate(appointments.get(i).getStartDate());
            currentAppointmentDto.setEndDate(appointments.get(i).getEndDate());
            currentAppointmentDto.setDose(String.valueOf(appointments.get(i).getDose()));
            appointmentDtos.add(currentAppointmentDto);
            currentAppointmentDto = new AppointmentDto();
        }
        return appointmentDtos;
    }

    public Long getAppointmentsCount() {
        return appointmentsDao.getAppointmentsCount();
    }

    public AppointmentDto editTimePattern(AppointmentDto appointmentDto) {
        if(appointmentDto.getTimePattern().equals("Once a day") || appointmentDto.getTimePattern().equals("Twice a day")
        || appointmentDto.getTimePattern().equals("Once a week")) {
            appointmentsDao.editTimePattern(appointmentDto.getId(), appointmentDto.getTimePattern(), appointmentDto.getPageNumber());
            return new AppointmentDto("Time pattern has been successfully changed");
        }
        return new AppointmentDto("Invalid time pattern");
    }


    public AppointmentDto editDose(AppointmentDto appointmentDto) {
        double dose;
        try{
            dose = Double.parseDouble(appointmentDto.getDose());
        } catch (Exception e) {
            return new AppointmentDto("Invalid dose");
        }
        if(appointmentsDao.findAll(appointmentDto.getPageNumber()).get(appointmentDto.getId()).getTreatment().getType().equals("procedure")) {
            return new AppointmentDto("You can't initialize dose in procedure");
        }
        if(dose < 1 || dose > 1000) {
            return new AppointmentDto("Dose must be a positive number");
        }
        appointmentsDao.editDose(appointmentDto.getId(), dose, appointmentDto.getPageNumber());
        return new AppointmentDto("Dose has been successfully changed");
    }


    public AppointmentDto editPeriod(AppointmentDto appointmentDto) {
        if(appointmentDto.getStartDate() == null || appointmentDto.getEndDate() == null) {
            return new AppointmentDto("Invalid input");
        }
        appointmentsDao.editPeriod(appointmentDto.getId(), appointmentDto.getStartDate(),
                appointmentDto.getEndDate(), appointmentDto.getPageNumber());
        return new AppointmentDto("Period has been successfully changed");
    }





    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        if(patientsDao.findPatientByName(appointmentDto.getPatientName()) == null) {
            return new AppointmentDto("Invalid patient name");
        }
        Double dose;
        try {
            dose = Double.parseDouble(appointmentDto.getDose());
        } catch(Exception e) {
            return new AppointmentDto("Invalid dose");
        }
        if(dose < 1 || dose > 1000) {
            new AppointmentDto("Dose must be positive and less than 1000");
        }
        if(treatmentsDao.findTreatmentByName(appointmentDto.getTreatmentName()).getType().equals("procedure")) {
            dose = null;
        }
        appointmentsDao.addAppointment(appointmentDto.getPatientName(), dose,
                appointmentDto.getStartDate(), appointmentDto.getEndDate(), appointmentDto.getTimePattern(), appointmentDto.getTreatmentName());
        return new AppointmentDto("Add new appointment");
    }

    public void deleteAppointment(Integer appointmentNumber) {
        if (appointmentNumber != null) {
            if(appointmentNumber >= 0)
            appointmentsDao.deleteAppointment(appointmentNumber);
        }
    }
}
