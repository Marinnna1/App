package ru.javaschool.dao;

import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Data;

import java.util.List;

@Repository

public class DataDao {

    public List<Data> findAll() {
        List<Data> data = (List<Data>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Data").list();
        return data;
    }
}
