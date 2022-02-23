package ru.javaschool.dao;

import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Doctor;

import java.util.List;


@Repository
public class DoctorDao {

    public List<Doctor> findAll() {
        try {
            List<Doctor> doctors = (List<Doctor>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Doctor").list();
            return doctors;
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public List<Doctor> findDoctorByName(String name) {
        System.out.println(name + " - doctor");
        List<Doctor> doctor = ((List<Doctor>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From Doctor where user.name = \'" + name + "\'").list());
        return doctor;
    }



}
