package com.finance.fetch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty("account")
    private String accountId;
    @JsonProperty("type")
    private String accountType;
    @JsonProperty("transactions")
    private ArrayList<Transaction> transactions;
    @JsonProperty("Blance")//typo in source
    private double balance;

    public Account() {
    }

    public Account(String accountId, String accountType, ArrayList<Transaction> transactions, double balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.transactions = transactions;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"accountId\" :\"" + accountId + "\", \n" +
                "\"accountType\" :\"" + accountType + "\", \n" +
                "\"balance\" :" + (!Double.isNaN(balance) ? balance : "N/A") + ", \n" +
                "\"transactions\" :" + transactions +
                '}';
    }
}
