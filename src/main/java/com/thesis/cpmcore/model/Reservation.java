package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idReservation;
    @Column(name = "`from`")
    private Timestamp from;
    @Column(name = "`to`")
    private Timestamp to;
    private String reason;
    private String contact;
    private String type;

    @JoinColumn(name = "reserverUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User reserverUser;

    @JoinColumn(name = "itemId", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item itemId;

    public static String RESERVATION = "RESERVATION";
    public static String CHECK_OUT = "CHECK_OUT";

    public Reservation(){

    }

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

    public User getReserverUser() {
        return reserverUser;
    }

    public void setReserverUser(User reserverUser) {
        this.reserverUser = reserverUser;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
