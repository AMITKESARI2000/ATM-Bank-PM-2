package com.AmitKesari;

import static com.AmitKesari.Main.showMainMenu;

public class ATMMachine implements ATMDisplay {
    private final String ATMID = "IITT_001_enihcam";
    private double reserveDailyAmount = 10e7;
    private double reserveDailyAmountUser = 50000;
    private int paperRoll = 1000;
    private float ambientTemp = 28;
    private String currencyType = "Rupee";
    private float energyConsumeKWH = (float) 0.6;

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

    @Override
    public String keypadIP() {
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
            System.out.println("Exception. Only use Numbers of Keypad. ");

        }
        ipString = "0";
        return ipString;

    }

    @Override
    public boolean cashDispenser(UserSchema userSchema) {
        float amtWithdraw = 0;
        System.out.println("Enter the amount you want to Withdraw: ");
        amtWithdraw = Float.parseFloat(keypadIP());
        if (amtWithdraw > 0) {
            if (amtWithdraw <= userSchema.getAccBalance()) {
                if (amtWithdraw >= reserveDailyAmount || amtWithdraw >= reserveDailyAmountUser) {
                    System.out.println("Limit exceeded!");
                    amtWithdraw = 0;
                } else
                    System.out.println("Withdrawing Cash... Collect Cash from Slot Please");
                userSchema.setAccBalance((userSchema.getAccBalance() - amtWithdraw));
                reserveDailyAmount -= amtWithdraw;
                System.out.println(currencyType + " " + amtWithdraw + " withdrawn.");
                return true;
            } else {
                System.out.println("You are trying to withdraw amount greater\nthan what you have in your account. Cancelling...");
                return false;
            }
        }
        System.out.println("Nothing happened and changed.");
        return false;
    }

    public boolean cashDepositor(UserSchema userSchema) {
        float amtDeposit = 0;
        System.out.println("Enter the amount you want to Deposit: ");
        amtDeposit = Float.parseFloat(keypadIP());
        if (amtDeposit != 0) {
            if (amtDeposit >= 0) {
                System.out.println("Depositing Cash... Deposit Cash In The Slot Please");
                userSchema.setAccBalance((userSchema.getAccBalance() + amtDeposit));
                reserveDailyAmount += amtDeposit;
                System.out.println("Rs." + amtDeposit + " deposited.");
                return true;
            } else {
                System.out.println("You are trying to Deposit invalid amount. Cancelling...");
                return false;
            }
        }
        System.out.println("Nothing happened and changed.");
        return false;
    }

    @Override
    public boolean balanceSlipDispenser() {
        if (paperRoll > 0) {
            System.out.println("Collect the Balance Slip Please");
            paperRoll--;
            return true;
        }
        System.out.println("Paper Roll is finished. Please use account display only. Sorry for inconvenience caused.");
        return false;

    }


    private int option = 1;

    @Override
    public void showMenu() {
        System.out.println("Menu");
    }

    @Override
    public void functionInvoker(int option) {
        System.out.println("Invoke "+option);
    }
}
