/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kpi.java.web.labs.lab1_java_web;

import java.io.File;
import java.io.FileInputStream;
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

    private File dir;
    private String newDirName;
    private String word;
    private ExecutorService pool;
    
    public ReverseWritter(File dir, String word, ExecutorService pool) {
        this.dir = dir;
        this.word = word;
        this.pool = pool;
    }

    public boolean search(File ff) {
        try ( Scanner sc = new Scanner(new FileInputStream(ff))) {
            boolean flag = false;
            while (!flag && sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.contains(word)) {
                    flag = true;
                }
            }
            return flag;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Integer call() {
        int count = 0;
        try {
            File[] files = dir.listFiles();
            ArrayList<Future<Integer>> result = new ArrayList<>();

            for (File f : files) {
                if (f.isDirectory()) {
                    ReverseWritter counter = new ReverseWritter(f, word, pool);
                    Future<Integer> rez = pool.submit(counter);
                    result.add(rez);
                } else if (search(f)) {
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
