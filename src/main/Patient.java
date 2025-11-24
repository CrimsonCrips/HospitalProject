package main;

import java.util.LinkedList;

public class Patient extends Person {

    private int patientAge;
    private int patientId;
    private LinkedList<String> patientMedications = new LinkedList<>();

    private LinkedList<String> patientAllergies = new LinkedList<>();

    private LinkedList<String> patientConditions = new LinkedList<>();

    public void setPatientAge(int val) {
        patientAge = val;
    }

    public void setPatientId(int val) {
        patientId = val;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public long getPatientId() {
        return patientId;
    }

    public LinkedList<String> getPatientMedications() {
        return patientMedications;
    }

    public LinkedList<String> getPatientAllergies() {
        return patientAllergies;
    }

    public LinkedList<String> getPatientConditions() {
        return patientConditions;
    }
}
