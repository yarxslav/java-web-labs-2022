package ua.kpi.ispservice.view;

import java.util.Scanner;

public class IndexView {
    private final Scanner sc = new Scanner(System.in);

    public boolean goToMenu() {
        System.out.println("If you want to perform different operation, please, type \"Yes\"");
        System.out.println("If you want to leave, please, type \"No\"");
        String answer = sc.next();
        return answer.equalsIgnoreCase("Yes");
    }

    public void somethingWentWrong() {
        System.out.println("Ooops... Something went wrong. Try again later.");
    }
}
