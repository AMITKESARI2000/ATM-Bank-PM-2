package com.AmitKesari;

import java.util.ArrayList;

class UserSchema {
    private String userName = "";
    private String accNumber = "";
    private String accPassword = "";
    private String guardianName = "";
    private String address = "";
    private String mobile = "";
    private String IFSC = "";
    private float accBalance = 0;

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

    public UserSchema() {

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

public class UserData {

    static ArrayList<UserSchema> userArrayList = new ArrayList<>(0);

    UserData() {
        userArrayList.add(new UserSchema(
                "Amit", "20341314171", "1234", "papa",
                "JR-49,Hindalco Colony", "9616773794", "IITTN0001612", 1000000
        ));
        userArrayList.add(new UserSchema(
                "Anu", "90141724171", "1234", "papa",
                "F-5,Hindalco Colony", "9816123794", "IITTN0001612", 100000
        ));
        userArrayList.add(new UserSchema(
                "r", "39941724171", "12", "rrr",
                "Sr-511,Hindalco Colony", "7116123794", "IITTN0001612", 200000
        ));
    }
}
