package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class HospitalUtils {

    public static void delay(float seconds){
        try {
            Thread.sleep((long) (seconds * 1000F));
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void listAdd(List<String> list){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input value to add inside list, Type 'CONTINUE' to finalize list");
        boolean finished = false;
        while (!finished) {
            String possibleValue = scanner.nextLine();
            if (!list.contains(possibleValue)){
                if (!possibleValue.toUpperCase(Locale.ROOT).contains("CONTINUE")) {
                    list.add(possibleValue);
                    System.out.println(list);
                    printSeperator();
                } else {
                    finished = true;
                }
            } else {
                System.out.println("'" + possibleValue + "' is present already in list");
            }

        }
    }

    public static void listDelete(List<String> list){
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

    public static void pushRecord(LinkedList<String> log, String string){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        log.add(formattedDate + ":" + string);
    }


    public static void dischargePatient(Patient patient,boolean recovered){
        Room room = patient.getPatientRoom();
        if (room != null){
            patient.setPatientRoom(null);
            room.setAssignedPatient(null);
            if (room.getAssignedEmployee() != null){
                Employee employee = patient.getPatientRoom().getAssignedEmployee();
                employee.setAssignedRoom(null);
                room.setAssignedEmployee(null);
            }
        }
        pushRecord(patient.getHistoryLogs(),
                "Patient, " + patient.getName() + "(" + patient.getPatientId() + ") discharged \n" +
                        "RECOVERED?: " + recovered);
    }

    public static void dischargePatient(Patient patient,boolean recovered,String reason){
        Room room = patient.getPatientRoom();
        if (room != null){
            patient.setPatientRoom(null);
            room.setAssignedPatient(null);
            if (room.getAssignedEmployee() != null){
                Employee employee = patient.getPatientRoom().getAssignedEmployee();
                employee.setAssignedRoom(null);
                room.setAssignedEmployee(null);
            }
        }
        pushRecord(patient.getHistoryLogs(),
                "Patient, " + patient.getName() + "(" + patient.getPatientId() + ") discharged \n" +
                        "RECOVERED?: " + recovered + "\n" +
                        "Reason:" + reason);
    }



    public static void printSortingList(List arrayList,int pagesize,boolean seperatorEnabled){
        var page = 0;
        while (true) {
            printPage(arrayList, (page * pagesize), Math.min((page + 1) * pagesize, arrayList.size()),seperatorEnabled);
            delay(0.2F);
            System.out.println("NEXT - Next Page, PREVIOUS - Previous Page, CONTINUE - Continue");
            var input = getInput();
            if (input == 0) {
                page = Math.max(0, page - 1);
            } else if (input == 1) {
                page = Math.min(page + 1, arrayList.size() / pagesize);
            } else if (input == 2) {
                printSeperator();
                break;
            }
        }
    }

    private static void printPage(List items, int lower, int upper,boolean seperatorEnabled) {
        for (var i = lower; i < upper; i++) {
            System.out.println(items.get(i));
            if(seperatorEnabled){
                printSeperator();
            }
        }
    }

    private static int getInput() {
        final int NEXT = 1;
        final int PREVIOUS = 0;
        final int CONTINUE = 2;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return switch (input.toUpperCase()) {
            case "NEXT" -> NEXT;
            case "PREVIOUS" -> PREVIOUS;
            case "CONTINUE" -> CONTINUE;
            default -> -1;
        };
    }

    public static boolean presenceCheck(Patient patient){
        if (!patient.isPresent()){
            System.out.println("Patient is not present,unable to do task");
        }
        return patient.isPresent();
    }

    public static void printSeperator(){
        System.out.println("-------------------");
    }

    public static void viewHistory(Patient currentPatient){
        List<Object> logLIst = new ArrayList<>(currentPatient.getHistoryLogs());
        HospitalUtils.printSortingList(logLIst,5,true);
    }

}
