package main;

abstract public class Person {
    String personName;

    public void setName(String val){
        personName = val;
    }

    public String getName(){
        return personName;
    }

    abstract boolean isAssigned();

}
