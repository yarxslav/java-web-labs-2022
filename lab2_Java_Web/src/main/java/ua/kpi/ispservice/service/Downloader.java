package ua.kpi.ispservice.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.*;
import ua.kpi.ispservice.view.options.DownloadOptions;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class Downloader {
    private TariffService tariffService;
    private ServiceService serviceService;

    public Downloader(TariffService tariffService, ServiceService serviceService) {
        this.tariffService = tariffService;
        this.serviceService = serviceService;
    }

    public void download(DownloadOptions downloadOption) {
        switch (downloadOption) {
            case TXT -> downloadTXT();
            case PDF -> downloadPDF();
            case DOCX -> downloadDOCX();
        }
    }

    public void download(DownloadOptions downloadOption, Service service) {
        switch (downloadOption) {
            case TXT -> downloadTXT(service);
            case PDF -> downloadPDF(service);
            case DOCX -> downloadDOCX(service);
        }
    }

    private void downloadTXT() {
        List<Service> services = serviceService.findAll();

        try (BufferedWriter f_writer
                     = new BufferedWriter(new FileWriter(
                "C:\\Users\\User\\Downloads\\allTariffs.txt"));) {
            for (Service service : services) {
                List<Tariff> tariffs = tariffService.getTariffsByService(service);
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

    public void downloadTXT(Service service) {
        try (BufferedWriter f_writer
                     = new BufferedWriter(new FileWriter(
                "C:\\Users\\User\\Downloads\\" + service.getServiceName() + "Tariffs.txt"));) {
                List<Tariff> tariffs = tariffService.getTariffsByService(service);
                f_writer.write(service.getServiceName() + ":\n");
                for (Tariff tariff : tariffService.getTariffsByService(service)) {
                    f_writer.write("\t" + tariff.getName() + ": " + tariff.getDescription() + "\n\tCost: " + tariff.getCost() + "\n");
                }
                f_writer.write("\n");
        } catch (IOException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }
    }

    private void downloadPDF() {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\User\\Downloads\\allTariffs.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(4);
            addTableHeader(table);
            addRows(table);

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

    private void addRows(PdfPTable table) {
        int counter = 0;
        for (Service service : serviceService.findAll()) {
            table.addCell(service.getServiceName());
            for (Tariff tariff : tariffService.getTariffsByService(service)) {
                counter++;
                table.addCell(tariff.getName());
                table.addCell(tariff.getDescription());
                table.addCell(tariff.getCost().toString());
                if (counter < tariffService.getTariffsByService(service).size()) {
                    table.addCell("");
                } else {
                    counter = 0;
                }
            }
        }
    }

    private void addRows(PdfPTable table, Service service) {
            table.addCell(service.getServiceName());
            for (Tariff tariff : tariffService.getTariffsByService(service)) {
                table.addCell(tariff.getName());
                table.addCell(tariff.getDescription());
                table.addCell(tariff.getCost().toString());
                table.addCell("");
            }
    }

    private void downloadPDF(Service service) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\User\\Downloads\\" + service.getServiceName() + "Tariffs.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(4);
            addTableHeader(table);
            addRows(table, service);

            document.add(table);
            document.close();
        } catch (IOException | DocumentException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }
    }

    private void downloadDOCX() {
        try {
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\User\\Downloads\\allTariffs.docx"));

            XWPFTable table = document.createTable();

            XWPFTableRow rowOne = table.getRow(0);
            XWPFParagraph cell0 = rowOne.getCell(0).addParagraph();
            XWPFParagraph cell1 = rowOne.addNewTableCell().addParagraph();
            XWPFParagraph cell2 = rowOne.addNewTableCell().addParagraph();
            XWPFParagraph cell3 = rowOne.addNewTableCell().addParagraph();
            setRun(cell0.createRun(), "Calibri", 12, "000000", "Service Name", true);
            setRun(cell1.createRun(), "Calibri", 12, "000000", "Tariff Name", true);
            setRun(cell2.createRun(), "Calibri", 12, "000000", "Tariff Description", true);
            setRun(cell3.createRun(), "Calibri", 12, "000000", "Tariff Cost", true);

            int counter = 0;
            for (Service service : serviceService.findAll()) {
                for (Tariff tariff : tariffService.getTariffsByService(service)) {
                    XWPFTableRow row = table.createRow();
                    if (counter == 0) {
                        row.getCell(0).setText(service.getServiceName());
                        counter++;
                    } else if (counter > 0 && counter < tariffService.getTariffsByService(service).size()){
                        row.getCell(0).setText("");
                        counter++;
                    } else {
                        row.getCell(0).setText("");
                        counter = 0;
                    }
                    row.getCell(1).setText(tariff.getName());
                    row.getCell(2).setText(tariff.getDescription());
                    row.getCell(3).setText(tariff.getCost().toString());
                }
            }

            document.write(out);
            out.close();
        } catch (IOException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }

    }

    private static void setRun (XWPFRun run , String fontFamily , int fontSize , String colorRGB , String text , boolean bold) {
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setColor(colorRGB);
        run.setText(text);
        run.setBold(bold);
    }

    private void downloadDOCX(Service service) {
        try {
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\User\\Downloads\\" + service.getServiceName() + "Tariffs.docx"));

            XWPFTable table = document.createTable();

            XWPFTableRow rowOne = table.getRow(0);
            XWPFParagraph cell0 = rowOne.getCell(0).addParagraph();
            XWPFParagraph cell1 = rowOne.addNewTableCell().addParagraph();
            XWPFParagraph cell2 = rowOne.addNewTableCell().addParagraph();
            XWPFParagraph cell3 = rowOne.addNewTableCell().addParagraph();
            setRun(cell0.createRun(), "Calibri", 12, "000000", "Service Name", true);
            setRun(cell1.createRun(), "Calibri", 12, "000000", "Tariff Name", true);
            setRun(cell2.createRun(), "Calibri", 12, "000000", "Tariff Description", true);
            setRun(cell3.createRun(), "Calibri", 12, "000000", "Tariff Cost", true);

            int counter = 0;
                for (Tariff tariff : tariffService.getTariffsByService(service)) {
                    XWPFTableRow row = table.createRow();
                    if (counter == 0) {
                        row.getCell(0).setText(service.getServiceName());
                        counter++;
                    } else if (counter > 0 && counter < tariffService.getTariffsByService(service).size()){
                        row.getCell(0).setText("");
                        counter++;
                    } else {
                        row.getCell(0).setText("");
                        counter = 0;
                    }
                    row.getCell(1).setText(tariff.getName());
                    row.getCell(2).setText(tariff.getDescription());
                    row.getCell(3).setText(tariff.getCost().toString());
                }

            document.write(out);
            out.close();
        } catch (IOException e) {
            System.out.println("Ooops...Something went wrong. Unable to download :(");
        }
    }

}
