package com.thesis.cpmcore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Personaldata {
    private Integer idPersonalData;
    private String name;
    private String surname;
    private Timestamp dateOfBirth;
    private String gender;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdPersonalData() {
        return idPersonalData;
    }

    public void setIdPersonalData(Integer idPersonalData) {
        this.idPersonalData = idPersonalData;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personaldata that = (Personaldata) o;
        return Objects.equals(idPersonalData, that.idPersonalData) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idPersonalData, name, surname, dateOfBirth, gender);
    }
}
