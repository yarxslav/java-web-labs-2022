package ua.kpi.ispservice.dao;

import ua.kpi.ispservice.entity.Service;

import java.util.List;

public interface ServiceDao {

    List<Service> findAll();
    Service findServiceById(Long id);
    Service findServiceByName(String name);
}
