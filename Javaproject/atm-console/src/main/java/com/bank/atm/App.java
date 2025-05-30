package com.bank.atm;
import com.bank.atm.repo.Bank;
import com.bank.atm.ui.ATM;
public class App {
    public static void main(String[] args) {
        ATM atm = new ATM(Bank.demoBank());
        atm.start();
    }
}