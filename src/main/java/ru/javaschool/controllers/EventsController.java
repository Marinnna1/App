package ru.javaschool.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.dto.EventDto;
import ru.javaschool.services.EventsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("events/")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @PostMapping("all")
    public List<EventDto> getData(@RequestBody EventDto eventDto) {
        return eventsService.getAll(eventDto.getPageNumber(), eventDto.getStatus());
    }

    @PostMapping("events_count")
    public Long getEventsCount(@RequestBody EventDto eventDto) {

        return eventsService.getEventsCount(eventDto);
    }

    @PostMapping("edit_status")
    public EventDto editStatus(@RequestBody EventDto eventDto) {
        return eventsService.editStatus(eventDto);
    }

}
