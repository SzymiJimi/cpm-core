package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;
    private String username;
    private String password;
    private String email;

    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne(optional = false)
    private Role idRole;

    @JoinColumn(name = "idPersonaldata", referencedColumnName = "idPersonaldata")
    @ManyToOne(optional = false)
    private Personaldata idPersonaldata;


    public User(){

    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public Personaldata getIdPersonaldata() {
        return idPersonaldata;
    }

    public void setIdPersonaldata(Personaldata idPersonaldata) {
        this.idPersonaldata = idPersonaldata;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(idUser, user.idUser) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, username, password, email);
    }
}
