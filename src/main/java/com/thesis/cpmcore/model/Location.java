package com.thesis.cpmcore.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Location {
    private Integer idLocation;
    private String name;
    private BigDecimal coordinateX;
    private BigDecimal coordinateY;
    private String address;

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

    public BigDecimal getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(BigDecimal coordinateX) {
        this.coordinateX = coordinateX;
    }

    public BigDecimal getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(BigDecimal coordinateY) {
        this.coordinateY = coordinateY;
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
                Objects.equals(coordinateX, location.coordinateX) &&
                Objects.equals(coordinateY, location.coordinateY) &&
                Objects.equals(address, location.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idLocation, name, coordinateX, coordinateY, address);
    }
}
