package com.AmitKesari;

import static com.AmitKesari.Main.showMainMenu;

public class ATMMachine implements ATMDisplay {
    private double reserveDailyAmount = 10e7;
    private int paperRoll = 1000;
    private float ambientTemp = 28;
    private String currencyType = "Rupee";
    private float energyConsumeKWH = (float) 0.6;

    @Override
    public String keypadIP() {
        return null;
    }

    @Override
    public boolean cashDispenser(float amount) {
        return false;
    }

    @Override
    public boolean cashDepositor(float amount) {
        return false;
    }

    @Override
    public boolean balanceSlipDispenser() {
        return false;
    }

    private int option = 1;

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
