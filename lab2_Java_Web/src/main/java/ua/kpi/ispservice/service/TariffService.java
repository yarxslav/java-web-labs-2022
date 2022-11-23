package ua.kpi.ispservice.service;

import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.repository.TariffRepository;
import ua.kpi.ispservice.view.options.SortOption;

import java.util.List;

public class TariffService {

    private TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public List<Tariff> getTariffsByService(Service service, SortOption sortOption) {
        return tariffRepository.getAllByService(service, sortOption);
    }

    public Tariff getByServiceAndName(Service service, String name) {
        return tariffRepository.getByServiceAndName(service, name);
    }

    public void create(Tariff tariff) {
        tariffRepository.create(tariff);
    }

    public void delete(Tariff tariff) {
        tariffRepository.delete(tariff);
    }

    public void update(Tariff tariff) {
        tariffRepository.update(tariff);
    }
}
