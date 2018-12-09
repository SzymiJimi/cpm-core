package com.thesis.cpmcore.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Data
@Entity
public class Report implements Comparable<Report> {
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

    @JoinColumn(name = "serviceman", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User serviceman;

    public Report() {
    }

    public static String DAMAGED = "DAMAGED";
    public static String MAINTENANCE = "MAINTENANCE";
    public static String CREATED = "CREATED";
    public static String REPAIRING = "REPAIRING";
    public static String FINISHED = "FINISHED";
    public static String DECOMMISSIONING = "DECOMMISSIONING";
    public static String DECOMMISSIONED = "DECOMMISSIONED";
    public static String REPAIRED = "REPAIRED";
    public static String CHECKED = "CHECKED";

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

    public User getServiceman() {
        return serviceman;
    }

    public void setServiceman(User serviceman) {
        this.serviceman = serviceman;
    }

    @Override
    public int compareTo(@NotNull Report o) {
        return getEventDate().compareTo(o.getEventDate());
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
