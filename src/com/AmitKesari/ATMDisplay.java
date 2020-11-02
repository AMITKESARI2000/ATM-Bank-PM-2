package com.AmitKesari;

public interface ATMDisplay extends MenuDrive {
    default void msgDisplay() {
        for (int i = 0; i < 20; i++)         System.out.printf("=");
        System.out.println("\tGREETING\t");
        for (int i = 0; i < 20; i++)         System.out.printf("=");
    }
    String keypadIP();
    boolean cashDispenser(float amount);
    boolean cashDepositor(float amount);
    boolean balanceSlipDispenser();
}
