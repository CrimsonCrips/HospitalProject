package main;

import java.util.ArrayList;
import java.util.Scanner;

import static main.HospitalUtils.*;


public class PatientPayment extends AdministrativeServer {

    public static void patientPay(AdministrativeServer administrativeServer) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Patient> patientList = administrativeServer.getPatientList();
        printSeperator();
        System.out.println("Patient Payment");
        HospitalUtils.delay(0.2F);
        boolean payPass = false;
        Patient chosenPatient = null;
        while (!payPass){
            printSeperator();
            System.out.println("Input patient's name/ID");
            String input = scanner.nextLine();
            for (Patient patient : patientList) {
                long patientId = patient.getPatientId();
                if (patient.getName().contains(input) || Long.toString(patientId).equals(input)) {
                    chosenPatient = patient;
                    payPass = true;
                }
            }
        }
        System.out.println("Patient " + chosenPatient.getName() + "'s current bill is: " + chosenPatient.getBill());
        delay(1);
        System.out.println("Pay bill?");
        boolean payQuestion = false;
        while (!payQuestion) {
            printSeperator();
            switch (HospitalInterface.yesOrNoDeterminer(scanner.nextLine())) {
                case 1 -> {
                    pushRecord(chosenPatient.getHistoryLogs(),
                            "Patient, " + chosenPatient.getName() + "(" + chosenPatient.getPatientId() + ") paid for bill of: " + chosenPatient.getBill());
                    chosenPatient.setBill(0);
                    payQuestion = true;
                }
                case 0 -> {
                    payQuestion = true;
                }
                case -1 -> {
                    System.out.println("Unknown response,please try again");
                }
            }
        }
    }

}
