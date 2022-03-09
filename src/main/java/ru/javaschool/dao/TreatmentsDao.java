package ru.javaschool.dao;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;

import ru.javaschool.entities.Treatment;

import java.util.List;

@Repository
public class TreatmentsDao {

    private static Logger LOGGER = Logger.getLogger(TreatmentsDao.class.getName());


    public List<Treatment> findAll() {
        try {
            List<Treatment> treatments = (List<Treatment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Treatment").list();
            return treatments;
        }
        catch (NullPointerException e) {
            LOGGER.warn("Don't find treatments in database");
            return null;
        }
    }


    public Treatment findTreatmentByName(String name) {
        Treatment treatment = ((List<Treatment>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From Treatment where name = \'" + name + "\'").list()).get(0);
        return treatment;
    }
}
