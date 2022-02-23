package ru.javaschool.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Appointment;
import ru.javaschool.entities.Data;

import java.util.List;

@Repository
public class AppointmentsDao {

    @Autowired
    private PatientsDao patientsDao;

    @Autowired
    private TreatmentsDao treatmentsDao;

    public List<Appointment> findAll() {
        try {
            List<Appointment> appointments = (List<Appointment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Appointment").list();
            return appointments;
        }
        catch (Exception e) {
            return null;
        }
    }


    public void addAppointment(String patientName, double dose, String period, String timePattern, String treatmentName) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patientsDao.findPatientByName(patientName));
        appointment.setTreatment(treatmentsDao.findTreatmentByName(treatmentName));
        appointment.setPeriod(period);
        appointment.setTimePattern(timePattern);
        appointment.setDose(dose);

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(appointment);
        session.getTransaction().commit();

    }


    public void deleteAppointment(Integer appointmentNumber) {
        Appointment appointment = ((List<Appointment>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Appointment").list()).get(appointmentNumber);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(appointment);
        session.getTransaction().commit();
    }


}
