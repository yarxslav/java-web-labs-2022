package ua.kpi.ispservice.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    public Long id;
    public Long ownerId;
    public BigDecimal balance;

    public Account(Long id, Long ownerId, BigDecimal balance) {
        this.id = id;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) && ownerId.equals(account.ownerId) && balance.equals(account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, balance);
    }
}
