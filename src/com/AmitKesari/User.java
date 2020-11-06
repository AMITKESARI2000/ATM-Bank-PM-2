package com.AmitKesari;

import java.util.Scanner;

import static com.AmitKesari.Main.*;


public class User implements MenuDrive {
    private int option;
    UserSchema userSchema = new UserSchema();

    //Bring User Data from Database
    User(UserSchema userSchema) {
        this.userSchema = userSchema;
    }

    //Display User Details on screen
    void displayCurrentUserDetails() {
        System.out.println("Details: ");

        System.out.printf("%-20s%-20s\n", "User Name: ", userSchema.getUserName());
        System.out.printf("%-20s%-20s\n", "Account Number: ", userSchema.getAccNumber());
        System.out.printf("%-20s%-20.2f\n", "Account Balance: Rs.", userSchema.getAccBalance());
        System.out.printf("%-20s%-20s\n", "Guardian Name: ", userSchema.getGuardianName());
        System.out.printf("%-20s%-20s\n", "Address: ", userSchema.getAddress());
        System.out.printf("%-20s%-20s\n", "Mobile: ", userSchema.getMobile());
        System.out.printf("%-20s%-20s\n", "IFSC: ", userSchema.getIFSC());
        System.out.println();
    }

    //Change password of user
    void changeAccPassword() {
        System.out.println("Confirm Current PIN First ");
//        String tmpPassword= scanner.next();
        if (passwordSystem.passwordVerifier(userSchema.getAccPassword())) {
            System.out.println("Enter New 5 Digit PIN: ");
            String tmpPassword1 =atmMachine.keypadIP();
            System.out.println("Confirm New PIN By Typing It Again: ");
            String tmpPassword2 = atmMachine.keypadIP();
            if (tmpPassword1.equals(tmpPassword2)) {
                if(tmpPassword1.length()==5) {
                    System.out.println("✔PIN Changed");
                    userSchema.setAccPassword(tmpPassword1);
                }else{
                    System.out.println("❌Too Long PIN. PIN Not Changed.");
                }
            } else {
                System.out.println("❌PIN Did Not Match. PIN Not Changed.");
            }

        }
    }

    //Shows Menu
    @Override
    public void showMenu() {
        System.out.println("Current User:"+userSchema.getUserName());
        System.out.println("Choose your option:");
        Scanner scanner = new Scanner(System.in);
        String[] functions = new String[]{"Account Details", "Cash Withdrawal", "Cash Deposition",
                "Bank Statement Slip", "Change PIN", "Back", "Exit"};
        for (int i = 0; i < functions.length; i++) {
            System.out.println(i + 1 + ": " + functions[i]);
        }
        option = scanner.nextInt();
        functionInvoker(option);
    }

    //Switches function according to option chosen
    @Override
    public void functionInvoker(int option) {
        switch (option) {
            case 1: {
                displayCurrentUserDetails();
                showMenu();
                break;
            }

            case 2: {
                if (atmMachine.cashDispenser(userSchema)) {
                    System.out.println("✔Done");
                } else System.out.println("❌Error");
                showMenu();
                break;
            }
            case 3: {
                if (atmMachine.cashDepositor(userSchema)) {
                    System.out.println("✔Done");
                } else System.out.println("❌Error");
                showMenu();
                break;
            }
            case 4: {
                if (atmMachine.balanceSlipDispenser(userSchema)) {
                    System.out.println("✔Done");
                }
                showMenu();
                break;
            }
            case 5: {
                changeAccPassword();
                showMenu();
            }
            case 6: {
                showMainMenu();
                break;
            }
            case 7: {
                System.exit(0);
                break;
            }
            default:
                System.out.println("Choose correctly");
                showMenu();
        }
    }
}
