package com.AmitKesari;

import java.util.Date;

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
    boolean balanceSlipDispenser();
}
