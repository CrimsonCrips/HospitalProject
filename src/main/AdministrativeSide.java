package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static main.HospitalUtils.*;


public class AdministrativeSide extends AdministrativeServer implements HospitalInterface {


    public static void adminLogin(AdministrativeServer administrativeServer) {
        ArrayList<Employee> employeeList = administrativeServer.getEmployeeList();

        Scanner scanner = new Scanner(System.in);
        Employee currentEmployee = null;
        boolean idPass = false;
        while (!idPass) {
            printSeperator();
            System.out.println("to login, use Anon for username and 'password' for password");
            delay(0.2F);
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
            printSeperator();
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
            printSeperator();
            System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
            HospitalUtils.delay(1F);
            System.out.println("Welcome " + currentEmployee.getName() + ", What would you like to do?");
            HospitalUtils.delay(0.5F);

            System.out.println("RA - Room Assigning, PM - Patient Management,  R - Return");
            switch (scanner.nextLine()) {
                case "RA" -> {
                    roomManage(administrativeServer,currentEmployee);
                }
                case "PM" ->{
                    patientManage(administrativeServer);
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



    private static void roomManage(AdministrativeServer administrativeServer,Employee currentUser){
        ArrayList<Room> roomList = administrativeServer.getRoomList();
        ArrayList<Patient> patientList = administrativeServer.getPatientList();
        ArrayList<Employee> employeeList = administrativeServer.getEmployeeList();
        Scanner scanner = new Scanner(System.in);

        boolean roomManagePass = false;
        while (!roomManagePass) {
            printSeperator();
            System.out.println("What would you like to do?");
            HospitalUtils.delay(0.5F);

            System.out.println("RL - Room List, AR - Assign Room,  R - Return");
            switch (scanner.nextLine()) {
                case "RL" -> {
                    List<Object> list = new ArrayList<>();
                    for (Room room : roomList){
                        String possibleName = room.isOccupied() ? room.getAssignedPatient().getName() : "N/A";
                        String info = "Room No." + room.getRoomNo() + "\n" +
                                "Occupied: " + possibleName;
                        list.add(info);
                    }
                    HospitalUtils.printSortingList(list,5,true);
                }
                case "AR" -> {
                    printSeperator();
                    showAvailableRooms(administrativeServer);

                    boolean mRPass = false;
                    while (!mRPass) {
                        Room selectedRoom = roomAsk(administrativeServer);
                        if (selectedRoom != null){
                            mRPass = true;
                            boolean currentRoomPass = false;
                            while (!currentRoomPass) {
                                printSeperator();
                                System.out.println("What would you like to do?");
                                HospitalUtils.delay(0.1F);
                                System.out.println("AP - Assign Patient, AS - Assign Staff,  R - Return");
                                switch (scanner.nextLine()) {
                                    case "AP" -> {
                                        administrativeServer.viewQueue(administrativeServer);
                                        while (true) {
                                            printSeperator();
                                            Patient currentPatient = patientAsk(administrativeServer);
                                            if (currentPatient != null) {
                                                assignPatient(currentPatient,selectedRoom,administrativeServer);
                                                if(selectedRoom.getAssignedEmployee() == null){
                                                    boolean staffQuestion = false;
                                                    while (!staffQuestion) {
                                                        System.out.println("Assign a staff inside selected room?  Y/N");
                                                        printSeperator();
                                                        switch (HospitalInterface.yesOrNoDeterminer(scanner.nextLine())) {
                                                            case 1 -> {
                                                                assignStaff(administrativeServer, selectedRoom);
                                                                staffQuestion = true;
                                                            }
                                                            case 0 -> {
                                                                staffQuestion = true;
                                                            }
                                                            case -1 -> {
                                                                System.out.println("Unknown response, Please try again\n \n");
                                                                HospitalUtils.delay(0.2F);
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            } else {
                                                System.out.println("Selected patient is not present in patient list, Please try again");
                                                HospitalUtils.delay(0.2F);
                                            }

                                        }
                                        currentRoomPass = true;
                                    }
                                    case "AS" -> {
                                        assignStaff(administrativeServer, selectedRoom);
                                        currentRoomPass = true;
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
                            System.out.println("Selected room no is missing or incorrect, Please try again");
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

    private static void patientManage(AdministrativeServer administrativeServer) {
        ArrayList<Room> roomList = administrativeServer.getRoomList();
        ArrayList<Patient> patientList = administrativeServer.getPatientList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Note that patient identification format goes as follows:  Name(ID)");
        List<Object> list = new ArrayList<>();
        for (Patient patient : patientList){
            String roomPossible = patient.getPatientRoom() != null ? String.valueOf(patient.getPatientRoom().getRoomNo()) : "N/A";
            String info = patient.getName() + "(" + patient.getPatientId() + ") Room:" + roomPossible;


            list.add(info);
        }
        HospitalUtils.printSortingList(list,5,false);

        boolean patientPass = false;
        while (!patientPass) {
            printSeperator();
            Patient currentPatient = patientAsk(administrativeServer);

            if (currentPatient != null) {
                patientPass = true;
                boolean patientManage = false;
                while (!patientManage) {
                    printSeperator();
                    System.out.println("What would you like to do?");
                    HospitalUtils.delay(0.5F);

                    System.out.println("DP - Discharge Patient, MC - Modify Conditions, AR - Assign Room \n" +
                            "HC - History Check, AM - Assign Medications,  R - Return");
                    switch (scanner.nextLine()) {
                        case "AM" -> {
                            if (!presenceCheck(currentPatient))
                                return;
                            createTreatment(currentPatient);
                        }
                        case "DP" -> {
                            if (!presenceCheck(currentPatient))
                                return;
                            boolean recoveredPass = false;
                            while (!recoveredPass) {
                                printSeperator();
                                System.out.println("Has patient recovered fully?  Y/N");
                                switch (HospitalInterface.yesOrNoDeterminer(scanner.nextLine())) {
                                    case 1 -> {
                                        dischargePatient(currentPatient,true);
                                        recoveredPass = true;
                                        patientManage = true;

                                    }
                                    case 0 ->{
                                        System.out.println("Write down remarks");

                                        String remarks = scanner.nextLine();
                                        dischargePatient(currentPatient, false, remarks);
                                        recoveredPass = true;
                                        patientManage = true;
                                    }
                                    case -1 -> {
                                        System.out.println("Unknown response, Please try again\n \n");
                                        HospitalUtils.delay(0.2F);
                                    }
                                }
                                currentPatient.setPresent(false);
                            }

                        }
                        case "AR" -> {
                            if (!presenceCheck(currentPatient))
                                return;
                            while (true){
                                showAvailableRooms(administrativeServer);
                                Room selectedRoom = roomAsk(administrativeServer);
                                if (selectedRoom != null) {
                                    assignPatient(currentPatient, selectedRoom, administrativeServer);
                                    break;
                                } else {
                                    System.out.println("Selected room no is missing or incorrect, Please try again");
                                    printSeperator();
                                }
                            }
                        }
                        case "MC" -> {
                            boolean finishedList = false;
                            while (!finishedList) {
                                printSeperator();
                                System.out.println("Select changes to the given list");
                                System.out.println(currentPatient.getPatientConditions());
                                HospitalUtils.delay(0.2F);
                                System.out.println("F - Finished,  A - Add,  D - Delete");
                                switch (scanner.nextLine()) {
                                    case "F" -> {
                                        finishedList = true;
                                    }
                                    case "A" -> {
                                        HospitalUtils.listAdd(currentPatient.getPatientConditions());
                                    }
                                    case "D" -> {
                                        HospitalUtils.listDelete(currentPatient.getPatientConditions());
                                    }
                                    default -> {
                                        HospitalUtils.delay(0.2F);
                                    }
                                }
                            }
                        }
                        case "HC" -> {
                            viewHistory(currentPatient);
                        }
                        case "R" -> {
                            patientManage = true;
                        }
                        default -> {
                            System.out.println("Unknown response, Please try again\n \n");
                            HospitalUtils.delay(0.2F);
                        }
                    }
                }
            } else {
                System.out.println("Selected patient is not present in patient list, Please try again");
                HospitalUtils.delay(0.2F);
            }
        }



    }


    private static void assignPatient(Patient patient, Room room,AdministrativeServer administrativeServer){
        Scanner scanner = new Scanner(System.in);
        int roomNo = room.getRoomNo();
        Patient replacedPatient = room.getAssignedPatient();
        if (replacedPatient != null){
            boolean replaceQuestion = false;
            while (!replaceQuestion) {
                System.out.println(replacedPatient.getName() + " will result in no rooms, Assign to new room? Y/N");
                printSeperator();
                switch (HospitalInterface.yesOrNoDeterminer(scanner.nextLine())) {
                    case 1 -> {
                        showAvailableRooms(administrativeServer);
                        while (true){
                            Room selectedRoom = roomAsk(administrativeServer);
                            if (selectedRoom != null) {
                                assignPatient(replacedPatient, selectedRoom, administrativeServer);
                                break;
                            } else {
                                System.out.println("Selected room no is missing or incorrect, Please try again");
                                printSeperator();
                            }
                        }
                        replaceQuestion = true;
                    }
                    case 0 -> {
                        pushRecord(patient.getHistoryLogs(),
                                "Patient, " + replacedPatient.getName() + "(" + replacedPatient.getPatientId() + ") unassigned to room: " + roomNo);
                        replaceQuestion = true;
                    }
                    case -1 -> {
                        System.out.println("Unknown response, Please try again\n \n");
                        HospitalUtils.delay(0.2F);
                    }
                }
            }
            HospitalUtils.delay(0.5F);
            printSeperator();

            if (patient.getPatientRoom() != null){
                System.out.println(patient.getName() + " reassigned to room " + roomNo + ", replacing " + room.getAssignedPatient().getName());
                patient.getPatientRoom().setAssignedPatient(null);
                pushRecord(patient.getHistoryLogs(),
                        "Patient, " + patient.getName() + "(" + patient.getPatientId() + ") reassigned to room: " + roomNo);
            } else {
                System.out.println(patient.getName() + " assigned to room " + roomNo);
                pushRecord(patient.getHistoryLogs(),
                        "Patient, " + patient.getName() + "(" + patient.getPatientId() + ") assigned to room: " + roomNo);
            }
        } else {
            if (patient.getPatientRoom() != null){
                System.out.println(patient.getName() + " reassigned to room " + roomNo);
                patient.getPatientRoom().setAssignedPatient(null);
                pushRecord(patient.getHistoryLogs(),
                        "Patient, " + patient.getName() + "(" + patient.getPatientId() + ") reassigned to room: " + roomNo);
            } else {
                System.out.println(patient.getName() + " assigned to room " + roomNo);
                pushRecord(patient.getHistoryLogs(),
                        "Patient, " + patient.getName() + "(" + patient.getPatientId() + ") assigned to room: " + roomNo);
            }
        }
        patient.setPatientRoom(room);
        room.setAssignedPatient(patient);
    }


    private static void showAvailableRooms(AdministrativeServer administrativeServer){
        System.out.println("Available Rooms");
        HospitalUtils.delay(0.3F);
        List<Object> list = new ArrayList<>();
        for (Room room : administrativeServer.getRoomList()){
            if (room.getAssignedPatient() == null){
                int roomNo = room.getRoomNo();
                list.add(roomNo);
            }
        }
        HospitalUtils.printSortingList(list,10,false);
        HospitalUtils.delay(1F);
    }

    public static void createTreatment(Patient currentPatient){
        Scanner scanner = new Scanner(System.in);
        List<String> medications = new ArrayList<>();
        printSeperator();
        HospitalUtils.listAdd(medications);
        printSeperator();
        boolean finishedList = false;
        while (!finishedList) {
            System.out.println("Would you like to make any changes to the given list?");
            System.out.println(medications);
            HospitalUtils.delay(0.2F);
            System.out.println("F - Finished,  A - Add,  D - Delete");
            switch (scanner.nextLine()) {
                case "F" -> {
                    finishedList = true;
                }
                case "A" -> {
                    printSeperator();
                    HospitalUtils.listAdd(medications);
                }
                case "D" -> {
                    printSeperator();
                    HospitalUtils.listDelete(medications);
                }
                default -> {
                    printSeperator();
                    HospitalUtils.delay(0.2F);
                }
            }
        }
        System.out.println("\n=== TREATMENT CREATED ===");
        System.out.println(medications);
        pushRecord(currentPatient.getHistoryLogs(),
                "Patient, " + currentPatient.getName() + "(" + currentPatient.getPatientId() + ") assigned with \n" +
                        medications + " for medications");
    }

    public static void assignStaff(AdministrativeServer administrativeServer, Room selectedRoom){
        Scanner scanner = new Scanner(System.in);
        boolean staffAddPass = false;
        while (!staffAddPass) {
            printSeperator();
            System.out.println("Enter staff name");
            String possibleName = scanner.nextLine();

            boolean passed = false;
            for (Employee employee : administrativeServer.getEmployeeList()) {
                passed = employee.getName().equals(possibleName);
                if (passed) {
                    staffAddPass = true;

                    if (employee.getRoomAssigned() != null){
                        employee.getRoomAssigned().setAssignedEmployee(null);
                        System.out.println(employee.getName() + " reassigned to room: " + selectedRoom.getRoomNo());
                    } else {
                        System.out.println(employee.getName() + " assigned to room: " + selectedRoom.getRoomNo());
                    }
                    selectedRoom.setAssignedEmployee(employee);
                    employee.setAssignedRoom(selectedRoom);
                    break;
                }
            }
            if (!passed) {
                System.out.println(possibleName + " is not in staff list, Please try again");
                HospitalUtils.delay(0.2F);
            }
        }
    }

    public static Patient patientAsk(AdministrativeServer administrativeServer){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter patient id");
            int patientId = scanner.nextInt();
            scanner.nextLine();

            for (Patient patient : administrativeServer.getPatientList()) {
                if (patient.getPatientId() == patientId) {
                    return patient;
                }
            }
        } catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException.getMessage() + " is not a valid input please try again.");
        }
        return null;
    }

    public static Room roomAsk(AdministrativeServer administrativeServer){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter room no.");
            int roomNo = scanner.nextInt();
            scanner.nextLine();

            for (Room room : administrativeServer.getRoomList()) {
                if (room.getRoomNo() == roomNo) {
                    return room;
                }
            }
        } catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException.getMessage() + " is not a valid input please try again.");
        }
        return null;
    }
}
