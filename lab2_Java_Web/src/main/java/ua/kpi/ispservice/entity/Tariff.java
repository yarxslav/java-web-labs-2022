package ua.kpi.ispservice.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Tariff {

    private Long id;
    private Long serviceId;
    private String name;
    private String description;
    private BigDecimal cost;


    public Tariff(Long id, Long serviceId, String name, String description, BigDecimal cost) {
        this.id = id;
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return id.equals(tariff.id) && serviceId.equals(tariff.serviceId) && cost.equals(tariff.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceId, cost);
    }
}
