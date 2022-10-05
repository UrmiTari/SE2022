package org.example;

public class WarehouseManager {
    private int userID;
    private String name;
    private int age;
    private String BDAY;

    public WarehouseManager(int userID, String name, int age, String BDAY) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.BDAY = BDAY;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBDAY() {
        return BDAY;
    }

    public void setBDAY(String BDAY) {
        this.BDAY = BDAY;
    }
}
