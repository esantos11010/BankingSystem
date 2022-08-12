package cs.pkg3.assignment.pkg2;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardo_santos7495
 */
public class Transaction implements Serializable{////implement serializable to enable reading and writing objects
    private int transNumber;
    private int transId;
    private double transAmt;
    
    public Transaction(int number, int id, double amount)
    {
        transNumber=number;
        transId=id;
        transAmt=amount;
    }
    
    public int getTransNumber()
    {
        return transNumber;
    }
    
    public int getTransId()
    {
        return transId;
    }
    
    public double getTransAmount()
    {
        return transAmt;
    }

}
