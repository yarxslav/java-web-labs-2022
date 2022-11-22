package ua.kpi.ispservice.repository.dao;

import ua.kpi.ispservice.entity.Account;
import ua.kpi.ispservice.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    void create(Long ownerId);
    void updateBalance(User user, BigDecimal amount);
    List<Account> findAll();
}
