package ru.javaschool.dao;

import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Event;
import ru.javaschool.entities.Patient;

import java.util.List;

@Repository
public class PatientsDao {

    public List<Patient> findAll() {
        try {
            List<Patient> patients = (List<Patient>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Patient").list();
            return patients;
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public Patient findPatientByName(String name) {
        Patient patient = ((List<Patient>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From Patient where name = \'" + name + "\'").list()).get(0);
        return patient;
    }

}
