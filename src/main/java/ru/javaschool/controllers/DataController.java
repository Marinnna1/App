package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.entities.Data;
import ru.javaschool.services.DataService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("doctor/")
public class DataController {

    @Autowired
    private DataService dataService;


    @GetMapping("data")
    public List<Data> getData() {
        return dataService.getAllData();
    }
}
