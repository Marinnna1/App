package ru.javaschool.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.EventsDao;

import ru.javaschool.dto.EventDto;
import ru.javaschool.entities.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class EventsService {

    @Autowired
    private EventsDao eventsDao;


    public List<EventDto> getAll(Integer pageNumber, String status) {
        List<Event> events = eventsDao.findAll(pageNumber, status);
        List<EventDto> eventDtos = new ArrayList<>();
        EventDto currentEventDto = new EventDto();
      //  events.stream()
     //           .map(//maping v dto)
    //            .collect(Collectors.toList());
        for(int i = 0; i < events.size(); i++) {
            currentEventDto.setId(i);
         //   currentEventDto.setPatientName(events.get(i).getPatient().getName());
            currentEventDto.setDateTime(events.get(i).getDate());
            currentEventDto.setStatus(events.get(i).getStatus());
            currentEventDto.setTreatmentName(events.get(i).getTreatment().getName());
            currentEventDto.setTreatmentType(events.get(i).getTreatment().getType());
            eventDtos.add(currentEventDto);
            currentEventDto = new EventDto();
        }
        return eventDtos;
    }

    public Long getEventsCount(EventDto eventDto) {
        return eventsDao.getEventsCount(eventDto.getStatus());
    }

    public EventDto editStatus(EventDto eventDto) {
        String reason = null;
        if(eventDto.getStatus().equals("Cancelled")) {
            reason = eventDto.getReason();
        }
        if((eventDto.getStatus().equals("Cancelled") || eventDto.getStatus().equals("Done"))
                && eventDto.getOldStatus().equals("Planned")) {
            eventsDao.changeStatus(eventDto.getId(), eventDto.getOldStatus(), eventDto.getStatus(), eventDto.getPageNumber(), reason);
            return new EventDto("Status successfully changed");
        }
        return new EventDto("Something went wrong, can't change status");
    }
}


