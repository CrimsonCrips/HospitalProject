package main;

import java.util.*;

import static main.HospitalUtils.printSeperator;

public class BaseHospital {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //Example Initialization
        AdministrativeServer administrativeServer = new AdministrativeServer();
        Employee employee0 = new Employee();
        employee0.setName("Anon");
        employee0.setPassword("password");
        administrativeServer.getEmployeeList().add(employee0);

        int patientExamples = 23;
        int roomExamples = 27;

        for (int i = 1; i < patientExamples + 1;i++){
            Patient patient = new Patient(i, false);
            patient.setName("A" + i);
            administrativeServer.getPatientList().add(patient);
            administrativeServer.addToQueue(patient);
        }

        for (int i = 1; i < roomExamples + 1;i++){
            Room room = new Room(i);
            administrativeServer.getRoomList().add(room);
        }


        //Intro Text
        System.out.println(" _____  ___________    ______          _           _   ");
        HospitalUtils.delay(0.3F);
        System.out.println(        "|  _  ||  _  | ___ \\   | ___ \\        (_)         | |  ");
        HospitalUtils.delay(0.15F);
        System.out.println(        "| | | || | | | |_/ /   | |_/ / __ ___  _  ___  ___| |_ ");
        HospitalUtils.delay(0.05F);
        System.out.println(        "| | | || | | |  __/    |  __/ '__/ _ \\| |/ _ \\/ __| __|");
        HospitalUtils.delay(0.03F);
        System.out.println(       "\\ \\_/ /\\ \\_/ / |       | |  | | | (_) | |  __/ (__| |_ ");
        HospitalUtils.delay(0.01F);
        System.out.println(        " \\___/  \\___/\\_|       \\_|  |_|  \\___/| |\\___|\\___|\\__|");
        HospitalUtils.delay(0.005F);
        System.out.println(        "                                     _/ |              ");
        HospitalUtils.delay(0.5F);
        System.out.println(        "                                    |__/               ");

        HospitalUtils.delay(1F);
        System.out.println(" ____ ___  _   _____ ____  ____  _     ____    ____ \n" +
                "/  __\\\\  \\//  /  __//  __\\/  _ \\/ \\ /\\/  __\\  / ___\\\n" +
                "| | // \\  /   | |  _|  \\/|| / \\|| | |||  \\/|  |    \\\n" +
                "| |_\\\\ / /    | |_//|    /| \\_/|| \\_/||  __/  \\___ |\n" +
                "\\____//_/     \\____\\\\_/\\_\\\\____/\\____/\\_/     \\____/\n" +
                "                                                    ");
        HospitalUtils.delay(0.5F);

        System.out.println("Note that patient identification format goes as follows:  Name(ID)");

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
                    System.out.println("to login, use Anon for username and 'password' for password");
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
            printSeperator();
        }
    }



}