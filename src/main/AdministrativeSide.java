package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static main.HospitalUtils.passwordCheck;


public class AdministrativeSide extends AdministrativeServer {


    public static void adminLogin(AdministrativeServer administrativeServer) {
        ArrayList<Employee> employeeList = administrativeServer.getEmployeeList();

        Scanner scanner = new Scanner(System.in);
        Employee currentEmployee = null;
        boolean idPass = false;
        while (!idPass) {
            System.out.println("Enter user's name:");
            String possibleName = scanner.nextLine();
            boolean passed = false;
            for (Employee employee : employeeList) {
                passed = employee.getName().equals(possibleName);
                if (passed) {
                    idPass = true;
                    currentEmployee = employee;
                }
            }
            if (!passed) {
                System.out.println(possibleName + " is not present, Please try again");
                HospitalUtils.delay(0.2F);
            }
        }

        boolean passwordPass = false;
        while (!passwordPass) {
            System.out.println("Enter password:");
            if (currentEmployee.getPassword().equals(scanner.nextLine())) {
                passwordPass = true;
            } else {
                System.out.println("Incorrect password, Please try again");
                HospitalUtils.delay(0.2F);
            }
        }

        boolean usingPass = false;
        while (!usingPass) {
            System.out.println("Welcome " + currentEmployee.getName() + ", What would you like to do?");
            HospitalUtils.delay(0.5F);

            System.out.println("SM - Staff Management, RC - Room Management,  R - Return");
            switch (scanner.nextLine()) {
                case "SM" -> {
                    boolean staffManagementPass = false;
                    while (!staffManagementPass) {
                        System.out.println("What would you like to do?");
                        HospitalUtils.delay(0.5F);

                        System.out.println("AS - Add Staff, RS - Remove Staff,  R - Return");
                        switch (scanner.nextLine()) {
                            case "AS" -> {
                                if (passwordCheck(currentEmployee)) {
                                    addStaff(employeeList);
                                }
                            }
                            case "RS" -> {
                                if (passwordCheck(currentEmployee)) {
                                    removeStaff(employeeList);
                                }
                            }
                            case "R" -> {
                                staffManagementPass = true;
                            }
                            default -> {
                                System.out.println("Unknown response, Please try again\n \n");
                                HospitalUtils.delay(0.2F);
                            }
                        }
                    }
                }
                case "RC" -> {
                    roomManage(administrativeServer);
                }
                case "R" -> {
                    usingPass = true;
                }
                default -> {
                    System.out.println("Unknown response, Please try again\n \n");
                    HospitalUtils.delay(0.2F);
                }
            }
        }
    }

    private static void addStaff(ArrayList<Employee> list){
        Employee employee = new Employee();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Staff Registration");
        System.out.println("####################");
        HospitalUtils.delay(0.2F);
        System.out.println("Input person's name: ");
        String name = scanner.nextLine();
        HospitalUtils.delay(0.2F);
        System.out.println("Input person's password: ");
        String password = scanner.nextLine();

        employee.setName(name);
        employee.setPassword(password);
        list.add(employee);
    }

    private static void removeStaff(ArrayList<Employee> list){
        Scanner scanner = new Scanner(System.in);
        HospitalUtils.delay(0.2F);
        System.out.println("Input person's name: ");
        boolean staffRemovePass = false;
        while (!staffRemovePass) {
            String possibleEmployee = scanner.nextLine();
            boolean passed = false;
            for (Employee employee : list) {
                passed = employee.getName().equals(possibleEmployee);
                if (passed) {
                    list.remove(employee);
                    staffRemovePass = true;
                    System.out.println(possibleEmployee + " removed.");
                    HospitalUtils.delay(1F);
                    break;
                }
            }
            if (!passed) {
                System.out.println(possibleEmployee + " is not present, Please try again");
                HospitalUtils.delay(0.2F);
            }
        }
    }



    private static void roomManage(AdministrativeServer administrativeServer){
        LinkedHashMap<Integer,Patient> roomList = administrativeServer.getRoomList();
        ArrayList<Patient> patientList = administrativeServer.getPatientList();
        Scanner scanner = new Scanner(System.in);

        boolean roomManagePass = false;
        while (!roomManagePass) {
            System.out.println("What would you like to do?");
            HospitalUtils.delay(0.5F);

            System.out.println("RL - Room List, MR - Manage Room,  R - Return");
            switch (scanner.nextLine()) {
                case "RL" -> {
                    for (int i = 1; i < roomList.size() + 1;i++){
                        String possibleName = roomList.get(i) != null ? roomList.get(i).getName() : "N/A";

                        System.out.print("Room No." + i + "\n" +
                                "Occupied: " + possibleName + "\n \n");
                    }
                }
                case "MR" -> {
                    boolean mRPass = false;
                    HospitalUtils.delay(0.5F);
                    System.out.println("Enter room no.");
                    while (!mRPass) {
                        int roomNo = scanner.nextInt();
                        scanner.nextLine();
                        if (roomList.containsKey(roomNo)){
                            mRPass = true;
                            boolean currentRoomPass = false;
                            while (!currentRoomPass) {
                                System.out.println("What would you like to do?");
                                HospitalUtils.delay(0.5F);
                                System.out.println("UP - Unassign Patient, AP - Assign Patient, US - Unassign Staff , AS - Assign Staff,  R - Return");
                                switch (scanner.nextLine()) {
                                    case "UP" -> {
                                        roomList.replace(roomNo,null);
                                        System.out.println("Room " + roomNo + " unassigned");
                                    }

                                    case "US" -> {
                                        boolean staffRemovePass = false;
                                        while (!staffRemovePass) {
                                            System.out.println("Enter staff name");
                                            String possibleName = scanner.nextLine();

                                            boolean passed = false;
                                            for (Employee employee : administrativeServer.getEmployeeList()) {
                                                passed = employee.getName().equals(possibleName);
                                                if (passed && employee.getAssignedRoomID() != -1) {
                                                    staffRemovePass = true;
                                                    employee.setAssignedRoomID(-1);
                                                    System.out.println(employee.getName() + " unassigned to room: " + roomNo);
                                                    break;
                                                }
                                            }
                                            if (!passed) {
                                                System.out.println(possibleName + " is not present in staff list, Please try again");
                                                HospitalUtils.delay(0.2F);
                                            }
                                        }
                                    }
                                    case "AP" -> {
                                        boolean patientAddPass = false;
                                        while (!patientAddPass) {
                                            System.out.println("Enter patient id.");
                                            int possibleId = scanner.nextInt();
                                            scanner.nextLine();

                                            boolean passed = false;
                                            for (Patient patient : patientList) {
                                                passed = patient.getPatientId() == possibleId;
                                                if (passed) {
                                                    patientAddPass = true;
                                                    roomList.replace(roomNo,patient);
                                                    System.out.println(patient.getName() + " assigned to room: " + roomNo);
                                                    break;
                                                }
                                            }
                                            if (!passed) {
                                                System.out.println(possibleId + " is not present in patient list, Please try again");
                                                HospitalUtils.delay(0.2F);
                                            }
                                        }
                                    }
                                    case "AS" -> {
                                        boolean staffAddPass = false;
                                        while (!staffAddPass) {
                                            System.out.println("Enter staff name");
                                            String possibleName = scanner.nextLine();

                                            boolean passed = false;
                                            for (Employee employee : administrativeServer.getEmployeeList()) {
                                                passed = employee.getName().equals(possibleName);
                                                if (passed && employee.getAssignedRoomID() == -1) {
                                                    staffAddPass = true;
                                                    employee.setAssignedRoomID(roomNo);
                                                    System.out.println(employee.getName() + " assigned to room: " + roomNo);
                                                    break;
                                                }
                                            }
                                            if (!passed) {
                                                System.out.println(possibleName + " is not present in staff list, Please try again");
                                                HospitalUtils.delay(0.2F);
                                            }
                                        }
                                    }
                                    case "R" -> {
                                        currentRoomPass = true;
                                    }
                                    default -> {
                                        System.out.println("Unknown response, Please try again\n \n");
                                        HospitalUtils.delay(0.2F);
                                    }
                                }
                            }
                        } else {
                            System.out.println("Room no. " + roomNo + " is missing, Please try again");
                        }
                    }
                }
                case "R" -> {
                    roomManagePass = true;
                }
                default -> {
                    System.out.println("Unknown response, Please try again\n \n");
                    HospitalUtils.delay(0.2F);
                }
            }
        }
    }

}
