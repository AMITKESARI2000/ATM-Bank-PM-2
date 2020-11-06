package com.AmitKesari;

import java.util.Date;
import java.util.Scanner;

public class ATMMachine implements ATMDisplay {
    private final String ATMID = "IITT_001_enihcam";
    private double reserveDailyAmount = 10e7;
    private double reserveDailyAmountUser = 50000;
    private int paperRoll = 1000;
    private float ambientTemp = 28;
    private String currencyType = "Rupee";
    private float energyConsumeKWH = (float) 0.6;
    private int balanceSlipCount = 3;

    ATMMachine() {
        msgDisplay();
    }

    public String getATMID() {
        return ATMID;
    }

    public double getReserveDailyAmount() {
        return reserveDailyAmount;
    }

    public float getAmbientTemp() {
        return ambientTemp;
    }

    public int getPaperRoll() {
        return paperRoll;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public float getEnergyConsumeKWH(float hr) {
        return energyConsumeKWH * hr;
    }

    //Display Keypad on screen
    @Override
    public String keypadIP() {
        Scanner scanner = new Scanner(System.in);
        String ipString = "0";
        System.out.println("7\t8\t9\t");
        System.out.println("4\t5\t6\t");
        System.out.println("1\t2\t3\t");
        System.out.println(".\t0\t-\t");
        System.out.println("Enter data using keypad: ");
        ipString = scanner.next();

        try {
            if (Float.parseFloat(ipString) > 0 || Float.parseFloat(ipString) <= 0) {
                System.out.println("Entered: " + ipString);
                return ipString;
            } else {
                System.out.println("Only use Numbers of Keypad.");

            }
        } catch (NumberFormatException e) {
            System.out.println("Exception Occurred. Only use Numbers of Keypad. ");
        }
        ipString = "0";
        return ipString;
    }

    //Dispenses cash to user
    @Override
    public boolean cashDispenser(UserSchema userSchema) {
        float amtWithdraw = 0;
        System.out.println("Enter the amount you want to Withdraw: ");
        amtWithdraw = Float.parseFloat(keypadIP());
        if (amtWithdraw > 0) {
            if (amtWithdraw <= userSchema.getAccBalance()) {
                if (OTPGeneration()) {
                    if (amtWithdraw >= reserveDailyAmount || amtWithdraw >= reserveDailyAmountUser) {
                        System.out.println("Limit exceeded!");
                        amtWithdraw = 0;
                    } else
                        System.out.println("Withdrawing Cash... Collect Cash from Slot Please");
                    userSchema.setAccBalance((userSchema.getAccBalance() - amtWithdraw));
                    reserveDailyAmount -= amtWithdraw;
                    System.out.println(currencyType + " " + amtWithdraw + " withdrawn.");
                    userSchema.addUserTransactionArrayList(new UserTransaction("Debit", amtWithdraw, new Date().toString()));
                    return true;

                } else {
                    System.out.println("OTP not verified! Try Again.");
                    return false;
                }
            } else {
                System.out.println("You are trying to withdraw amount greater\nthan what you have in your account. Cancelling...");
                return false;
            }
        }
        System.out.println("Nothing happened and changed.");
        return false;
    }

    //Deposits cash into user account
    public boolean cashDepositor(UserSchema userSchema) {
        float amtDeposit = 0;
        System.out.println("Enter the amount you want to Deposit: ");
        amtDeposit = Float.parseFloat(keypadIP());
        try {
            if (OTPGeneration()) {
                if (amtDeposit >= 0) {
                    System.out.println("Depositing Cash... Deposit Cash In The Slot Please");
                    userSchema.setAccBalance((userSchema.getAccBalance() + amtDeposit));
                    reserveDailyAmount += amtDeposit;
                    System.out.println("Rs." + amtDeposit + " deposited.");
                    userSchema.addUserTransactionArrayList(new UserTransaction("Credit", amtDeposit, new Date().toString()));
                    return true;
                } else {
                    System.out.println("You are trying to Deposit invalid amount. Cancelling...");
                    return false;
                }
            } else {
                System.out.println("OTP not verified! Try Again.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Nothing happened and changed.");
            return false;
        }
    }

    //Prints balance slip for user
    @Override
    public boolean balanceSlipDispenser(UserSchema userSchema) {
        if (paperRoll > 0) {
            System.out.println("Collect The Balance Slip Please. Save Paper Save Nature.");
            System.out.printf("%-20s%-20s\n", "User Name: ", userSchema.getUserName());
            System.out.printf("%-20s%-20s\n", "Account Number: ", userSchema.getAccNumber());
            System.out.printf("%-20s%-20.2f\n", "Account Balance: Rs.", userSchema.getAccBalance());
            System.out.println("Date Printed: " + new Date().toString());
            System.out.println("Last (max-" + balanceSlipCount + ") transactions:");
            int cnt = balanceSlipCount - 1;
            for (int i = userSchema.getUserTransactionArrayList().size() - 1; i > 0 && cnt > 0; i--, cnt--) {
                System.out.println((balanceSlipCount - cnt) + ": ");
                System.out.printf("%-20s%-20s\n", "Transaction Type: ", userSchema.getUserTransactionArrayList().get(i).getType());
                System.out.printf("%-20s%-20.2f\n", "Amount changed: Rs.", userSchema.getUserTransactionArrayList().get(i).getTransactionAmount());
                System.out.printf("%-20s%-20s\n", "Time: ", userSchema.getUserTransactionArrayList().get(i).getTransactionTime());
            }
            paperRoll--;
            return true;
        }
        System.out.println("Paper Roll Is Finished. Please use account display only. Sorry for inconvenience caused.");
        return false;
    }

    private int option = 1;

    @Override
    public void showMenu() {
        System.out.println("Menu");
    }

    @Override
    public void functionInvoker(int option) {
        System.out.println("Invoke " + option);
    }
}
