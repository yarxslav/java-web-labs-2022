/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.kpi.java.web.labs.lab1_java_web;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author yarxs
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        File dirToSearch;
        String dirToStorePath = null;
        String dirToStoreName = null;

        while (true) {

            try {
                System.out.println("Enter path to dir where to find files");
                dirToSearch = new File(sc.next());
                validateDir(dirToSearch);

                System.out.println("Enter path for storing directory");
                dirToStorePath = sc.next();

                System.out.println("Enter name for storing directory");
                dirToStoreName = sc.next();

                if (!createStoringDir(dirToStorePath + "\\" + dirToStoreName)) {
                    throw new UnsupportedOperationException();
                }

                break;
            } catch(InputMismatchException e) {
                System.out.println("Wrong path to directory. Try again!");
            } catch(UnsupportedOperationException e) {
                System.out.println("Failed to create storing directory. Try again!");
            }
        }

//        ExecutorService pool = Executors.newCachedThreadPool();
//        ReverseWritter counter = new ReverseWritter(new File(dir), word, pool);
//        Future<Integer> res = pool.submit(counter);
//
//        try {
//            System.out.println(res.get() + " files");
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        pool.shutdown();
    }

    private static void validateDir(File dir) {
        if (!dir.isDirectory()) {
            throw new InputMismatchException();
        }
    }

    private static boolean createStoringDir(String pathName) {
        boolean created = false;

        File theDir = new File(pathName);
        if (!theDir.exists()) {
            created = theDir.mkdir();
            System.out.println(created);
        }

        return created;
    }

}
