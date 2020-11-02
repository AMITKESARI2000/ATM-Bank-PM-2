package com.AmitKesari;

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
        System.out.printf("%-20s%-20s\n", "Account Balance: Rs.", userSchema.getAccBalance());
        System.out.printf("%-20s%-20s\n", "Password (length): ", userSchema.getAccPassword().length());
        System.out.printf("%-20s%-20s\n", "Guardian Name: ", userSchema.getGuardianName());
        System.out.printf("%-20s%-20s\n", "Address: ", userSchema.getAddress());
        System.out.printf("%-20s%-20s\n", "Mobile: ", userSchema.getMobile());
        System.out.printf("%-20s%-20s\n", "IFSC: ", userSchema.getIFSC());
        System.out.println();
    }

    //Change password of user
    void changeAccPassword() {
        System.out.println("Confirm Current Password First ");
//        String tmpPassword= scanner.next();
        if (atmMachine.passwordVerifier(userSchema.getAccPassword())) {
            System.out.println("Enter New Password: ");
            String tmpPassword1 = scanner.next();
            System.out.println("Confirm New Password By Typing It Again: ");
            String tmpPassword2 = scanner.next();
            if (tmpPassword1.equals(tmpPassword2)) {
                System.out.println("✔Password Changed");
                userSchema.setAccPassword(tmpPassword1);
            } else {
                System.out.println("❌Password Did Not Match. Password Not Changed.");
            }

        }
    }

    //Shows Menu
    @Override
    public void showMenu() {
        System.out.println("Choose your option:");

        String[] functions = new String[]{"Account Details", "Cash Withdrawal", "Cash Deposition",
                "Bank Statement Slip", "Change Password", "Back", "Exit"};
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
                if (atmMachine.balanceSlipDispenser()) {
                    displayCurrentUserDetails();
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
