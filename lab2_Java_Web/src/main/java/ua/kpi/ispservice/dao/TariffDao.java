package ua.kpi.ispservice.dao;

import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.utils.SortOption;

import java.util.List;

public interface TariffDao {

    void add(Tariff tariff);
    void update(Tariff tariff);
    void delete(Tariff tariff);

    Tariff findById(Long id);
    Tariff findByIdAndServiceId(Long id, Long serviceId);
    List<Tariff> findByServiceId(Long serviceId);
    List<Tariff> findAll(SortOption sortOption);
}
