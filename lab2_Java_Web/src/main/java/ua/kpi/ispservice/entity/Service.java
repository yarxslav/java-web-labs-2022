package ua.kpi.ispservice.entity;

import java.util.Objects;

public class Service {

    private Long id;
    private String serviceName;
    private String description;

    public Service(Long id, String serviceName, String description) {
        this.id = id;
        this.serviceName = serviceName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
        Service service = (Service) o;
        return id.equals(service.id) && serviceName.equals(service.serviceName) && description.equals(service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceName, description);
    }

    @Override
    public String toString() {
        return serviceName + ": " + description;
    }
}
