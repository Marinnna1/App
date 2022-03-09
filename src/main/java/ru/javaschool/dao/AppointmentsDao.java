package ru.javaschool.dao;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Appointment;

import java.util.Date;
import java.util.List;

@Repository
public class AppointmentsDao {

    @Autowired
    private PatientsDao patientsDao;

    @Autowired
    private TreatmentsDao treatmentsDao;

    private static Logger LOGGER = Logger.getLogger(AppointmentsDao.class.getName());

    public List<Appointment> findAll(Integer pageNumber) {
        int pageSize = 3;
        int currentPage = pageNumber;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("From Appointment");
            query.setFirstResult(((currentPage - 1) * pageSize));
            query.setMaxResults(pageSize);
            List<Appointment> appointments = query.getResultList();
            for(Appointment appointment : appointments) {
                System.out.println(appointment.getDose());
            }
            return appointments;
        }
        catch (Exception e) {
            LOGGER.warn("Don't find appointments in database");
            return null;
        }
    }

    public Long getAppointmentsCount() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Long recordsCount = (Long) session.createQuery("SELECT COUNT(*) FROM Appointment").getSingleResult();
            return recordsCount;
        }
        catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Can't define pagesCount");
            return null;
        }

    }


    public void addAppointment(String patientName, Double dose, Date startDate, Date endDate, String timePattern, String treatmentName) {
        Appointment appointment = new Appointment();

        appointment.setPatient(patientsDao.findPatientByName(patientName));
        appointment.setTreatment(treatmentsDao.findTreatmentByName(treatmentName));
        appointment.setStartDate(startDate);
        appointment.setEndDate(endDate);
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


    public void editTimePattern(int id, String timePattern, int pageNumber) {
        Appointment appointment = findAll(pageNumber).get(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        appointment.setTimePattern(timePattern);
        session.beginTransaction();
        session.merge(appointment);
        session.getTransaction().commit();
        session.close();
    }


    public void editDose(int id, double dose, int pageNumber) {
        Appointment appointment = findAll(pageNumber).get(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        appointment.setDose(dose);
        session.beginTransaction();
        session.merge(appointment);
        session.getTransaction().commit();
        session.close();
    }

    public void editPeriod(int id, Date startDate, Date endDate, int pageNumber) {
        Appointment appointment = findAll(pageNumber).get(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        appointment.setStartDate(startDate);
        appointment.setEndDate(endDate);
        session.beginTransaction();
        session.merge(appointment);
        session.getTransaction().commit();
        session.close();
    }

}
