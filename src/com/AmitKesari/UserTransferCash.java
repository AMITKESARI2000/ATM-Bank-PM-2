package com.AmitKesari;

import java.util.Date;
import java.util.Scanner;

import static com.AmitKesari.Main.bankNameIITT;
import static com.AmitKesari.Main.showMainMenu;
import static com.AmitKesari.UserData.userArrayList;

public class UserTransferCash extends ATMMachine implements MenuDrive {
    UserSchema userSchema = new UserSchema();

    //Bring User Data from Database
    UserTransferCash(UserSchema userSchema) {
        this.userSchema = userSchema;
    }

    boolean ACACCashTransfer() {
        Scanner scanner = new Scanner(System.in);
        boolean isUserExist = false;
        System.out.println("Enter Account Number Of The User To Which You Want To Transfer: ");
        String ipAccNumber = scanner.next();
        System.out.println("Enter User's IFSC code: ");
        String ipIFSC = scanner.next();
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getAccNumber().equals(ipAccNumber) &&
                    userArrayList.get(i).getIFSC().equals(ipIFSC)) {
                isUserExist = true;
                User transferUser = new User(userArrayList.get(i));
                System.out.println("Enter Amount You want to transfer: ");

                float ipAmount = Float.parseFloat(keypadIP());
                if (!userSchema.getBankAcronym().equals(ipIFSC.substring(0, 4))) {
                    System.out.println("Different Bank Account Detected. Charging 2% Processing Fee");
                    ipAmount += 0.02 * ipAmount;
                }
                if (ipAmount < userSchema.getAccBalance()) {
                    System.out.println("Transferring " + (ipAmount - 0.02 * ipAmount) + " from " +
                            userSchema.getAccNumber() + " to " + ipAccNumber + " using");
                    transferUser.userSchema.setAccBalance(transferUser.userSchema.getAccBalance() + ipAmount);
                    transferUser.userSchema.addUserTransactionArrayList(new UserTransaction("A/C-A/C Credit", ipAmount, new Date().toString()));
                    userSchema.setAccBalance((userSchema.getAccBalance() - ipAmount));
                    userSchema.addUserTransactionArrayList(new UserTransaction("A/C-A/C Debit", ipAmount, new Date().toString()));
                    return true;
                } else {
                    System.out.println("You Don't Have Enough Amount In Your Balance.");
                    return false;
                }
            }
        }
        if (!isUserExist) {
            System.out.println("No Account Registered By The Account Entered. ");
            return false;
        }
        return false;
    }

    boolean internationalTransfer() {
        Scanner scanner = new Scanner(System.in);
        boolean isUserExist = false;
        System.out.println("Enter International Account Number To Which You Want To Transfer: ");
        String ipAccNumber = scanner.next();
        System.out.println("Enter User's IFSC code: ");
        String ipIFSC = scanner.next();
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getAccNumber().equals(ipAccNumber) &&
                    userArrayList.get(i).getIFSC().equals(ipIFSC)) {
                isUserExist = true;
                User transferUser = new User(userArrayList.get(i));
                System.out.println("Enter Amount You want to transfer: ");
                System.out.println("(NOTE: Processing fees is 30%)");
                float ipAmount = Float.parseFloat(keypadIP());
                ipAmount += 0.3 * ipAmount;
                if (ipAmount < userSchema.getAccBalance()) {
                    System.out.println("Transferring " + getCurrencyType() + " " + (ipAmount - 0.3 * ipAmount) + " from " +
                            userSchema.getAccNumber() + " to " + ipAccNumber + " using");
                    transferUser.userSchema.setAccBalance(transferUser.userSchema.getAccBalance() + ipAmount);
                    transferUser.userSchema.addUserTransactionArrayList(new UserTransaction("International Credit", ipAmount, new Date().toString()));
                    userSchema.setAccBalance((userSchema.getAccBalance() - ipAmount));
                    userSchema.addUserTransactionArrayList(new UserTransaction("International Debit", ipAmount, new Date().toString()));
                    return true;
                } else {
                    System.out.println("You Don't Have Enough Amount In Your Balance.");
                    return false;
                }
            }
        }
        if (!isUserExist) {
            System.out.println("No Account Registered By The Account Entered. ");
            return false;
        }
        return false;
    }

    private int option = 1;

    //Shows Menu
    @Override
    public void showMenu() {
        System.out.println("Current User:" + userSchema.getUserName());
        System.out.println("Choose your option:");
        Scanner scanner = new Scanner(System.in);
        String[] functions = new String[]{"A/C to A/C Transfer", "International Transfer", "Back", "Exit"};
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
                ACACCashTransfer();
                showMenu();
                break;
            }
            case 2: {
                internationalTransfer();
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
