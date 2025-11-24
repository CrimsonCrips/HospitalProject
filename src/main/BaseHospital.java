package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class BaseHospital {


    public static void main(String[] args){
        
        //Example Initialization
        AdministrativeServer administrativeServer = new AdministrativeServer();
        Employee employee0 = new Employee();
        employee0.setName("Anon");
        employee0.setPassword("password");
        administrativeServer.getEmployeeList().add(employee0);

        for (int i = 1; i < 20 + 1;i++){
            Patient patient = new Patient();
            patient.setPatientId(i);
            patient.setName("No." + i);
            administrativeServer.getPatientList().add(patient);
        }
        for (int i = 1; i < 20 + 1;i++){
            administrativeServer.getRoomList().put(i,null);
        }


        //Intro Text
        System.out.println(" _____  ___________    ______          _           _   ");
        HospitalUtils.delay(0.5F);
        System.out.println(        "|  _  ||  _  | ___ \\   | ___ \\        (_)         | |  ");
        HospitalUtils.delay(0.3F);
        System.out.println(        "| | | || | | | |_/ /   | |_/ / __ ___  _  ___  ___| |_ ");
        HospitalUtils.delay(0.1F);
        System.out.println(        "| | | || | | |  __/    |  __/ '__/ _ \\| |/ _ \\/ __| __|");
        HospitalUtils.delay(0.05F);
        System.out.println(       "\\ \\_/ /\\ \\_/ / |       | |  | | | (_) | |  __/ (__| |_ ");
        HospitalUtils.delay(0.01F);
        System.out.println(        " \\___/  \\___/\\_|       \\_|  |_|  \\___/| |\\___|\\___|\\__|");
        HospitalUtils.delay(0.005F);
        System.out.println(        "                                     _/ |              ");
        HospitalUtils.delay(0.5F);
        System.out.println(        "                                    |__/               ");

        HospitalUtils.delay(2F);
        System.out.println(" ____ ___  _   _____ ____  ____  _     ____    ____ \n" +
                "/  __\\\\  \\//  /  __//  __\\/  _ \\/ \\ /\\/  __\\  / ___\\\n" +
                "| | // \\  /   | |  _|  \\/|| / \\|| | |||  \\/|  |    \\\n" +
                "| |_\\\\ / /    | |_//|    /| \\_/|| \\_/||  __/  \\___ |\n" +
                "\\____//_/     \\____\\\\_/\\_\\\\____/\\____/\\_/     \\____/\n" +
                "                                                    ");
        HospitalUtils.delay(1F);
        Scanner scanner = new Scanner(System.in);

        boolean introPass = false;
        while (!introPass) {
            System.out.println("What would you like to do?");
            HospitalUtils.delay(1F);

            System.out.println("R - Register Patient,  S - Staff Login,  C - Close Program");
            switch (scanner.nextLine()) {
                case "R" -> {
                    PatientRegistration.register(administrativeServer);
                }
                case "S" -> {
                    AdministrativeSide.adminLogin(administrativeServer);
                }
                case "C" -> {
                    introPass = true;
                }
                default -> {
                    System.out.println("Unknown response, Please try again");
                    HospitalUtils.delay(0.2F);
                }
            }
        }
    }



}