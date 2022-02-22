package ru.javaschool.dao;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Appointment;
import ru.javaschool.entities.Data;

import java.util.List;

@Repository
public class AppointmentsDao {

    public List<Appointment> findAll() {
        try {
            List<Appointment> appointments = (List<Appointment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Appointment").list();
            return appointments;
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}
