package ua.kpi.ispservice.service;

import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.AccountRepository;
import ua.kpi.ispservice.repository.UserRepository;
import ua.kpi.ispservice.repository.dao.UserDaoImpl;

import java.math.BigDecimal;

public class AccountService {

    private AccountRepository accountRepository;
    private UserService userService;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        userService = new UserService(new UserRepository(new UserDaoImpl()));
    }

    public BigDecimal getBalanceByUser(User user) {
        return accountRepository.getBalanceByUser(user);
    }

    public void updateBalance(User currentUser, BigDecimal amount) {
        BigDecimal newBalance = getBalanceByUser(currentUser).add(amount);
        accountRepository.updateBalance(currentUser, newBalance);
        if (getBalanceByUser(currentUser).compareTo(new BigDecimal(0.0)) == 1) {
            userService.updateStatus(currentUser, false);
        }
    }
}
