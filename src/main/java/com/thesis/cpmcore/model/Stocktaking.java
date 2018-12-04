package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
public class Stocktaking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idStocktaking;
    private Timestamp start;
    private Timestamp finish;
    private String type;
    private String method;

    @JoinColumn(name = "location", referencedColumnName = "idLocation")
    @ManyToOne(optional = false)
    private Location location;

    @JoinColumn(name = "manager", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User manager;

    public Stocktaking() {
    }

    public Integer getIdSocktaking() {
        return idStocktaking;
    }

    public void setIdSocktaking(Integer idSocktaking) {
        this.idStocktaking = idSocktaking;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getFinish() {
        return finish;
    }

    public void setFinish(Timestamp finish) {
        this.finish = finish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stocktaking that = (Stocktaking) o;
        return Objects.equals(idStocktaking, that.idStocktaking);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStocktaking);
    }

    @Override
    public String toString() {
        return "Stocktaking{" +
                "idSocktaking=" + idStocktaking +
                ", start=" + start +
                ", finish=" + finish +
                ", type='" + type + '\'' +
                ", method='" + method + '\'' +
                ", location=" + location +
                ", manager=" + manager +
                '}';
    }
}
