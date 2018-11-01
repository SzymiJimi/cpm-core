package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idItem;
    private String brand;
    private String model;
    private BigDecimal value;
    private Timestamp purchaseDate;
    private BigDecimal residualValue;
    private Timestamp warrantyDate;
    private String serialNumber;
    private String description;



    @JoinColumn(name = "location", referencedColumnName = "idLocation")
    @ManyToOne(optional = false)
    private Location location;

    @JoinColumn(name = "creationUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User creationUser;

    public Item() {
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getResidualValue() {
        return residualValue;
    }

    public void setResidualValue(BigDecimal residualValue) {
        this.residualValue = residualValue;
    }

    public Timestamp getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(Timestamp warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(User creationUser) {
        this.creationUser = creationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(idItem, item.idItem) &&
                Objects.equals(brand, item.brand) &&
                Objects.equals(model, item.model) &&
                Objects.equals(value, item.value) &&
                Objects.equals(purchaseDate, item.purchaseDate) &&
                Objects.equals(residualValue, item.residualValue) &&
                Objects.equals(warrantyDate, item.warrantyDate) &&
                Objects.equals(serialNumber, item.serialNumber) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idItem, brand, model, value, purchaseDate, residualValue, warrantyDate, serialNumber, description);
    }
}
