package com.AmitKesari;

import java.util.Scanner;

import static com.AmitKesari.Main.bankNameIITT;

public interface MenuDrive {
    Scanner scanner = new Scanner(System.in);

    //Display menu items to screen
    void showMenu();

    //Takes in the option for switching particular functions
    void functionInvoker(int option);

    //Verifies the inputted password by checking three times
    default boolean passwordVerifier(String correctPassword) {
        String inputPassword = new String();
        int attempts = 0;
        final int allowedAttempts = 3;
        while (attempts <= allowedAttempts) {
            if (attempts == 0) {
                System.out.println("Enter the password");
                inputPassword = scanner.next();
                if (inputPassword.equals(correctPassword)) {
                    System.out.println("✔ Verified ");
                    return true;
                }
                attempts++;
            } else if (attempts == 1 || attempts == 2) {
                System.out.println("❌ Wrong password ! Enter the password again ( " + (allowedAttempts - attempts) + " remaining )");
                inputPassword = scanner.next();
                if (inputPassword.equals(correctPassword)) {
                    System.out.println("✔ Verified ");
                    return true;
                }
                attempts++;
            } else {
                System.out.println("You have been blocked by system. Please visit your nearest " + bankNameIITT + " to reset the password.");
                return false;
            }
        }
        return false;
    }
}
