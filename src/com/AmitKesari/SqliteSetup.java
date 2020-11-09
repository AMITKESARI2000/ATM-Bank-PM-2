package com.AmitKesari;

import java.sql.*;
import java.util.ArrayList;

public class SqliteSetup {
    SqliteSetup() {
        createNewTable();

        //For Inserting New Data
        insert("Amit", "90141", "12345", "Mr JM Dubey",
                "F-5,Hindalco Colony", "9816123794", "GLAM0001612", 100000);

        selectAll();
    }

    static private ArrayList<UserSchema> sqliteArrayList = new ArrayList<>();

    private static Connection connect() {
        Connection conn = null;
        String dbPath = "src/com/AmitKesari/db/userdatabase.db";
        String url = "jdbc:sqlite:" + dbPath;
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
        return conn;
    }

    public static void createNewTable() {
        Connection conn = connect();
        String sql = "CREATE TABLE IF NOT EXISTS userDetailsATM (\n"
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
            Statement stmt = null;
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void insert(String userName, String accNumber, String accPassword, String guardianName,
                       String address, String mobile, String IFSC, float accBalance) {
        String sql = "INSERT INTO userDetailsATM" +
                "(userName, accNumber,accPassword,guardianName,address,mobile,IFSC,accBalance)" +
                " VALUES(?,?,?,?,?,?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
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

    public void selectAll() {
        String sql = "SELECT * FROM userDetailsATM";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                /*System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("userName") + "\t" +
                        rs.getString("accNumber") + "\t" +
                        rs.getString("accPassword") + "\t" +
                        rs.getString("guardianName") + "\t" +
                        rs.getString("address") + "\t" +
                        rs.getString("mobile") + "\t" +
                        rs.getString("IFSC") + "\t" +
                        rs.getFloat("accBalance"));*/
                final String secretKey = "secret";

                String passwordString = rs.getString("accPassword");

                PasswordSystem passwordSystem = new PasswordSystem();
                String encryptedString = passwordSystem.encrypt(passwordString, secretKey);
                sqliteArrayList.add(new UserSchema(
                        rs.getString("userName"), rs.getString("accNumber"),
                        encryptedString, rs.getString("guardianName"),
                        rs.getString("address"), rs.getString("mobile"),
                        rs.getString("IFSC"), rs.getFloat("accBalance")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<UserSchema> getsqliteArrayList() {
        return sqliteArrayList;
    }
}
