package com.driver;

public class Main {
    public static void main(String[] args) {
        CurrentAccount currentAccount;
        try {
            currentAccount=new CurrentAccount("asd",6576.9,"MOOJOOIO");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            currentAccount.validateLicenseId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(currentAccount.getTradeLicenseId());
    }
}