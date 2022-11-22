package ua.kpi.ispservice.service;

import ua.kpi.ispservice.ApplicationContext;
import ua.kpi.ispservice.entity.Subscription;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.AccountRepository;
import ua.kpi.ispservice.repository.SubscriptionRepository;
import ua.kpi.ispservice.repository.UserRepository;
import ua.kpi.ispservice.repository.dao.AccountDaoImpl;
import ua.kpi.ispservice.repository.dao.UserDaoImpl;

import java.math.BigDecimal;
import java.util.List;

public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;
    private AccountService accountService;
    private UserService userService;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
        accountService = new AccountService(new AccountRepository(new AccountDaoImpl()));
        userService = new UserService(new UserRepository(new UserDaoImpl()));
    }

    public boolean subscribe(Subscription subscription, Tariff tariff) {

        User user = ApplicationContext.getInstance().getCurrentUser();

        if (user.isBlocked()) {
            return false;
        } else {
            subscriptionRepository.subscribe(subscription);
            accountService.updateBalance(user, tariff.getCost().negate());

            int comparison = accountService.getBalanceByUser(user).compareTo(new BigDecimal(0.0));
            if (comparison != 0 && comparison != 1) {
                userService.block(user);
            }
            return true;
        }
    }

    public List<Subscription> findByUser(User currentUser) {
        return subscriptionRepository.findByUser(currentUser);
    }
}
