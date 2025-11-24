package main;

import java.util.Scanner;

public class PatientRegistration extends AdministrativeServer {

    public static void register(AdministrativeServer administrativeServer) {
        Patient patient = new Patient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Patient Registration");
        System.out.println("####################");
        HospitalUtils.delay(0.2F);
        System.out.println("Input patient's full name: ");
        patient.setName(scanner.nextLine());
        HospitalUtils.delay(0.2F);
        System.out.println("Input patient's age:");
        patient.setPatientAge(scanner.nextInt());
        HospitalUtils.delay(0.2F);
        scanner.nextLine();
        System.out.println("Input patient's contact number:");
        patient.setContactNum(scanner.nextLine());
        HospitalUtils.delay(0.2F);
        System.out.println("Does the patient have any medication?  Y/N?");

        boolean medicationPass = false;
        while (!medicationPass) {
            switch (scanner.nextLine()) {
                case "Y" -> {
                    HospitalUtils.listAdd(patient.getPatientMedications());
                    boolean finishedList = false;
                    while (!finishedList) {
                        System.out.println("Would you like to make any changes to the given list?");
                        System.out.println(patient.getPatientMedications());
                        HospitalUtils.delay(0.2F);
                        System.out.println("F - Finished,  A - Add,  D - Delete");
                        switch (scanner.nextLine()) {
                            case "F" -> {
                                finishedList = true;
                            }
                            case "A" -> {
                                HospitalUtils.listAdd(patient.getPatientMedications());
                            }
                            case "D" -> {
                                HospitalUtils.listDelete(patient.getPatientMedications());
                            }
                            default -> {
                                System.out.println("Unknown response, Please try again");
                                HospitalUtils.delay(0.2F);
                            }
                        }
                    }
                    medicationPass = true;
                }
                case "N" -> {
                    medicationPass = true;
                }
                default -> {
                    System.out.println("Unknown response, Please try again");
                    HospitalUtils.delay(0.2F);
                    System.out.println("Does the patient have any medication?  Y/N?");
                }
            }
        }

        HospitalUtils.delay(0.2F);
        System.out.println("Does the patient have any medication?  Y/N?");


        boolean allergiesPass = false;
        while (!allergiesPass) {
            switch (scanner.nextLine()) {
                case "Y" -> {
                    HospitalUtils.listAdd(patient.getPatientAllergies());
                    boolean finishedList = false;
                    while (!finishedList) {
                        System.out.println("Would you like to make any changes to the given list?");
                        System.out.println(patient.getPatientAllergies());
                        HospitalUtils.delay(0.2F);
                        System.out.println("F - Finished,  A - Add,  D - Delete");
                        switch (scanner.nextLine()) {
                            case "F" -> {
                                finishedList = true;
                            }
                            case "A" -> {
                                HospitalUtils.listAdd(patient.getPatientAllergies());
                            }
                            case "D" -> {
                                HospitalUtils.listDelete(patient.getPatientAllergies());
                            }
                            default -> {
                                System.out.println("Unknown response, Please try again");
                                HospitalUtils.delay(0.2F);
                            }
                        }
                    }
                    allergiesPass = true;
                }
                case "N" -> {
                    allergiesPass = true;
                }
                default -> {
                    System.out.println("Unknown response, Please try again");
                    HospitalUtils.delay(0.2F);
                    System.out.println("Does the patient have any allergies?  Y/N?");
                }
            }
        }


        boolean conditionsPass = false;
        while (!conditionsPass) {
            switch (scanner.nextLine()) {
                case "Y" -> {
                    HospitalUtils.listAdd(patient.getPatientConditions());
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
                                HospitalUtils.listAdd(patient.getPatientConditions());
                            }
                            case "D" -> {
                                HospitalUtils.listDelete(patient.getPatientConditions());
                            }
                            default -> {
                                System.out.println("Unknown response, Please try again");
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
                    System.out.println("Does the patient have any allergies?  Y/N?");
                }
            }
        }

        administrativeServer.getPatientList().add(patient);

    }

}
