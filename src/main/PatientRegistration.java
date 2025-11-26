package main;

import java.util.Scanner;

import static main.HospitalUtils.pushRecord;
import static main.HospitalUtils.printSeperator;


public class PatientRegistration extends AdministrativeServer {

    public static void register(AdministrativeServer administrativeServer) {
        Patient patient = new Patient(administrativeServer.getPatientList().size() + 1,false);
        Scanner scanner = new Scanner(System.in);
        printSeperator();
        System.out.println("Patient Registration");
        HospitalUtils.delay(0.2F);
        System.out.println("Input patient's full name: ");
        patient.setName(scanner.nextLine());
        HospitalUtils.delay(0.2F);
        printSeperator();

        boolean conditionsPass = false;
        while (!conditionsPass) {
            System.out.println("Does the patient have any conditions?  Y/N?");
            switch (scanner.nextLine()) {
                case "Y" -> {
                    printSeperator();
                    HospitalUtils.listAdd(patient.getPatientConditions());
                    printSeperator();
                    boolean finishedList = false;
                    while (!finishedList) {
                        System.out.println("Would you like to make any changes to the given list?");
                        System.out.println(patient.getPatientConditions());
                        HospitalUtils.delay(0.2F);
                        System.out.println("F - Finished,  A - Add,  D - Delete");
                        switch (scanner.nextLine()) {
                            case "F" -> {
                                finishedList = true;
                            }
                            case "A" -> {
                                printSeperator();
                                HospitalUtils.listAdd(patient.getPatientConditions());
                            }
                            case "D" -> {
                                printSeperator();
                                HospitalUtils.listDelete(patient.getPatientConditions());
                            }
                            default -> {
                                printSeperator();
                                HospitalUtils.delay(0.2F);
                            }
                        }
                    }
                    conditionsPass = true;
                }
                case "N" -> {
                    conditionsPass = true;
                }
                default -> {
                    System.out.println("Unknown response, Please try again");
                    HospitalUtils.delay(0.2F);
                }
            }
        }
        printSeperator();
        boolean emergencyPass = false;
        while (!emergencyPass) {
            System.out.println("Is it an emergency?,  Y/N?");
            switch (scanner.nextLine()) {
                case "Y" -> {
                    patient.setPriority(true);
                    emergencyPass = true;
                }
                case "N" -> {
                    emergencyPass = true;
                }
                default -> {
                    System.out.println("Unknown response, Please try again");
                    HospitalUtils.delay(0.2F);
                }
            }
        }
        printSeperator();
        boolean presentAlready = false;
        Patient oldPatient = null;
        for (Patient patient1 : administrativeServer.getPatientList()){
            if (patient1.getName().equals(patient.getName())){
                presentAlready = true;
                oldPatient = patient1;
                break;
            }
        }

        administrativeServer.getAdmissionQueue().add(patient);
        if (!presentAlready){
            administrativeServer.getPatientList().add(patient);
            System.out.println("Patient, " + patient.getName() + "(" + patient.getPatientId() + ") successfully registered");
            pushRecord(patient.getHistoryLogs(),
                    "Patient, " + patient.getName() + "(" + patient.getPatientId() + ") registered. \n" +
                            "Conditions: " + patient.getPatientConditions());
            patient.setPresent(true);
        } else {
            oldPatient.getPatientConditions().add(patient.getPatientConditions().toString());
            oldPatient.setPriority(patient.isPriority());
            System.out.println("Patient, " + oldPatient.getName() + "(" + oldPatient.getPatientId() + ") successfully reregistered");
            pushRecord(oldPatient.getHistoryLogs(),
                    "Patient, " + oldPatient.getName() + "(" + oldPatient.getPatientId() + ") reregistered. \n" +
                            "Conditions: " + oldPatient.getPatientConditions());
            oldPatient.setPresent(true);
        }
        HospitalUtils.delay(1F);


    }

}
