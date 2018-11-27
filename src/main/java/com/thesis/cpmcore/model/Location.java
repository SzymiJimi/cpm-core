package com.thesis.cpmcore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Location {
    private Integer idLocation;
    private String name;
    private String address;

    public Location() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
