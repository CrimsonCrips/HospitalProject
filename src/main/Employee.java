package main;

public class Employee extends Person {

   private String password;
   private Room assignedRoom;


    public String getPassword() {
        return password;
    }

    public Room getRoomAssigned(){
        return assignedRoom;
    }

    public void setPassword(String val) {
        password = val;
    }

    public void setAssignedRoom(Room val) {
        assignedRoom = val;
    }

    @Override
    boolean isAssigned() {
        return getRoomAssigned() != null;
    }
}
