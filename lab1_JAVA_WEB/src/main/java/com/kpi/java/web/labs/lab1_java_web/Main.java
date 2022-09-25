package com.kpi.java.web.labs.lab1_java_web;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yarxs
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        File dirToSearch;
        String dirToStorePath;
        String dirToStoreName;
        String dirToStorePathName;

        while (true) {

            try {
                System.out.println("Enter path to dir where to find files: ");
                dirToSearch = new File(sc.next());
                validateDir(dirToSearch);

                System.out.println("Enter path for storing directory: ");
                dirToStorePath = sc.next();

                System.out.println("Enter name for storing directory: ");
                dirToStoreName = sc.next();

                dirToStorePathName = dirToStorePath + "\\" + dirToStoreName;

                if (!createStoringDir(dirToStorePathName)) {
                    throw new UnsupportedOperationException();
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong path to directory. Try again!");
            } catch (UnsupportedOperationException e) {
                System.out.println("Failed to create storing directory. Try again!");
            }
        }

        ExecutorService pool = Executors.newCachedThreadPool();
        ReverseWriter reverseWriter = new ReverseWriter(dirToSearch, dirToStorePathName, pool);
        pool.submit(reverseWriter);

        try {
            Thread.sleep(5000);
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All files reversed!");

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
        }

        return created;
    }

}
