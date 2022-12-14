package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.context.ApplicationContext;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.ServiceRepository;
import ua.kpi.ispservice.repository.TariffRepository;
import ua.kpi.ispservice.repository.UserRepository;
import ua.kpi.ispservice.repository.dao.RoleDaoImpl;
import ua.kpi.ispservice.repository.dao.ServiceDaoImpl;
import ua.kpi.ispservice.repository.dao.TariffDaoImpl;
import ua.kpi.ispservice.repository.dao.UserDaoImpl;
import ua.kpi.ispservice.repository.RoleRepository;
import ua.kpi.ispservice.service.RoleService;
import ua.kpi.ispservice.service.ServiceService;
import ua.kpi.ispservice.service.TariffService;
import ua.kpi.ispservice.service.UserService;
import ua.kpi.ispservice.view.AdminView;
import ua.kpi.ispservice.view.IndexView;
import ua.kpi.ispservice.view.options.AdminOptions;

import java.math.BigDecimal;
import java.util.List;

public class AdminController {

    private AdminView adminView;
    private ServiceService serviceService;
    private TariffService tariffService;
    private UserService userService;
    private RoleService roleService;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
        serviceService = new ServiceService(new ServiceRepository(new ServiceDaoImpl()));
        tariffService = new TariffService(new TariffRepository(new TariffDaoImpl()));
        userService = new UserService(new UserRepository(new UserDaoImpl()));
        roleService = new RoleService(new RoleRepository(new RoleDaoImpl()));
    }

    public void execute() {
        adminView.greeting(ApplicationContext.getInstance().getCurrentUser().getUsername());
        AdminOptions option = adminView.defineAdminOption();
        if (option != null) {
            switch (option) {
                case ADD_TARIFF -> addTariff();
                case DELETE_TARIFF -> deleteTariff();
                case UPDATE_TARIFF -> updateTariff();
                case REGISTER_CUSTOMER -> registerCustomer();
                case BLOCK_CUSTOMER -> updateStatus(true);
                case UNBLOCK_CUSTOMER -> updateStatus(false);
                case CHECK_SERVICES -> checkServices();
                case LOGOUT -> logout();
            }
        } else {
            adminView.wrongOption();
        }
    }

    private void addTariff() {
        Service service = serviceService.findByName(adminView.askForData("Service Name"));

        try {
            Tariff tariff = new Tariff(service.getId(), adminView.askForData("Tariff Name"),
                    adminView.askForData("Tariff Description"),
                    new BigDecimal(Double.parseDouble(adminView.askForData("Tariff Cost"))));
            tariffService.create(tariff);
            adminView.tariffAdded(tariff.getName());
        } catch (NumberFormatException e) {
            adminView.wrongData();
            addTariff();
        }
    }

    private void deleteTariff() {
        Service service = serviceService.findByName(adminView.askForData("Service Name"));
        if (service != null) {
            String name = adminView.askForData("Tariff name");
            Tariff tariff = tariffService.getByServiceAndName(service, name);
            if (tariff != null) {
                tariffService.delete(tariff);
                adminView.tariffDeleted();
            } else {
                adminView.wrongTariff();
            }
        } else {
            adminView.wrongService();
        }

    }

    private void updateTariff() {
        Service service = serviceService.findByName(adminView.askForData("Service Name"));
        if (service != null) {
            String name = adminView.askForData("Tariff name");
            Tariff tariff = tariffService.getByServiceAndName(service, name);
            if (tariff != null) {
                switch (adminView.defineUpdateOption()) {
                    case NAME -> updateTariffName(tariff);
                    case DESCRIPTION -> updateTariffDescription(tariff);
                    case COST -> updateTariffCost(tariff);
                }
                adminView.tariffUpdated();
            } else {
                adminView.wrongTariff();
            }
        } else {
            adminView.wrongService();
        }

    }

    private void updateTariffName(Tariff tariff) {
        String newName = adminView.askForData("New Name");
        tariff.setName(newName);
        tariffService.update(tariff);
    }

    private void updateTariffDescription(Tariff tariff) {
        String newDescription = adminView.askForData("New Description");
        tariff.setDescription(newDescription);
        tariffService.update(tariff);
    }

    private void updateTariffCost(Tariff tariff) {
        try {
            BigDecimal newCost = new BigDecimal(Double.parseDouble(adminView.askForData("New Cost")));
            tariff.setCost(newCost);
            tariffService.update(tariff);
        } catch (NumberFormatException e) {
            adminView.wrongData();
            updateTariffCost(tariff);
        }


    }

    private void registerCustomer() {
        String username = adminView.askForData("Username");
        String password = adminView.askForData("Password");
        User user = null;

        if (userService.findByUsername(username) == null) {
            user = new User(username, password, roleService.findByName("Customer").getId(), false);
            userService.create(user);
            adminView.userAdded(username);
        } else {
            adminView.userAlreadyExisting();
        }

    }

    private void updateStatus(boolean isBlocked) {
        String username = adminView.askForData("Username");
        User user = userService.findByUsername(username);
        if (user != null) {
            userService.updateStatus(user, isBlocked);
            adminView.statusUpdated(isBlocked);
        } else {
            adminView.userDoesntExist();
        }
    }

    private void logout() {
        ApplicationContext.getInstance().setCurrentUser(null);
        new IndexController(new IndexView(), new UserRepository(new UserDaoImpl())).execute();
    }

    private void checkServices() {
        List<Service> services = serviceService.findAll();
        adminView.displayServices(services);
    }
}
