package ua.kpi.ispservice.repository;

import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.repository.dao.TariffDaoImpl;
import ua.kpi.ispservice.view.options.SortOption;

import java.util.List;

public class TariffRepository {

    private TariffDaoImpl tariffDao;

    public TariffRepository(TariffDaoImpl tariffDao) {
        this.tariffDao = tariffDao;
    }

    public List<Tariff> getAllByService(Service service, SortOption sortOption) {
        return tariffDao.findByServiceId(service.getId(), sortOption);
    }

    public Tariff getByServiceAndName(Service service, String name) {
        return tariffDao.findByNameAndServiceId(name, service.getId());
    }

    public void create(Tariff tariff) {
        tariffDao.add(tariff);
    }

    public void delete(Tariff tariff) {
        tariffDao.delete(tariff);
    }

    public void update(Tariff tariff) {
        tariffDao.update(tariff);
    }
}
