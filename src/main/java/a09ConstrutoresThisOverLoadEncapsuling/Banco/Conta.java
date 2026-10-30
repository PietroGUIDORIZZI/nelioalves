package a09ConstrutoresThisOverLoadEncapsuling.Banco;

import java.util.ArrayList;

public class Conta {
    private String name;
    private double balance;
    private final int number;


    public Conta(String name, int number){
        this.name = name;
        this.number = number;
        this.balance = 0;

    }
    public Conta(String name, double balance,
            int number){
        this.name = name;
        this.balance = balance;
        this.number = number;
    }

    public void deposit(double value){
        this.balance += value;
    }

    public void cashout(double value){
        if(this.balance >= value){
            this.balance -= value + 5.0;
        }
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }



    public double getBalance() {
        return balance;
    }

    public int getNumber() {
        return number;
    }
}
