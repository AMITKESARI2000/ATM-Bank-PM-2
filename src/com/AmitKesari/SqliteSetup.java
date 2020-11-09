package com.AmitKesari;

import java.sql.*;
import java.util.ArrayList;

public class SqliteSetup {
    static private ArrayList<UserSchema> sqliteArrayList = new ArrayList<>();

    SqliteSetup() {
        createNewTable();

        //For Inserting New Data
//        insertDataRow("Amit", "90141", "12345", "Mr JM Dubey",
//                "F-5,Hindalco Colony", "9816123794", "GLAM0001612", 100000);

        selectAllData();
    }

    public static DatabaseMetaData meta;

    //Establish connection with database
    private static Connection connectDatabase() {
        Connection tryConnect = null;
        String dbPath = "src/com/AmitKesari/db/userdatabase.db";
        String url = "jdbc:sqlite:" + dbPath;
        try {
            tryConnect = DriverManager.getConnection(url);
            if (tryConnect != null) {
                meta = tryConnect.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error Connecting To Database.");
        }
//        finally {
//            try {
//                if (tryConnect != null) {
//                    tryConnect.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
        return tryConnect;
    }

    //Creation Of Table Having Fields Of UserData
    public static void createNewTable() {
        Connection tryConnect = connectDatabase();
        String QUERY = "CREATE TABLE IF NOT EXISTS userDetailsATM (\n"
                + " id integer PRIMARY KEY,\n"
                + " userName text NOT NULL,\n"
                + " accNumber text NOT NULL, \n"
                + " accPassword text NOT NULL, \n"
                + " guardianName text, \n"
                + " address text, \n"
                + " mobile text , \n"
                + " IFSC text NOT NULL, \n"
                + " accBalance real NOT NULL\n"
                + ");";

        try {
            Statement statementSQL = null;
            statementSQL = tryConnect.createStatement();
            statementSQL.execute(QUERY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Insert Data Back Into Database
    public void insertDataRow(String userName, String accNumber, String accPassword, String guardianName,
                              String address, String mobile, String IFSC, float accBalance) {
        String QUERY = "INSERT INTO userDetailsATM" +
                "(userName, accNumber,accPassword,guardianName,address,mobile,IFSC,accBalance)" +
                " VALUES(?,?,?,?,?,?,?,?)";

        try {
            Connection tryConnect = this.connectDatabase();
            PreparedStatement pstmt = tryConnect.prepareStatement(QUERY);
            pstmt.setString(1, userName);
            pstmt.setString(2, accNumber);
            pstmt.setString(3, accPassword);
            pstmt.setString(4, guardianName);
            pstmt.setString(5, address);
            pstmt.setString(6, mobile);
            pstmt.setString(7, IFSC);
            pstmt.setFloat(8, accBalance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Selecting All data from database
    public void selectAllData() {
        String QUERY = "SELECT * FROM userDetailsATM";

        try {
            Connection tryConnect = this.connectDatabase();
            Statement statementSQL = tryConnect.createStatement();
            ResultSet returnedQuery = statementSQL.executeQuery(QUERY);

            // Loop through the result set
            while (returnedQuery.next()) {
                /*System.out.println(returnedQuery.getInt("id") + "\t" +
                        returnedQuery.getString("userName") + "\t" +
                        returnedQuery.getString("accNumber") + "\t" +
                        returnedQuery.getString("accPassword") + "\t" +
                        returnedQuery.getString("guardianName") + "\t" +
                        returnedQuery.getString("address") + "\t" +
                        returnedQuery.getString("mobile") + "\t" +
                        returnedQuery.getString("IFSC") + "\t" +
                        returnedQuery.getFloat("accBalance"));*/
                final String secretKey = "secret";

                String passwordString = returnedQuery.getString("accPassword");

                PasswordSystem passwordSystem = new PasswordSystem();
                String encryptedString = passwordSystem.encrypt(passwordString, secretKey);
                sqliteArrayList.add(new UserSchema(
                        returnedQuery.getString("userName"), returnedQuery.getString("accNumber"),
                        encryptedString, returnedQuery.getString("guardianName"),
                        returnedQuery.getString("address"), returnedQuery.getString("mobile"),
                        returnedQuery.getString("IFSC"), returnedQuery.getFloat("accBalance")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<UserSchema> getsqliteArrayList() {
        return sqliteArrayList;
    }
}
