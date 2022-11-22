package ua.kpi.ispservice.repository;

import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.repository.dao.ServiceDaoImpl;

import java.util.List;

public class ServiceRepository {

    private ServiceDaoImpl serviceDao;

    public ServiceRepository(ServiceDaoImpl serviceDao) {
        this.serviceDao = serviceDao;
    }

    public List<Service> findAll() {
        return serviceDao.findAll();
    }

    public Service findByName(String serviceName) {
        return serviceDao.findServiceByName(serviceName);
    }
}
