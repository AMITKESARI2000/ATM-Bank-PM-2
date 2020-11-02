package com.AmitKesari;

import static com.AmitKesari.Main.showMainMenu;

public class User implements MenuDrive{
    private int option;
    @Override
    public void showMenu() {
        System.out.println("Choose your option:");

        String[] functions = new String[]{"Account Details","Cash Withdrawal","Cash Deposition","Bank Statement Slip"
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
