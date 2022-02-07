package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javaschool.dao.DataDao;
import ru.javaschool.entities.Data;

import java.util.List;

@Component
public class DataService {

    @Autowired
    private DataDao dataDao;

    public List<Data> getAllData() {
        return dataDao.findAll();
    }
}
