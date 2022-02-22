package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.dto.EventDto;
import ru.javaschool.services.EventsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("events/")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("all")
    public List<EventDto> getData() {
        return eventsService.getAll();
    }

}
