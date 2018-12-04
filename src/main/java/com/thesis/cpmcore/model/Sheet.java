package com.thesis.cpmcore.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
public class Sheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSheet;
    private Integer code;
    private String unit;
    private Double quantity;
    private BigDecimal price;
    private BigDecimal value;
    private String comments;
    private Integer checked;

    @JoinColumn(name = "idStocktaking", referencedColumnName = "idStocktaking")
    @ManyToOne(optional = false)
    private Stocktaking idStocktaking;

    @JoinColumn(name = "idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Item idItem;

    public Sheet() {
    }

    public Sheet(Stocktaking idStocktaking,
                 Item idItem,
                 Integer checked,
                 Integer code,
                 String unit,
                 Double quantity,
                 BigDecimal price,
                 BigDecimal value) {
        this.idStocktaking = idStocktaking;
        this.idItem = idItem;
        this.checked = checked;
        this.code = code;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.value = value;
    }

    public Integer getIdSheet() {
        return idSheet;
    }

    public void setIdSheet(Integer idSheet) {
        this.idSheet = idSheet;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Stocktaking getIdStocktaking() {
        return idStocktaking;
    }

    public void setIdStocktaking(Stocktaking idStocktaking) {
        this.idStocktaking = idStocktaking;
    }

    public Item getIdItem() {
        return idItem;
    }

    public void setIdItem(Item idItem) {
        this.idItem = idItem;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "idSheet=" + idSheet +
                ", code=" + code +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", value=" + value +
                ", comments='" + comments + '\'' +
                ", idStocktaking=" + idStocktaking +
                ", idItem=" + idItem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sheet sheet = (Sheet) o;
        return Objects.equals(idSheet, sheet.idSheet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSheet);
    }
}
