package main;

public class Room  {

   private Patient assignedPatient;

   private Employee assignedEmployee;

   private final int roomNo;

   public Room(int var){
       roomNo = var;
   }

    public Patient getAssignedPatient() {
        return assignedPatient;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setAssignedEmployee(Employee var) {
        assignedEmployee = var;
    }

    public void setAssignedPatient(Patient var) {
        assignedPatient = var;
    }

    public boolean isOccupied(){
        return assignedPatient != null;
    }
}
