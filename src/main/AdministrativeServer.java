package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AdministrativeServer {

    private ArrayList<Employee> employeeList = new ArrayList<Employee>();

    private ArrayList<Patient> patientList = new ArrayList<Patient>();

    private LinkedHashMap<Integer,Patient> roomList = new LinkedHashMap<>();

    public ArrayList<Employee> getEmployeeList(){
        return employeeList;
    }

    public ArrayList<Patient> getPatientList(){
        return patientList;
    }

    public LinkedHashMap<Integer,Patient> getRoomList(){
        return roomList;
    }



}
