package ua.kpi.ispservice.service;

import ua.kpi.ispservice.service.download.Downloader;
import ua.kpi.ispservice.service.download.DownloaderDOCX;
import ua.kpi.ispservice.service.download.DownloaderPDF;
import ua.kpi.ispservice.service.download.DownloaderTXT;
import ua.kpi.ispservice.view.options.DownloadOptions;
import ua.kpi.ispservice.entity.Service;
import ua.kpi.ispservice.view.options.SortOption;

public class DownloadService {

    public void download(DownloadOptions downloadOption, Service service, SortOption sortOption) {
        Downloader downloader = null;
        switch (downloadOption) {
            case TXT -> downloader = new DownloaderTXT();
            case PDF -> downloader = new DownloaderPDF();
            case DOCX -> downloader = new DownloaderDOCX();
        }
        if (service != null) {
            downloader.download(service, sortOption);
        } else {
            downloader.download(sortOption);
        }
    }

}
