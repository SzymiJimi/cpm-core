package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Role {
    private Integer idRole;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(idRole, role.idRole) &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRole, name);
    }
}
