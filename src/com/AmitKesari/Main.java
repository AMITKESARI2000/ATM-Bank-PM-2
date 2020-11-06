

package com.AmitKesari;

import java.util.Scanner;

import static com.AmitKesari.UserData.userArrayList;

public class Main  {
    static BankAdmin bankAdmin = new BankAdmin();
    private static final String correctAdminPassword = "1234";
    public static final String bankNameIITT = "IITT Bank of Renukoot";
    static ATMMachine atmMachine = new ATMMachine();

    static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        String yn = "y";

        //Menu For Displaying Options at the Main screen
        do {
            int option = 0;
            System.out.println("Main Menu. Choose Your Option:");
            String[] functions = new String[]{"Bank Admin Dash", "Customer Dash", "Exit Program"};
            for (int i = 0; i < functions.length; i++) {
                System.out.println(i + 1 + ": " + functions[i]);
            }
            option = Integer.parseInt(scanner.next());
            switch (option) {
                case 1: {
                    //For Bank Purposes
                    System.out.println("ADMIN ACCESS");
                    if (bankAdmin.passwordVerifier(correctAdminPassword)) {
                        bankAdmin.showMenu();
                    } else {
                        System.out.println("Not Authorized!!!");

                    }
                    break;
                }
                case 2: {
                    //For User Using ATM
                    System.out.println("Greetings Dear User 😊 ");
                    boolean isUserExist = false;

                    System.out.println("Enter Username: ");
                    String userName = scanner.next();
                    System.out.println("Enter Account Number: ");
                    String accNumber = scanner.next();
                    for (int i = 0; i < userArrayList.size(); i++) {
                        if (userArrayList.get(i).getAccNumber().equals(accNumber)) {
                            isUserExist = true;
                            if (bankAdmin.passwordVerifier(userArrayList.get(i).getAccPassword())) {
                                User currentUser = new User(userArrayList.get(i));
                                currentUser.showMenu();
                            } else {
                                System.out.println("Not Authorized!!!");
                            }
                            break;
                        }
                    }
                    if (!isUserExist)
                        System.out.println("No Account Registered By The Username Entered. " +
                                "Open A Premium Account In " + bankNameIITT + " Now And Avail Amazing Offers!");
                    break;
                }
                case 3: {
                    //For Exiting from Program
                    System.out.println("Regards. Have a Great Day 😊");
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("Choose correctly");
                    showMainMenu();
            }
        } while (yn.equals("y"));
    }

    public static void main(String[] args){
        for (int i = 0; i < 52; i++) System.out.printf("=");
        System.out.println("\n==========Welcome To " + bankNameIITT + "==========");
        for (int i = 0; i < 52; i++) System.out.printf("=");
        System.out.println();
        showMainMenu();

    }


}
