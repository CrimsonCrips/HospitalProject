package main;

import java.util.LinkedList;
import java.util.Scanner;

public class HospitalUtils {

    public static void delay(float seconds){
        try {
            Thread.sleep((long) (seconds * 1000F));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void listAdd(LinkedList list){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input value to add inside list, Type 'CONTINUE' to finalize list");
        boolean finished = false;
        while (!finished) {
            String possibleValue = scanner.nextLine();
            if (!list.contains(possibleValue)){
                if (!possibleValue.equals("CONTINUE")) {
                    list.add(possibleValue);
                    System.out.println(list);
                } else {
                    finished = true;
                }
            } else {
                System.out.println("'" + possibleValue + "' is present already in list");
            }

        }
    }

    public static void listDelete(LinkedList list){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input value to delete inside list, Type 'CONTINUE' to finalize list");
        boolean finished = false;
        while (!finished) {
            String possibleValue = scanner.nextLine();

            if (!possibleValue.equals("CONTINUE")) {
                if (list.contains(possibleValue)){
                    list.remove(possibleValue);
                    System.out.println(list);
                } else {
                    System.out.println("'" + possibleValue + "' is not present in list");
                }
            } else {
                finished = true;
            }
        }
    }

    public static boolean passwordCheck(Employee currentEmployee){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your password, " + currentEmployee.getName());
        while (true) {
            if (currentEmployee.getPassword().equals(scanner.nextLine())){
                return true;
            } else {
                System.out.println("Incorrect password, Please try again");
                HospitalUtils.delay(0.2F);
            }
        }
    }

}
