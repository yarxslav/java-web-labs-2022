package com.kpi.java.web.labs.lab1_java_web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReverseWriter implements Runnable {

    private String dirToStorePathName;
    private File file;

    public ReverseWriter(String dirToStorePathName, File file) {
        this.dirToStorePathName = dirToStorePathName;
        this.file = file;
    }

    @Override
    public void run() {
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
}
