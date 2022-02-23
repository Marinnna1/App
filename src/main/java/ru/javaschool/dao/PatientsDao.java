package ru.javaschool.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Doctor;
import ru.javaschool.entities.Event;
import ru.javaschool.entities.Patient;

import java.util.List;

@Repository
public class PatientsDao {

    @Autowired
    private DoctorDao doctorDao;

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


    public void deletePatient(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Patient patient = ((List<Patient>) session.createQuery("From Patient where name = \'" + name + "\'").list()).get(0);
        System.out.println("patient id " + patient.getId());
        session.beginTransaction();
        session.delete(patient);
        session.getTransaction().commit();
    }

}
