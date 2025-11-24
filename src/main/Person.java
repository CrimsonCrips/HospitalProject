package main;

public class Person {
    String personName;
    String personContactNum;

    public void setContactNum(String val){
        personContactNum = val;
    }

    public void setName(String val){
        personName = val;
    }

    public String getContactNum(){
        return personContactNum;
    }

    public String getName(){
        return personName;
    }

}
