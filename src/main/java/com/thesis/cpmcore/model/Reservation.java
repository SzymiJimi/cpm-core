package com.thesis.cpmcore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Reservation {
    private Integer idReservation;
    private Timestamp from;
    private Timestamp to;
    private String reason;
    private String contact;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }


    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }


    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(idReservation, that.idReservation) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idReservation, from, to, reason, contact);
    }
}
