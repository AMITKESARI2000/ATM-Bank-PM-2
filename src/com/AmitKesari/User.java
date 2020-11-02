package com.AmitKesari;

import static com.AmitKesari.Main.atmMachine;
import static com.AmitKesari.Main.showMainMenu;


public class User implements MenuDrive {
    private int option;
    UserSchema userSchema = new UserSchema();

    User(UserSchema userSchema) {
        this.userSchema = userSchema;
    }

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

    @Override
    public void showMenu() {
        System.out.println("Choose your option:");

        String[] functions = new String[]{"Account Details", "Cash Withdrawal", "Cash Deposition", "Bank Statement Slip"
                , "Back", "Exit"};
        for (int i = 0; i < functions.length; i++) {
            System.out.println(i + 1 + ": " + functions[i]);
        }
        option = scanner.nextInt();
        functionInvoker(option);
    }

    @Override
    public void functionInvoker(int option) {
        switch (option) {
            //Add different functions below
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
