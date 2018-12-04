package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLocation;
    private String name;
    private String address;

    @JoinColumn(name = "unitHead", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User unitHead;

    public Location() {
    }


    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUnitHead() {
        return unitHead;
    }

    public void setUnitHead(User unitHead) {
        this.unitHead = unitHead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(idLocation, location.idLocation) &&
                Objects.equals(name, location.name) &&
                Objects.equals(address, location.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idLocation, name, address);
    }
}
