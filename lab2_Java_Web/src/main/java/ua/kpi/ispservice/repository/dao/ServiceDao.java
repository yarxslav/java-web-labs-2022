package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Service;

import java.util.List;

public interface ServiceDao {

    List<Service> findAll();
    Service findServiceById(Long id);
    Service findServiceByName(String name);
}
