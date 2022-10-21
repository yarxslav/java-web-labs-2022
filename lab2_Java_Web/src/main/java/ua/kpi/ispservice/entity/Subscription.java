package ua.kpi.ispservice.entity;

import java.util.Objects;

public class Subscription {

    private Long id;
    private Long userId;
    private Long serviceId;

    public Subscription(Long id, Long userId, Long serviceId) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
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
}