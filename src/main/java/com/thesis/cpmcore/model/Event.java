package com.thesis.cpmcore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Event {
    private Integer idEvent;
    private String type;
    private Timestamp eventDate;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(idEvent, event.idEvent) &&
                Objects.equals(type, event.type) &&
                Objects.equals(eventDate, event.eventDate) &&
                Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idEvent, type, eventDate, description);
    }
}
