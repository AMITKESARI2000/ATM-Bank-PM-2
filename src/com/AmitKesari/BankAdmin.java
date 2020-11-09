package com.AmitKesari;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import static com.AmitKesari.Main.showMainMenu;
import static com.AmitKesari.SqliteSetup.meta;
import static com.AmitKesari.UserData.userArrayList;
import static com.AmitKesari.Main.atmMachine;

public class BankAdmin extends PasswordSystem implements MenuDrive {

    private float upTimeHR = 24;

    //Initialise Bank Admin and User Data
    BankAdmin() {
        UserData userData = new UserData();

    }

    //Displays Data of all logged users
    public void displayUserData() {
        System.out.println("Details: ");
        for (int i = 0; i < userArrayList.size(); i++) {
            System.out.println(i + 1 + ": ");
            System.out.printf("%-20s%-20s\n", "User Name: ", userArrayList.get(i).getUserName());
            System.out.printf("%-20s%-20s\n", "Account Number: ", userArrayList.get(i).getAccNumber());
            System.out.printf("%-20s%-20s\n", "Guardian Name: ", userArrayList.get(i).getGuardianName());
            System.out.printf("%-20s%-20s\n", "Address: ", userArrayList.get(i).getAddress());
            System.out.printf("%-20s%-20s\n", "Mobile: ", userArrayList.get(i).getMobile());
            System.out.printf("%-20s%-20s\n", "IFSC: ", userArrayList.get(i).getIFSC());
            System.out.println();
        }
    }

    //Displays Data of all ATM
    public void displayATMDetails() {
        System.out.println("Details: ");
        System.out.printf("%-20s%-20s\n", "ATM ID: ", atmMachine.getATMID());
        System.out.printf("%-20s%-20s\n", "Currency Type: ", atmMachine.getCurrencyType());
        System.out.printf("%-20s%-20s\n", "Cash In Machine: ", atmMachine.getReserveDailyAmount());
        System.out.printf("%-20s%-20s\n", "Paper Rolls Left: ", atmMachine.getPaperRoll());
        System.out.printf("%-20s%-20s\n", "Energy EatUp (kWh): ", atmMachine.getEnergyConsumeKWH(upTimeHR));
        System.out.printf("%-20s%-20s\n", "Ambient Temp (C): ", atmMachine.getAmbientTemp());
        System.out.println("System Date:"+new Date().toString());
        try {
            System.out.println("The driver name is " + meta.getDriverName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println();
    }

    private int option = 1;

    @Override
    public void showMenu() {
        System.out.println("Choose Your Option:");
        Scanner scanner = new Scanner(System.in);
        String[] functions = new String[]{"Display All User Data", "ATM Machine Details", "Back", "Exit"};
        for (int i = 0; i < functions.length; i++) {
            System.out.println(i + 1 + ": " + functions[i]);
        }
        option = scanner.nextInt();
        functionInvoker(option);
    }

    @Override
    public void functionInvoker(int option) {
        switch (option) {
            case 1: {
                displayUserData();
                showMenu();
                break;
            }
            case 2: {
                displayATMDetails();
                showMenu();
                break;
            }
            case 3: {
                showMainMenu();
                break;
            }
            case 4: {
                System.exit(0);
                break;
            }
            default:
                System.out.println("Choose correctly");
                showMenu();
        }
    }
}
