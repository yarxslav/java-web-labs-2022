package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Subscription;

import java.util.List;

public interface SubscriptionDao {

    void create(Subscription subscription);
    List<Subscription> findAll();
    List<Subscription> findByCustomerId(Long id);

}
