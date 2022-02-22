package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.PatientsDao;
import ru.javaschool.dao.TreatmentsDao;
import ru.javaschool.dto.PatientDto;
import ru.javaschool.dto.TreatmentDto;
import ru.javaschool.entities.Patient;
import ru.javaschool.entities.Treatment;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentsService {

    @Autowired
    private TreatmentsDao treatmentsDao;

    public List<TreatmentDto> getAll() {
        List<Treatment> treatments = treatmentsDao.findAll();
        List<TreatmentDto> treatmentDtos = new ArrayList<>();
        TreatmentDto currentTreatmentDto = new TreatmentDto();
        for(int i = 0; i < treatments.size(); i++) {
            currentTreatmentDto.setId(i);
            currentTreatmentDto.setName(treatments.get(i).getName());
            treatmentDtos.add(currentTreatmentDto);
            currentTreatmentDto = new TreatmentDto();
        }
        return treatmentDtos;
    }

}
