package com.finance.fetch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Accounts {
    ArrayList<Account> accounts;

    public Accounts() {
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"accounts\" :" + accounts + "\n" +
                '}';
    }
}
