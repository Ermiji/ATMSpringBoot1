package me.ermias.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    private long actNo;

    @Transient
    private boolean withdrawal;
    private boolean deposit;
    private boolean checkBalance;

    @NotNull
    private double amount;
    private String reason;
    private String action;

    public Transaction(){

    }

    public Transaction(long id, long actNo, boolean withdrawal, boolean deposit,
                       String action, double amount, String reason){
        this.id = id;
        this.actNo = actNo;
        this.withdrawal = withdrawal;
        this.deposit = deposit;
        this.action = action;
        this.amount = amount;
        this.reason = reason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getActNo() {
        return actNo;
    }

    public void setActNo(Long actNo) {
        this.actNo = actNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(boolean withdrawal) {
        this.withdrawal = withdrawal;
    }

    public boolean isDeposit() {
        return deposit;
    }

    public void setDeposit(boolean deposit) {
        this.deposit = deposit;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
