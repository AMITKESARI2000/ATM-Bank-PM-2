package com.AmitKesari;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public interface ATMDisplay extends MenuDrive {
    //Display welcome message
    default void msgDisplay() {
        Date date = new Date();
        System.out.println("\n\tGREETING\t");
        System.out.println("Date" + date.toString());

    }

    //Display Keypad on screen
    String keypadIP();

    //Dispenses cash to user
    boolean cashDispenser(UserSchema userSchema);

    //Deposits cash into user account
    boolean cashDepositor(UserSchema userSchema);

    //Prints balance slip for user
    boolean balanceSlipDispenser(UserSchema userSchema);

    //Generates and verifies 4 digit OTP
    default boolean OTPGeneration() {
        Random random = new Random();
        String otpGenerated = String.valueOf(random.nextInt(10000));
        System.out.println(otpGenerated + " OTP Generated. Enter This In ATM");
        System.out.println("Please Verify OTP: ");

        String ipOTP = keypadIP();
        if (ipOTP.equals(otpGenerated)) {
            System.out.println("✔Verified");
            return true;
        }
        return false;

    }
}
