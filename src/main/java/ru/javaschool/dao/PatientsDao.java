package ru.javaschool.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.dto.PatientDto;
import ru.javaschool.entities.Doctor;
import ru.javaschool.entities.Event;
import ru.javaschool.entities.Patient;

import java.util.List;

@Repository
public class PatientsDao {

    @Autowired
    private DoctorDao doctorDao;

    private static Logger LOGGER = Logger.getLogger(PatientsDao.class.getName());


    public List<Patient> findAll() {
        try {
            List<Patient> patients = (List<Patient>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Patient").list();
            return patients;
        }
        catch (NullPointerException e) {
            LOGGER.warn("Don't find patients in database");
            return null;
        }
    }

    public Patient findPatientByName(String name) {
        List<Patient> patients = ((List<Patient>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From Patient where name = \'" + name + "\'").list());
        if(!patients.isEmpty()) {
            return patients.get(0);
        }
        return null;
    }

    public void addPatient(String name, String diagnosis, int insurance, String status, String doctorName) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setDiagnosis(diagnosis);
        patient.setInsurance(insurance);
        patient.setStatus(status);
        patient.setDoctors(doctorDao.findDoctorByName(doctorName));

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(patient);
        session.getTransaction().commit();
    }


    public PatientDto deletePatient(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Patient> patients = ((List<Patient>) session.createQuery("From Patient where name = \'" + name + "\'").list());
        if(!patients.isEmpty()) {
            session.beginTransaction();
            session.delete(patients.get(0));
            session.getTransaction().commit();
            return new PatientDto("The patient was successfully removed");
        }
        return new PatientDto("Don't find patient");
    }

}
