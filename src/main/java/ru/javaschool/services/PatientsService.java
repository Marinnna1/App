package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.PatientsDao;
import ru.javaschool.dto.PatientDto;
import ru.javaschool.entities.Patient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientsService {

    @Autowired
    private PatientsDao patientsDao;

    public List<PatientDto> getAll() {
        List<Patient> patients = patientsDao.findAll();
        List<PatientDto> patientDtos = new ArrayList<>();
        PatientDto currentPatientDto = new PatientDto();
        for(int i = 0; i < patients.size(); i++) {
            currentPatientDto.setId(i);
            currentPatientDto.setName(patients.get(i).getName());
            patientDtos.add(currentPatientDto);
            currentPatientDto = new PatientDto();
        }
        return patientDtos;
    }


    public void addPatient(PatientDto patientDto) {
        patientsDao.addPatient(patientDto.getName(), patientDto.getDiagnosis(), patientDto.getInsurance(),
                patientDto.getStatus(), patientDto.getDoctorName());
    }


    public void deletePatient(PatientDto patientDto) {
        patientsDao.deletePatient(patientDto.getName());
    }
}
