package ua.kpi.ispservice.dao;

import ua.kpi.ispservice.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    void create(Long ownerId);
    void updateBalance(Long ownerId, BigDecimal amount);
    List<Account> findAll();
}
