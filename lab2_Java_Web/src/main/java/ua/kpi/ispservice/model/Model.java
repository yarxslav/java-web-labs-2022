package ua.kpi.ispservice.model;

import ua.kpi.ispservice.dao.ServiceDaoImpl;
import ua.kpi.ispservice.dao.TariffDaoImpl;
import ua.kpi.ispservice.dao.UserDaoImpl;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.entity.User;

import java.util.List;

public class Model {
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final TariffDaoImpl tariffDao = new TariffDaoImpl();
    private final ServiceDaoImpl serviceDao = new ServiceDaoImpl();

    public boolean userExists(String username) {
        if (new UserDaoImpl().findUserByUsername(username) != null) {
            return true;
        }

        return false;
    }

    public boolean correctPassword(User user, String password) {
        if (user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }

    public User getUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public void createTariff(Tariff tariff) {
        tariffDao.add(tariff);
    }

    public void deleteTariff(Tariff tariff) {
        tariffDao.delete(tariff);
    }

    public void updateTariff(Tariff tariff) {
        tariffDao.update(tariff);
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public void blockUser(User user) {
        userDao.block(user);
    }

    public void unblockUser(User user) {
        userDao.unblock(user);
    }

    public List<Service> getAllServices() {
        return serviceDao.findAll();
    }

    public List<Tariff> getTariffsForService(Service service) {
        return tariffDao.findByServiceId(service.getId());
    }
}
