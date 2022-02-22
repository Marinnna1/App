package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.EventsDao;

import ru.javaschool.dto.EventDto;
import ru.javaschool.entities.Event;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsService {

    @Autowired
    private EventsDao eventsDao;


    public List<EventDto> getAll() {
        List<Event> events = eventsDao.findAll();
        List<EventDto> eventDtos = new ArrayList<>();
        EventDto currentEventDto = new EventDto();
        for(int i = 0; i < events.size(); i++) {
            currentEventDto.setId(i);
            currentEventDto.setPatientName(events.get(i).getPatient().getName());
            currentEventDto.setDateTime(events.get(i).getDate());
            currentEventDto.setStatus(events.get(i).getStatus());
            currentEventDto.setTreatmentType(events.get(i).getTreatment().getType());
            eventDtos.add(currentEventDto);
            currentEventDto = new EventDto();
        }
        return eventDtos;
    }
}
