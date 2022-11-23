package ua.kpi.ispservice.service.download;

import org.apache.poi.xwpf.usermodel.*;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.entity.Tariff;
import ua.kpi.ispservice.view.options.SortOption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloaderDOCX extends Downloader {

    @Override
    public void download(SortOption sortOption) {
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
                for (Tariff tariff : tariffService.getTariffsByService(service, sortOption)) {
                    XWPFTableRow row = table.createRow();
                    if (counter == 0) {
                        row.getCell(0).setText(service.getServiceName());
                        counter++;
                    } else if (counter > 0 && counter < tariffService.getTariffsByService(service, sortOption).size()) {
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

    @Override
    public void download(Service service, SortOption sortOption) {
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
            for (Tariff tariff : tariffService.getTariffsByService(service, sortOption)) {
                XWPFTableRow row = table.createRow();
                if (counter == 0) {
                    row.getCell(0).setText(service.getServiceName());
                    counter++;
                } else if (counter > 0 && counter < tariffService.getTariffsByService(service, sortOption).size()){
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

    private static void setRun(XWPFRun run, String fontFamily, int fontSize, String colorRGB, String text, boolean bold) {
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setColor(colorRGB);
        run.setText(text);
        run.setBold(bold);
    }
}
