package ua.kpi.ispservice.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Subscription {

    private Long id;
    private Long userId;
    private Long serviceId;
    private String serviceName;
    private String tariffName;
    private BigDecimal tariffCost;

    private Long tariffId;

    public Subscription(Long id, Long userId, Long serviceId, String serviceName, String tariffName, BigDecimal tariffCost, Long tariffId) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.tariffName = tariffName;
        this.tariffCost = tariffCost;
        this.tariffId = tariffId;
    }

    public Subscription(Long id, Long userId, Long serviceId, Long tariffId) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.tariffId = tariffId;
    }

    public Subscription(Long userId, Long serviceId, Long tariffId) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.tariffId = tariffId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id.equals(that.id) && userId.equals(that.userId) && serviceId.equals(that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, serviceId);
    }

    @Override
    public String toString() {
        return "Service: " + serviceName + "\nTariff: " + tariffName + "\nCost: " + tariffCost;
    }
}
