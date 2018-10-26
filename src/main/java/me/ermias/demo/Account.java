package me.ermias.demo;

public class Account {
    private long actNumber;
    private double balance;


    public Account(){

    }

    public Account(long actNumber, double balance){
        this.actNumber = actNumber;
        this.balance = balance;
    }

    public long getActNumber() {
        return actNumber;
    }

    public void setActNumber(long actNumber) {
        this.actNumber = actNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
