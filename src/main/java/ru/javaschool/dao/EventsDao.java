package ru.javaschool.dao;

import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Event;

import java.util.List;


@Repository
public class EventsDao {

    public List<Event> findAll() {
        try {
            List<Event> events = (List<Event>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Event").list();
            return events;
        }
        catch (NullPointerException e) {
            return null;
        }
    }
}
