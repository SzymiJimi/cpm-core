package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Data
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idRequest;
    private String type;
    private Timestamp eventDate;
    private Timestamp repairDate;
    private String description;
    private String status;

    @JoinColumn(name = "itemId", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item itemId;

    @JoinColumn(name = "declarant", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User declarant;

    public Report() {
    }

    public static String DAMAGED = "DAMAGED";
    public static String MAINTENANCE = "MAINTENANCE";
    public static String CREATED = "CREATED";
    public static String REPAIRING = "REPAIRING";
    public static String FINISHED = "FINISHED";

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

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public Timestamp getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Timestamp repairDate) {
        this.repairDate = repairDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public User getDeclarant() {
        return declarant;
    }

    public void setDeclarant(User declarant) {
        this.declarant = declarant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(idRequest, report.idRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequest);
    }

    @Override
    public String toString() {
        return "Report{" +
                "idRequest=" + idRequest +
                ", type='" + type + '\'' +
                ", eventDate=" + eventDate +
                ", repairDate=" + repairDate +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
