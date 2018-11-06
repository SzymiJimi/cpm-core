package com.thesis.cpmcore.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Request {
    private Integer idRequest;
    private String type;
    private String quantity;
    private Timestamp fromDate;
    private Timestamp toDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Integer idRequest) {
        this.idRequest = idRequest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(idRequest, request.idRequest) &&
                Objects.equals(type, request.type) &&
                Objects.equals(quantity, request.quantity) &&
                Objects.equals(fromDate, request.fromDate) &&
                Objects.equals(toDate, request.toDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRequest, type, quantity, fromDate, toDate);
    }
}
