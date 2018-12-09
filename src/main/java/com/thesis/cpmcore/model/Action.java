package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Data
@Entity
public class Action {
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

    @JoinColumn(name = "location", referencedColumnName = "idLocation")
    @ManyToOne(optional = false)
    private Location location;

    public static String RESERVATION = "RESERVATION";
    public static String CHECK_OUT = "CHECK_OUT";

    public Action(){

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Action{" +
                "idReservation=" + idReservation +
                ", from=" + from +
                ", to=" + to +
                ", reason='" + reason + '\'' +
                ", contact='" + contact + '\'' +
                ", type='" + type + '\'' +
                ", reserverUser=" + reserverUser +
                ", itemId=" + itemId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(idReservation, action.idReservation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation);
    }
}
