package com.calvin.model;

import java.sql.Date;

public class Transaction {

    private Date date;
    private String service;
    private double amount;
    private String code;

    public Transaction(Date date, String service, double amount, String code) {
        this.date = date;
        this.service = service;
        this.amount = amount;
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public String getService() {
        return service;
    }

    public double getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }
}
