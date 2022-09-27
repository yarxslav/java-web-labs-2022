package com.kpi.java.web.labs.lab1_java_web;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * @author yarxs
 */
public class Finder implements Runnable {

    private final File dirToSearch;
    private final String dirToStorePathName;
    private final ExecutorService pool;

    public Finder(File dirToSearch, String dirToStorePathName, ExecutorService pool) {
        this.dirToSearch = dirToSearch;
        this.dirToStorePathName = dirToStorePathName;
        this.pool = pool;
    }

    @Override
    public void run() {

        File[] files = dirToSearch.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    Finder finder = new Finder(f, dirToStorePathName, pool);
                    pool.submit(finder);
                } else {
                    ReverseWriter reverseWriter = new ReverseWriter(dirToStorePathName, f);
                    pool.submit(reverseWriter);
                }
            }
        }
    }
}
