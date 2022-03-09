package ru.javaschool.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Doctor;

import java.util.List;


@Repository
public class DoctorDao {

    private static Logger LOGGER = Logger.getLogger(DoctorDao.class.getName());

    public List<Doctor> findAll() {
        try {
            List<Doctor> doctors = (List<Doctor>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Doctor").list();
            return doctors;
        }
        catch (NullPointerException e) {
            LOGGER.warn("Don't find doctors in database");
            return null;
        }
    }

    public List<Doctor> findDoctorByName(String name) {
        List<Doctor> doctor = ((List<Doctor>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From Doctor where user.name = \'" + name + "\'").list());
        return doctor;
    }



}
