package com.AmitKesari;

import java.util.Date;
import java.util.Scanner;

import static com.AmitKesari.UserData.userArrayList;

public class UserTransferCash extends ATMMachine implements MenuDrive {
    UserSchema userSchema = new UserSchema();

    //Bring User Data from Database
    UserTransferCash(UserSchema userSchema) {
        this.userSchema = userSchema;
    }

    boolean ACACCashTransfer() {
        Scanner scanner = new Scanner(System.in);
        double processingFee = 0;
        boolean isUserExist = false;
        System.out.println("Enter Account Number Of The User To Which You Want To Transfer: ");
        String ipAccNumber = scanner.next();
        System.out.println("Enter User's IFSC code: ");
        String ipIFSC = scanner.next();
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getAccNumber().equals(ipAccNumber) &&
                    userArrayList.get(i).getIFSC().equals(ipIFSC)) {
                isUserExist = true;
                UserSchema transferUserSchema = userArrayList.get(i);
                System.out.println("Enter Amount You want to transfer: ");

                float ipAmount = Float.parseFloat(keypadIP());
                if (!userSchema.getBankAcronym().equals(ipIFSC.substring(0, 4))) {
                    processingFee = 0.02 * ipAmount;
                    System.out.print("Different Bank Account Detected. Charging 2% Processing Fee Rs.");
                    System.out.printf("%-20.2f\n", processingFee);
                }
                ipAmount += processingFee;
                if (OTPGeneration()) {
                    if (ipAmount < userSchema.getAccBalance()) {
                        System.out.println("Transferring " + (long) (ipAmount - processingFee) + " from A/C " +
                                userSchema.getAccNumber() + " to A/C " + ipAccNumber + " Using Secure Server");
                        transferUserSchema.setAccBalance((float) (transferUserSchema.getAccBalance() + ipAmount - processingFee));
                        transferUserSchema.addUserTransactionArrayList(new UserTransaction("AC-AC Credit", ipAmount, new Date().toString()));
                        userSchema.setAccBalance((userSchema.getAccBalance() - ipAmount));
                        userSchema.addUserTransactionArrayList(new UserTransaction("AC-AC Debit", ipAmount, new Date().toString()));
                        return true;
                    } else {
                        System.out.println("You Don't Have Enough Amount In Your Balance.");
                        return false;
                    }
                } else {
                    System.out.println("OTP not verified! Try Again.");
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
        double processingFee = 0;
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
                processingFee = 0.3 * ipAmount;
                System.out.println("Charging 30% Processing Fee Rs." + processingFee);
                ipAmount += processingFee;
                if (OTPGeneration()) {
                    if (ipAmount < userSchema.getAccBalance()) {
                        System.out.println("Transferring " + getCurrencyType() + " " + (ipAmount - processingFee) + " from A/C " +
                                userSchema.getAccNumber() + " to A/C " + ipAccNumber + " Using Secure Server");
                        transferUser.userSchema.setAccBalance((float) (transferUser.userSchema.getAccBalance() + ipAmount - processingFee));
                        transferUser.userSchema.addUserTransactionArrayList(new UserTransaction("International Credit", ipAmount, new Date().toString()));
                        userSchema.setAccBalance((userSchema.getAccBalance() - ipAmount));
                        userSchema.addUserTransactionArrayList(new UserTransaction("International Debit", ipAmount, new Date().toString()));
                        return true;
                    } else {
                        System.out.println("You Don't Have Enough Amount In Your Balance.");
                        return false;
                    }
                } else {
                    System.out.println("OTP not verified! Try Again.");
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

    boolean foreignExchange() {
        System.out.println("Welcome To Foreign Exchange. Hope You Have A Good Trip Abroad.");
        System.out.printf("You currently have: %.2f %s", userSchema.getAccBalance(), getCurrencyType());
        Scanner scanner = new Scanner(System.in);
        double processingFee = 0;
        int currencyOption = 1;

        System.out.println("Currency type you want to exchange it to:");
        String[] currencies = new String[]{"Indian Rupee(INR): 100", "Australian Dollar(AUD): 1.85", "Chinese Yuan(CNY): 8.89",
                "Euro(EUR): 1.14", "Pound Sterling(GBP): 1.01", "Japanese Yen(JPY): 141.66", "US Dollar (USD): 1.34"};
        for (int i = 0; i < currencies.length; i++) {
            System.out.println(i + 1 + ": " + currencies[i]);
        }
        currencyOption = scanner.nextInt();
        double[] rateFactors = new double[]{1.0, 0.0185, 0.0889, 0.0114, 0.0101, 1.4166, 0.0134};
        double rateFactor = 1;
        if (currencyOption <= currencies.length) rateFactor = rateFactors[currencyOption - 1];
        else System.out.println("Option Not Chosen Properly, Rate Factor Set to " + rateFactor);

        System.out.println("Enter Amount In INR To Be Converted: ");
        System.out.println("(NOTE: Processing fees is 25%)");
        float ipAmount = Float.parseFloat(keypadIP());
        processingFee = 0.25 * ipAmount;
        System.out.println("Charging 25% Processing Fee Rs." + processingFee);
        ipAmount += processingFee;
        if (OTPGeneration()) {
            if (ipAmount < userSchema.getAccBalance()) {
                double exchangedMoney = (float) ((ipAmount - processingFee) * rateFactor);
                System.out.printf("Please Collect Your (%s) Amount %.2f \n", currencies[currencyOption - 1], exchangedMoney);

                userSchema.setAccBalance((userSchema.getAccBalance() - ipAmount));
                userSchema.addUserTransactionArrayList(new UserTransaction("ForEx", ipAmount, new Date().toString()));
                return true;
            } else {
                System.out.println("You Don't Have Enough Amount In Your Balance.");
                return false;
            }
        } else {
            System.out.println("OTP not verified! Try Again.");
            return false;
        }

    }

    private int option = 1;

    //Shows Menu
    @Override
    public void showMenu() {
        System.out.println("Current User:" + userSchema.getUserName());
        System.out.println("Choose your option:");
        Scanner scanner = new Scanner(System.in);
        String[] functions = new String[]{"A/C to A/C Transfer", "International Transfer", "Foreign Exchange (Money Conversion)", "Back", "Exit"};
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
                foreignExchange();
                showMenu();
                break;
            }
            case 4: {
                new User(userSchema).showMenu();
                break;
            }
            case 5: {
                System.exit(0);
                break;
            }
            default:
                System.out.println("Choose correctly");
                showMenu();
        }
    }
}
