package com.AmitKesari;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        String yn = "y";

        //Menu For Displaying Options at the main screen
        do {
            int option = 0;
            System.out.println("Choose your option:");
            String[] functions = new String[]{"Bank Admin Dash", "Customer Dash", "Exit Program"};
            for (int i = 0; i < functions.length; i++) {
                System.out.println(i + 1 + ": " + functions[i]);
            }
            option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1: {
                    //For Bank purposes
                    System.out.println("ADMIN ACCESS");
                    if (bankAdmin.passwordVerifier("1234")) {
                        bankAdmin.showMenu();
                    } else {
                        System.out.println("Not Authorized!!!");
                    }
                    break;
                }
                case 2: {
                    //For User of ATM
                    System.out.println("Greetings Dear User 😊 ");

                    int i;
                    
                        System.out.println("Enter Username: ");
                        String userName = scanner.next();
                        //addddddddddddd

                    break;

                }
                case 3: {
                    //For Exit from Program
                    System.out.println(" Regards. Have a Great Day 😊");
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("Choose correctly");
                    showMainMenu();
            }


        } while (yn.equals("y"));

    }

    static BankAdmin bankAdmin = new BankAdmin();

    static final String bankNameIITT = "IITT Bank of Renukoot";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========Welcome To " + bankNameIITT + "==========");
        showMainMenu();

    }


}
