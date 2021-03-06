package com.AmitKesari;

import java.util.ArrayList;
import java.util.Date;

class UserTransaction {
    private String type = "Debit"; //Debit=nikalna
    private float transactionAmount = 0;
    private String transactionTime = new Date().toString();

    public UserTransaction(String type, float amtWithdraw, String date) {
        this.type = type;
        transactionAmount = amtWithdraw;
        date = transactionTime;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public String getType() {
        return type;
    }

    public String getTransactionTime() {
        return transactionTime;
    }
}

//Defines User Schema of all User Data
class UserSchema {
    private String userName = "";
    private String accNumber = "";
    private String accPassword = "";
    private String guardianName = "";
    private String address = "";
    private String mobile = "";
    private String IFSC = "";
    private String bankAcronym = "";
    private float accBalance = 0;
    private ArrayList<UserTransaction> userTransactionArrayList = new ArrayList<>(0);

    //For adding transactions of the customer in the list
    public void addUserTransactionArrayList(UserTransaction userTransaction) {
        userTransactionArrayList.add(userTransaction);
    }

    public ArrayList<UserTransaction> getUserTransactionArrayList() {
        return userTransactionArrayList;
    }

    //Default constructor
    public UserSchema() {

    }

    //Parametrised constructor for adding user data in User ArrayList
    public UserSchema(String userName, String accNumber, String accPassword, String guardianName,
                      String address, String mobile, String IFSC, float accBalance) {
        this.userName = userName;
        this.accNumber = accNumber;
        this.accPassword = accPassword;
        this.guardianName = guardianName;
        this.address = address;
        this.mobile = mobile;
        this.IFSC = IFSC;
        this.accBalance = accBalance;
        bankAcronym = IFSC.substring(0, 4);
    }

    //Setter Getter functions
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

    public String getBankAcronym() {
        return bankAcronym;
    }
}

public class UserData {

    static ArrayList<UserSchema> userArrayList = new ArrayList<>(0);
    static SqliteSetup sqliteSetup;

    //User Database Setting Up From SQL Database
    UserData() {
        sqliteSetup = new SqliteSetup();

        userArrayList = sqliteSetup.getsqliteArrayList();

//DUMMY DATA AND ENCRYPTION
    /*    final String secretKey = "secret";
        String encryptedString;
        String passwordString = "12345";

        PasswordSystem passwordSystem = new PasswordSystem();
        encryptedString = passwordSystem.encrypt(passwordString, secretKey);
        String decryptedString = passwordSystem.decrypt(encryptedString, secretKey);
*/
        /*userArrayList.add(new UserSchema(
                "Anurodh", "90141", encryptedString, "Mr JM Dubey",
                "F-5,Hindalco Colony", "9816123794", "GLAM0001612", 100000
        ));
        userArrayList.add(new UserSchema(
                "A1", "11111", encryptedString, "Mr yroehT",
                "Sr-511,Hindalco Colony", "9516123744", "IITT0001612", 253200
        ));
        userArrayList.add(new UserSchema(
                "A2", "11112", encryptedString, "Mr taCypoC",
                "HH-17,Hindalco Colony", "8116128194", "SDBN0001612", 640800
        ));
        userArrayList.add(new UserSchema(
                "A3", "11111", passwordSystem.encrypt("12345", secretKey), "Mr msiraigalP",
                "E-1,Hindalco Colony", "7116123794", "SRMI0001612", 700
        ));
        userArrayList.add(new UserSchema(
                "Ashutosh", "11100", passwordSystem.encrypt("00000", secretKey), "Mr retaehC",
                "D-121,Hindalco Colony", "90016523794", "SRMI0001612", 700
        ));
        userArrayList.add(new UserSchema(
                "Amit", "20341", passwordSystem.encrypt("12345", secretKey), "Mr Subhash",
                "JR-49,Hindalco Colony", "9616773794", "IITB0001612", 10000000
        ));*/
    }
}
