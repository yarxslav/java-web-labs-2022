package ua.kpi.ispservice.controller;

import ua.kpi.ispservice.context.ApplicationContext;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Subscription;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.entity.User;
import ua.kpi.ispservice.repository.*;
import ua.kpi.ispservice.repository.dao.*;
import ua.kpi.ispservice.service.*;
import ua.kpi.ispservice.view.CustomerView;
import ua.kpi.ispservice.view.IndexView;
import ua.kpi.ispservice.view.options.CustomerOptions;
import ua.kpi.ispservice.view.options.DownloadOptions;
import ua.kpi.ispservice.view.options.SortOption;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class CustomerController {

    private CustomerView customerView;
    private AccountService accountService;
    private ServiceService serviceService;
    private TariffService tariffService;
    private DownloadService downloadService;
    private SubscriptionService subscriptionService;

    public CustomerController(CustomerView customerView) {
        this.customerView = customerView;
        accountService = new AccountService(new AccountRepository(new AccountDaoImpl()));
        serviceService = new ServiceService(new ServiceRepository(new ServiceDaoImpl()));
        tariffService = new TariffService(new TariffRepository(new TariffDaoImpl()));
        downloadService = new DownloadService();
        subscriptionService = new SubscriptionService(new SubscriptionRepository(new SubscriptionDaoImpl()));
    }

    public void execute() {
        customerView.greeting(ApplicationContext.getInstance().getCurrentUser().getUsername());
        customerView.blockNotification(ApplicationContext.getInstance().getCurrentUser().isBlocked());
        CustomerOptions customerOption = customerView.defineCustomerOption();
        if (customerOption != null) {
            switch (customerOption) {
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
        } else {
            customerView.wrongOption();
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
        customerView.balanceUpdated();
    }

    private void checkServicesList() {
        List<Service> services = serviceService.findAll();
        customerView.displayServicesList(services);
    }

    private void checkTariffsForService() {
        String serviceName = customerView.getServiceName();
        Service service = serviceService.findByName(serviceName);
        if (service == null) {
            customerView.wrongServiceName();
            return;
        }
        SortOption sortOption = customerView.defineSortOption();

        if (sortOption != null) {
            List<Tariff> tariffs = tariffService.getTariffsByService(service, sortOption);
            customerView.displayTariffs(tariffs);
        } else {
            customerView.wrongSortOption();
        }
    }

    private void downloadAllTariffs() {
        customerView.downloadGroupedTariffs();
        SortOption sortOption = customerView.defineSortOption();

        downloadService.download(customerView.defineDownloadOptions(), null, sortOption);
        customerView.downloadSuccess();
    }

    private void downloadExactServiceTariffs() {
        SortOption sortOption = customerView.defineSortOption();

        Service service = serviceService.findByName(customerView.getServiceName());
        DownloadOptions downloadOption = customerView.defineDownloadOptions();
        if (downloadOption != null) {
            downloadService.download(downloadOption, service, sortOption);
            customerView.downloadSuccess();
        } else {
            customerView.wrongDownloadOption();
        }
    }

    private void subscribe() {
        User user = ApplicationContext.getInstance().getCurrentUser();

        if (user.isBlocked()) {
            customerView.blockedMessage();
        } else {
            Service service = serviceService.findByName(customerView.getServiceName());
            String tariffName = customerView.getTariffName();
            Tariff tariff = tariffService.getByServiceAndName(service, tariffName);
            Subscription subscription = new Subscription(user.getId(),
                    service.getId(), tariff.getId());
            if (subscriptionService.subscribe(subscription, tariff, user)) {
                customerView.subscribedSuccessfuly();
            } else {
                customerView.subscriptionExists();
            }
        }
    }

    private void checkSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.findByUser(ApplicationContext.getInstance().getCurrentUser());
        if (!subscriptions.isEmpty()) {
            customerView.displaySubscriptions(subscriptions);
        } else {
            customerView.noSubs();
        }

    }

    private void logout() {
        ApplicationContext.getInstance().setCurrentUser(null);
        new IndexController(new IndexView(), new UserRepository(new UserDaoImpl())).execute();
    }

}
