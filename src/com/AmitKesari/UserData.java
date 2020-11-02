package com.AmitKesari;

import java.util.ArrayList;

//Defines User Schema of all User Data
class UserSchema {
    private String userName = "";
    private String accNumber = "";
    private String accPassword = "";
    private String guardianName = "";
    private String address = "";
    private String mobile = "";
    private String IFSC = "";
    private float accBalance = 0;

    //Default constructor
    public UserSchema() {

    }

    //Parametrised constructor
    public UserSchema(String userName, String accNumber, String accPassword, String guardianName,
                      String address, String mobile, String IFSC, long accBalance) {
        this.userName = userName;
        this.accNumber = accNumber;
        this.accPassword = accPassword;
        this.guardianName = guardianName;
        this.address = address;
        this.mobile = mobile;
        this.IFSC = IFSC;
        this.accBalance = accBalance;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public String getAddress() {
        return address;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getIFSC() {
        return IFSC;
    }

    public float getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(float accBalance) {
        this.accBalance = accBalance;
    }
}

//User Database
public class UserData {

    static ArrayList<UserSchema> userArrayList = new ArrayList<>(0);

    UserData() {
        userArrayList.add(new UserSchema(
                "Anurodh", "90141", "12345", "Mr JM Dubey",
                "F-5,Hindalco Colony", "9816123794", "IITTN0001612", 100000
        ));
        userArrayList.add(new UserSchema(
                "A1", "11111", "12345", "Mr yroehT",
                "Sr-511,Hindalco Colony", "9516123744", "IITTN0001612", 253200
        ));
        userArrayList.add(new UserSchema(
                "A2", "1111", "12345", "Mr taCypoC",
                "HH-17,Hindalco Colony", "8116128194", "IITTN0001612", 640800
        ));
        userArrayList.add(new UserSchema(
                "A3", "11111", "12345", "Mr msiraigalP",
                "E-1,Hindalco Colony", "7116123794", "IITTN0001612", 700
        ));
        userArrayList.add(new UserSchema(
                "Amit", "20341", "12345", "Mr Subhash",
                "JR-49,Hindalco Colony", "9616773794", "IITTN0001612", 10000000
        ));
    }
}
