package ru.javaschool.dao;


import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;

import ru.javaschool.entities.Treatment;

import java.util.List;

@Repository
public class TreatmentsDao {

    public List<Treatment> findAll() {
        try {
            List<Treatment> treatments = (List<Treatment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Treatment").list();
            return treatments;
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}