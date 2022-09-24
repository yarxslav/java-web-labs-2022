/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kpi.java.web.labs.lab1_java_web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 * @author yarxs
 */
public class ReverseWritter implements Callable<Integer> {

    private File dirToSearch;
    private String dirToStorePathName;
    private ExecutorService pool;

    public ReverseWritter(File dirToSearch, String dirToStorePathName, ExecutorService pool) {
        this.dirToSearch = dirToSearch;
        this.dirToStorePathName = dirToStorePathName;
        this.pool = pool;
    }

    public boolean reverseWrite(File file) {
        if (file.isFile() && file.toString().endsWith(".java")) {
                String whereTo = dirToStorePathName + "\\" + file.getName().substring(0, file.getName().indexOf(".java")) + "Reversed" + ".java";
                try ( Scanner myReader = new Scanner(file);  FileWriter myWriter = new FileWriter(whereTo);) {
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        myWriter.write(new StringBuffer(data).reverse().toString());
                        myWriter.write(System.getProperty("line.separator"));
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    return false;
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    return false;
                }
            }
        
        return true;
    }

    @Override
    public Integer call() {
        int count = 0;
        try {
            File[] files = dirToSearch.listFiles();
            ArrayList<Future<Integer>> result = new ArrayList<>();

            for (File f : files) {
                if (f.isDirectory()) {
                    ReverseWritter reverseWritter = new ReverseWritter(f, dirToStorePathName, pool);
                    Future<Integer> rez = pool.submit(reverseWritter);
                    result.add(rez);
                } else if (reverseWrite(f)) {
                    count++;
                }

                for (Future<Integer> rez : result) {
                    count += rez.get();
                }

            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return count;
    }
}
