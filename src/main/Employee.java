package main;

import java.util.HashMap;
import java.util.LinkedList;

public class Employee extends Person {

   private String password;
   private int assignedRoomID = -1;


    public String getPassword() {
        return password;
    }

    public int getAssignedRoomID(){
        return assignedRoomID;
    }

    public void setPassword(String val) {
        password = val;
    }

    public void setAssignedRoomID(int val) {
        assignedRoomID = val;
    }

    public boolean isOccupied(){
        return assignedRoomID != -1;
    }
}
