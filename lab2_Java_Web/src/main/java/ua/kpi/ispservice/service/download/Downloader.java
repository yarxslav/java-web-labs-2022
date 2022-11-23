package ua.kpi.ispservice.service.download;

import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.repository.ServiceRepository;
import ua.kpi.ispservice.repository.TariffRepository;
import ua.kpi.ispservice.repository.dao.ServiceDaoImpl;
import ua.kpi.ispservice.repository.dao.TariffDaoImpl;
import ua.kpi.ispservice.service.ServiceService;
import ua.kpi.ispservice.service.TariffService;
import ua.kpi.ispservice.view.options.SortOption;

public abstract class Downloader {

    ServiceService serviceService;
    TariffService tariffService;

    public Downloader() {
        serviceService = new ServiceService(new ServiceRepository(new ServiceDaoImpl()));
        tariffService = new TariffService(new TariffRepository(new TariffDaoImpl()));
    }
    public abstract void download(SortOption sortOption);
    public abstract void download(Service service, SortOption sortOption);
}
