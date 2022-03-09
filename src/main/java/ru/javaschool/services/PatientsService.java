package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.DoctorDao;
import ru.javaschool.dao.PatientsDao;
import ru.javaschool.dto.PatientDto;
import ru.javaschool.entities.Patient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientsService {

    @Autowired
    private PatientsDao patientsDao;

    @Autowired
    private DoctorDao doctorDao;

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


    public PatientDto addPatient(PatientDto patientDto) {
        if(patientsDao.findPatientByName(patientDto.getName())  == null) {
            if(!patientDto.getDiagnosis().trim().equals("")) {
                if(patientDto.getInsurance() > 0 && patientDto.getInsurance() < 1000) {
                    if(patientDto.getStatus().equals("Treating") || patientDto.getStatus().equals("Health")) {
                        if(!doctorDao.findDoctorByName(patientDto.getDoctorName()).isEmpty()) {
                            patientsDao.addPatient(patientDto.getName(), patientDto.getDiagnosis(),
                                    patientDto.getInsurance(), patientDto.getStatus(), patientDto.getDoctorName());
                            return new PatientDto("Patient successfully added");
                        }
                        return new PatientDto("Invalid doctor name");
                    }
                    return new PatientDto("Invalid patient status");
                }
                return new PatientDto("Invalid insurance");
            }
            return new PatientDto("Invalid diagnosis");
        }
        return new PatientDto("Invalid patient name");
    }


    public PatientDto deletePatient(PatientDto patientDto) {
        return patientsDao.deletePatient(patientDto.getName());
    }
}
