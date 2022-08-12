/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg3.assignment.pkg2;

import java.io.Serializable;

/**
 *
 * @author Edd
 */
public class Account implements Serializable{//implement serializable to enable reading and writing objects
    protected String name;
    protected double balance;
    protected int AccNum;//used to keep Account array list in order
    public Account(String acctName, double initBalance)
    {
        balance = initBalance;
        name = acctName;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name=name;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    public int getAccountNumber()
    {
        return AccNum;
    }
}
