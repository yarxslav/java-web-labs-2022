package ua.kpi.ispservice.repository;

import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.dao.AccountDaoImpl;

import java.math.BigDecimal;

public class AccountRepository {

    private AccountDaoImpl accountDao;

    public AccountRepository(AccountDaoImpl accountDao) {
        this.accountDao = accountDao;
    }

    public BigDecimal getBalanceByUser(User user) {
        return accountDao.getBalanceByUser(user);
    }

    public void updateBalance(User currentUser, BigDecimal amount) {
        accountDao.updateBalance(currentUser, amount);
    }
}
