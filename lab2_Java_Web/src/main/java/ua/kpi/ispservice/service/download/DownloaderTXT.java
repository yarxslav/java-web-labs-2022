package ua.kpi.ispservice.service.download;

import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.view.options.SortOption;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DownloaderTXT extends Downloader {

    @Override
    public void download(SortOption sortOption) {
        List<Service> services = serviceService.findAll();

        try (BufferedWriter f_writer
                     = new BufferedWriter(new FileWriter(
                "C:\\Users\\User\\Downloads\\allTariffs.txt"));) {
            for (Service service : services) {
                List<Tariff> tariffs = tariffService.getTariffsByService(service, sortOption);
                f_writer.write(service.getServiceName() + ":\n");
                for (Tariff tariff : tariffs) {
                    f_writer.write("\t" + tariff.getName() + ": " + tariff.getDescription() + "\n\tCost: " + tariff.getCost() + "\n");
                }
                f_writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }
    }

    @Override
    public void download(Service service, SortOption sortOption) {
        try (BufferedWriter f_writer
                     = new BufferedWriter(new FileWriter(
                "C:\\Users\\User\\Downloads\\" + service.getServiceName() + "Tariffs.txt"));) {
            List<Tariff> tariffs = tariffService.getTariffsByService(service, sortOption);
            f_writer.write(service.getServiceName() + ":\n");
            for (Tariff tariff : tariffService.getTariffsByService(service, sortOption)) {
                f_writer.write("\t" + tariff.getName() + ": " + tariff.getDescription() + "\n\tCost: " + tariff.getCost() + "\n");
            }
            f_writer.write("\n");
        } catch (IOException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }
    }
}
