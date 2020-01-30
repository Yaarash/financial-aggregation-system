package com.finance.fetch.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @JsonProperty("id")
    private long transactionId;
    @JsonProperty("amount")
    private double transactionAmount;
    @JsonProperty("currency")
    private String transactionCurrency;

    public Transaction() {
    }

    public Transaction(long transactionId, double transactionAmount, String transactionCurrency) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionCurrency = transactionCurrency;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    @Override
    public String toString() {
        return "{" +
                "\"transactionId\" :" + transactionId + ",\n" +
                "\"transactionAmount\" :" + transactionAmount + ",\n" +
                "\"transactionCurrency\" :\"" + transactionCurrency + '\"' + "\n" +
                '}';
    }
}
