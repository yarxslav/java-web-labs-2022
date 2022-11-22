package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.ApplicationContext;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.repository.ServiceRepository;
import ua.kpi.ispservice.repository.TariffRepository;
import ua.kpi.ispservice.repository.dao.ServiceDaoImpl;
import ua.kpi.ispservice.repository.dao.TariffDaoImpl;
import ua.kpi.ispservice.service.ServiceService;
import ua.kpi.ispservice.service.TariffService;
import ua.kpi.ispservice.view.AdminView;

import java.math.BigDecimal;

public class AdminController {

    private AdminView adminView;
    private ServiceService serviceService;
    private TariffService tariffService;

    public AdminController(AdminView adminView) {
        this.adminView = adminView;
        serviceService = new ServiceService(new ServiceRepository(new ServiceDaoImpl()));
        tariffService = new TariffService(new TariffRepository(new TariffDaoImpl()));
    }

    public void execute() {
        adminView.greeting(ApplicationContext.getInstance().getCurrentUser().getUsername());
        switch (adminView.defineAdminOption()) {
            case ADD_TARIFF -> addTariff();
            case DELETE_TARIFF -> deleteTariff();
            case UPDATE_TARIFF -> updateTariff();
            case REGISTER_CUSTOMER -> registerCustomer();
            case BLOCK_CUSTOMER -> blockCustomer();
            case UNBLOCK_CUSTOMER -> unblockCustomer();
        }
    }

    private void addTariff() {
        Service service = serviceService.findByName(adminView.askForData("Service Name"));
        Tariff tariff = new Tariff(service.getId(), adminView.askForData("Tariff Name"),
                adminView.askForData("Tariff Description"),
                new BigDecimal(Double.parseDouble(adminView.askForData("Tariff Cost"))));

        tariffService.create(tariff);
    }

    private void deleteTariff() {
        Service service = serviceService.findByName(adminView.askForData("Service Name"));
        String name = adminView.askForData("Tariff name");
        Tariff tariff = tariffService.getByServiceAndName(service, name);

        tariffService.delete(tariff);
    }

    private void updateTariff() {
        Service service = serviceService.findByName(adminView.askForData("Service Name"));
        String name = adminView.askForData("Tariff name");
        Tariff tariff = tariffService.getByServiceAndName(service, name);

        switch (adminView.defineUpdateOption()) {
            case NAME -> updateTariffName(tariff);
            case DESCRIPTION -> updateTariffDescription(tariff);
            case COST -> updateTariffCost(tariff);
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
        BigDecimal newCost = new BigDecimal(Double.parseDouble(adminView.askForData("New Cost")));
        tariff.setCost(newCost);
        tariffService.update(tariff);
    }

    private void registerCustomer() {

    }

    private void blockCustomer() {

    }

    private void unblockCustomer() {

    }

}
