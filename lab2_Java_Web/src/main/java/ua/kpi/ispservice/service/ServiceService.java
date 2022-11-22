package ua.kpi.ispservice.service;

import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.repository.ServiceRepository;

import java.util.List;

public class ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    public Service findByName(String serviceName) {
        return serviceRepository.findByName(serviceName);
    }
}
