package com.kpi.java.web.labs.lab1_java_web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * @author yarxs
 */
public class ReverseWriter implements Runnable {

    private final File dirToSearch;
    private final String dirToStorePathName;
    private final ExecutorService pool;

    public ReverseWriter(File dirToSearch, String dirToStorePathName, ExecutorService pool) {
        this.dirToSearch = dirToSearch;
        this.dirToStorePathName = dirToStorePathName;
        this.pool = pool;
    }

    public void reverseWrite(File file) {
        if (file.isFile() && file.toString().endsWith(".java")) {
            String reversedFilePathName = dirToStorePathName + "\\" + file.getName().substring(0, file.getName().indexOf(".java")) + "Reversed" + ".java";
            try (Scanner reader = new Scanner(file); FileWriter writer = new FileWriter(reversedFilePathName)) {
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    writer.write(new StringBuffer(data).reverse().toString());
                    writer.write(System.getProperty("line.separator"));
                }
            } catch (IOException e) {
                System.out.println("An error occurred. Reload the app and try again!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

        File[] files = dirToSearch.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    ReverseWriter reverseWriter = new ReverseWriter(f, dirToStorePathName, pool);
                    pool.submit(reverseWriter);
                } else {
                    reverseWrite(f);
                }
            }
        }
    }
}
