package ua.kpi.ispservice.repository;

import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Subscription;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.dao.SubscriptionDaoImpl;

import java.util.List;

public class SubscriptionRepository {

    private SubscriptionDaoImpl subscriptionDao;

    public SubscriptionRepository(SubscriptionDaoImpl subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    public void subscribe(Subscription subscription) {
        subscriptionDao.create(subscription);
    }

    public List<Subscription> findByUser(User currentUser) {
        return subscriptionDao.findByCustomerId(currentUser.getId());
    }
}
