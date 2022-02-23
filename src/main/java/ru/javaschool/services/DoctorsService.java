package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.DoctorDao;
import ru.javaschool.dto.DoctorDto;
import ru.javaschool.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorsService {

    @Autowired
    private DoctorDao doctorDao;

    public List<DoctorDto> getAll() {
        List<Doctor> doctors = doctorDao.findAll();
        List<DoctorDto> doctorDtos = new ArrayList<>();
        DoctorDto currentDoctorDto = new DoctorDto();
        for(int i = 0; i < doctors.size(); i++) {
            currentDoctorDto.setId(i);
            currentDoctorDto.setName(doctors.get(i).getUser().getName());
            doctorDtos.add(currentDoctorDto);
            currentDoctorDto = new DoctorDto();
        }
        return doctorDtos;
    }
}
