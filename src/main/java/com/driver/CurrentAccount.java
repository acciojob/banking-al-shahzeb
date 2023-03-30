package com.driver;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only


    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;

        if(super.getBalance()<5000)
            throw new Exception("Insufficient Balance");
    }


    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        boolean isvalid=checkValidity(tradeLicenseId);

        if(!isvalid){
            int n=tradeLicenseId.length(),max=0;
            int freq[]=new int[26];
            for(int i=0; i<n; i++) {
                freq[tradeLicenseId.charAt(i)-'A']++;
                max=Math.max(max,freq[tradeLicenseId.charAt(i) - 'A']);
            }

            if(max>(n+1)/2)
                throw new Exception("Valid License can not be generated");
            else{
                tradeLicenseId = makeValid(tradeLicenseId);
            }
        }


    }

    private String makeValid(String tradeLicenseId) {
        int n=tradeLicenseId.length();

        char rearranged[] = new char[n];
        int freq[]=new int[26];

        for(int i=0; i<n; i++) {
            freq[tradeLicenseId.charAt(i) - 'A']++;
        }
        int ind=0,len=0;
        for(int i=0; i<26; i++){
            char ch=(char)((char)i + 'A');
            int val=freq[i];
            while(val>0){
                rearranged[ind]=ch;
                ind=(ind+2)%n;
                if(ind==0) ind++;
                val--;
            }
        }

        return new String(rearranged);
    }

    private int findMax(int ar[]){
        int max=0,maxi=-1;
        for(int i=0; i<ar.length;i++)
            if(ar[i]>max){
                max=ar[i];
                maxi=i;
            }
        return maxi;
    }

    private boolean checkValidity(String tradeLicenseId) {
        for(int i=0; i<tradeLicenseId.length()-1; i++)
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1))
                return false;

        return true;
    }

}
