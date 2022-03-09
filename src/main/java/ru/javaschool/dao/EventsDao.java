package ru.javaschool.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.javaschool.configurations.HibernateSessionFactoryUtil;
import ru.javaschool.entities.Event;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Repository
public class EventsDao {

    private static Logger LOGGER = Logger.getLogger(EventsDao.class.getName());


    public List<Event> findAll(Integer pageNumber, String status) {
        int pageSize = 3;
        int currentPage = pageNumber;
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("From Event Where status = \'" + status + "\'");
            query.setFirstResult(((currentPage - 1) * pageSize));
            query.setMaxResults(pageSize);
            List<Event> events = query.getResultList();
            return events;
        }
        catch (Exception e) {
            LOGGER.warn("Don't find appointments in database");
            return Collections.emptyList();
        }
    }

    //public Optional<Long> getEventsCount() {
    public Long getEventsCount(String status) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Long recordsCount = (Long) session
                    .createQuery("SELECT COUNT(*) FROM Event WHERE status=\'" + status + "\'")
                    .getSingleResult();
            return recordsCount;
      //      return Optional.ofNullable(recordsCount);
        }
        catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Can't define pagesCount");
            return null;
       //     return Optional.empty();
        }
    }

    public void changeStatus(int id, String oldStatus, String status, int pageNumber, String reason) {
        Event event = findAll(pageNumber, oldStatus).get(id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        event.setStatus(status);
        event.setReason(reason);
        session.beginTransaction();
        session.merge(event);
        session.getTransaction().commit();
    }
}
