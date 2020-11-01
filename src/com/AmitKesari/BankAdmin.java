package com.AmitKesari;

import java.util.ArrayList;

import static com.AmitKesari.Main.showMainMenu;
import static com.AmitKesari.UserData.userArrayList;

public class BankAdmin implements MenuDrive {

    BankAdmin() {
        UserData userData = new UserData();
    }

    //Displays Data of all logged users
    public void displayUserData() {
        System.out.println("Details: ");
        for (int i = 0; i < userArrayList.size(); i++) {
            System.out.println(i + 1 + ": ");
            System.out.printf("%-20s%-20s\n", "User Name: ", userArrayList.get(i).getUserName());
            System.out.printf("%-20s%-20s\n", "Account Number: ", userArrayList.get(i).getAccNumber());
            System.out.printf("%-20s%-20s\n", "Password (length): ", userArrayList.get(i).getAccPassword().length());
            System.out.printf("%-20s%-20s\n", "Guardian Name: ", userArrayList.get(i).getGuardianName());
            System.out.printf("%-20s%-20s\n", "Address: ", userArrayList.get(i).getAddress());
            System.out.printf("%-20s%-20s\n", "Mobile: ", userArrayList.get(i).getMobile());
            System.out.printf("%-20s%-20s\n", "IFSC: ", userArrayList.get(i).getIFSC());
            System.out.println();
        }


    }

    private int option;

    @Override
    public void showMenu() {
        System.out.println("Choose your option:");

        String[] functions = new String[]{"Display All User Data", "Back", "Exit"};
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
                displayUserData();
                showMenu();
                break;
            }

            case 2: {

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
