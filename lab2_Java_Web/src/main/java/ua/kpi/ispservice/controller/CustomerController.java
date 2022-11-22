package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.ApplicationContext;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Subscription;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.repository.*;
import ua.kpi.ispservice.repository.dao.*;
import ua.kpi.ispservice.service.*;
import ua.kpi.ispservice.view.CustomerView;
import ua.kpi.ispservice.view.IndexView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class CustomerController {

    private CustomerView customerView;
    private AccountService accountService;
    private ServiceService serviceService;
    private TariffService tariffService;
    private Downloader downloader;
    private SubscriptionService subscriptionService;

    public CustomerController(CustomerView customerView) {
        this.customerView = customerView;
        accountService = new AccountService(new AccountRepository(new AccountDaoImpl()));
        serviceService = new ServiceService(new ServiceRepository(new ServiceDaoImpl()));
        tariffService = new TariffService(new TariffRepository(new TariffDaoImpl()));
        downloader = new Downloader(new TariffService(new TariffRepository(new TariffDaoImpl())),
                new ServiceService(new ServiceRepository(new ServiceDaoImpl())));
        subscriptionService = new SubscriptionService(new SubscriptionRepository(new SubscriptionDaoImpl()));
    }

    public void execute() {
        customerView.greeting(ApplicationContext.getInstance().getCurrentUser().getUsername());
        customerView.blockNotification(ApplicationContext.getInstance().getCurrentUser().isBlocked());
        switch (customerView.defineCustomerOption()) {
            case CHECK_BALANCE -> checkBalance();
            case UPDATE_BALANCE -> updateBalance();
            case CHECK_SERVICES_LIST -> checkServicesList();
            case CHECK_TARIFFS_FOR_SERVICE -> checkTariffsForService();
            case DOWNLOAD_ALL_TARIFFS -> downloadAllTariffs();
            case DOWNLOAD_TARIFFS -> downloadExactServiceTariffs();
            case SUBSCRIBE -> subscribe();
            case CHECK_SUBSCRIPTION -> checkSubscriptions();
            case LOG_OUT -> logout();
        }
    }

    private void checkBalance() {
        BigDecimal balance = accountService.getBalanceByUser(ApplicationContext.getInstance().getCurrentUser())
                .round(new MathContext(5, RoundingMode.HALF_UP));
        customerView.displayBalance(balance);
    }

    private void updateBalance() {
        BigDecimal amount = customerView.updateBalanceDialog();

        accountService.updateBalance(ApplicationContext.getInstance().getCurrentUser(), amount);
    }

    private void checkServicesList() {
        List<Service> services = serviceService.findAll();

        customerView.displayServicesList(services);
    }

    private void checkTariffsForService() {
        String serviceName = customerView.getServiceName();
        Service service = serviceService.findByName(serviceName);

        List<Tariff> tariffs = tariffService.getTariffsByService(service);

        customerView.displayTariffs(tariffs);
    }

    private void downloadAllTariffs() {
        downloader.download(customerView.defineDownloadOptions());
    }

    private void downloadExactServiceTariffs() {
        Service service = serviceService.findByName(customerView.getServiceName());

        downloader.download(customerView.defineDownloadOptions(), service);
    }

    private void subscribe() {
        Service service = serviceService.findByName(customerView.getServiceName());
        String tariffName = customerView.getTariffName();
        Tariff tariff = tariffService.getByServiceAndName(service, tariffName);
        Subscription subscription = new Subscription(ApplicationContext.getInstance().getCurrentUser().getId(),
                service.getId(), tariff.getId());

        if (!subscriptionService.subscribe(subscription, tariff)) {
            customerView.blockedMessage();
        }
    }

    private void checkSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.findByUser(ApplicationContext.getInstance().getCurrentUser());

        customerView.displaySubscriptions(subscriptions);
    }

    private void logout() {
        ApplicationContext.getInstance().setCurrentUser(null);
        new IndexController(new IndexView(), new UserRepository(new UserDaoImpl())).execute();
    }

}
