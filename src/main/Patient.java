package main;

import java.util.LinkedList;
import java.util.List;

public class Patient extends Person implements Comparable<Patient> {

    private Room patientRoom;
    private int patientId;
    private boolean priority = false;
    private LinkedList<String> historyLogs = new LinkedList<>();
    private List<String> patientConditions = new LinkedList<>();
    private boolean present = false;
    private double bill = 0F;

    public Patient(int id, boolean priority){
        setPriority(priority);
        setPatientId(id);
    }

    public boolean isPresent(){
        return present;
    }

    public void setPresent(boolean val){
        present = val;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double getBill() {
        return bill;
    }

    public boolean isPriority() {
        return priority;
    }

    public LinkedList<String> getHistoryLogs(){
        return historyLogs;
    }

    public void setPatientRoom(Room val) {
        patientRoom = val;
    }

    public void setPatientId(int val) {
        patientId = val;
    }

    public void setPriority(boolean var) {
        priority = var;
    }

    public Room getPatientRoom() {
        return patientRoom;
    }

    public long getPatientId() {
        return patientId;
    }

    public List<String> getPatientConditions() {
        return patientConditions;
    }

    public void setPatientConditions(List<String> patientConditions) {
        this.patientConditions = patientConditions;
    }

    public int compareTo(Patient patient) {
        return compare(patient.priority, this.priority);
    }

    public static int compare(boolean x, boolean y) {
        return Boolean.compare(x, y);
    }

    @Override
    boolean isAssigned() {
        return getPatientRoom() != null;
    }
}
