package main;

import java.util.*;

import static main.HospitalUtils.printSortingList;

public class AdministrativeServer {

    private ArrayList<Employee> employeeList = new ArrayList<>();

    private ArrayList<Patient> patientList = new ArrayList<>();

    private ArrayList<Room> roomList = new ArrayList<>();

    private PriorityQueue<Patient> admissionQueue = new PriorityQueue<>();

    public PriorityQueue<Patient> getAdmissionQueue(){
        return admissionQueue;
    }

    public ArrayList<Employee> getEmployeeList(){
        return employeeList;
    }

    public ArrayList<Patient> getPatientList(){
        return patientList;
    }

    public ArrayList<Room> getRoomList(){
        return roomList;
    }

    public void addToQueue(Patient patient){
        admissionQueue.add(patient);
    }

    public void viewQueue(AdministrativeServer administrativeServer){
        List<Object> list = new ArrayList<>();
        for (Patient patient : administrativeServer.getAdmissionQueue()){
            if (patient.getPatientRoom() == null){
                String emergencyText = patient.isPriority() ? "EMERGENCY: " : "";
                list.add(emergencyText + patient.getName() + "(" + patient.getPatientId() + ")");
            }
        }
        if (list.isEmpty()){
            System.out.println("[Admission Queue] No patients waiting.");
        }
        printSortingList(list,10);
    }


}
