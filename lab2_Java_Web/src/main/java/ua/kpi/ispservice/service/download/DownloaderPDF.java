package ua.kpi.ispservice.service.download;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.view.options.SortOption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class DownloaderPDF extends Downloader {

    @Override
    public void download(SortOption sortOption) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\User\\Downloads\\allTariffs.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(4);
            addTableHeader(table);
            addRows(table, sortOption);

            document.add(table);
            document.close();
        } catch (IOException | DocumentException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }
    }

    @Override
    public void download(Service service, SortOption sortOption) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\User\\Downloads\\" + service.getServiceName() + "Tariffs.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(4);
            addTableHeader(table);
            addRows(table, service, sortOption);

            document.add(table);
            document.close();
        } catch (IOException | DocumentException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Service Name", "Tariff Name", "Tariff Description", "Tariff Cost")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, SortOption sortOption) {
        int counter = 0;
        for (Service service : serviceService.findAll()) {
            table.addCell(service.getServiceName());
            for (Tariff tariff : tariffService.getTariffsByService(service, sortOption)) {
                counter++;
                table.addCell(tariff.getName());
                table.addCell(tariff.getDescription());
                table.addCell(tariff.getCost().toString());
                if (counter < tariffService.getTariffsByService(service, sortOption).size()) {
                    table.addCell("");
                } else {
                    counter = 0;
                }
            }
        }
    }

    private void addRows(PdfPTable table, Service service, SortOption sortOption) {
        table.addCell(service.getServiceName());
        for (Tariff tariff : tariffService.getTariffsByService(service, sortOption)) {
            table.addCell(tariff.getName());
            table.addCell(tariff.getDescription());
            table.addCell(tariff.getCost().toString());
            table.addCell("");
        }
    }
}
