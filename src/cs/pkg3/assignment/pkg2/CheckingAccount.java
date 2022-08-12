/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg3.assignment.pkg2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author eduardo_santos7495
 */
public class CheckingAccount extends Account implements Serializable//implement serializable to enable reading and writing objects
{
    //private static final long serialVersionUID = 1L;
    //private double balance;
    private double totalServiceCharge;
    private double historyOfServiceCharges=0;
    private ArrayList<Transaction> transList=new ArrayList<>();
    //private Transaction[] transList;
    private int transCount=0;
    private boolean Below500;// added booleans to help keep track of the charges to account
    private boolean chargeOnce;// helpfull when they left the account above 500 in a previous run and fall bellow in another
    
    
    public CheckingAccount(String aName, double aBalance, boolean BalCheck, boolean CO)
    {
        super(aName, aBalance); 
        Below500=BalCheck;
        chargeOnce=CO;
        totalServiceCharge=0; 
        //transList=null;
    }
    
    
//    public double getBalance()// already defined in Account.java
//    {
//        return balance;
//    }
    
    public void setBalance(double transAmt, int tCode)
    {
        if (tCode==1) {
            //balance=balance-transAmt;//user input
            super.setBalance(super.getBalance()-transAmt);
        }
        else if (tCode==2){
            balance =balance+transAmt;// [lu
            //CheckingAccount.super.setBalance(CheckingAccount.super.getBalance()+transAmt);
            
        }
    }
    
    public double getServiceCharge()
    {
        return totalServiceCharge;
    }
    
    public void setServiceCharge(double currentServiceCharge)
    {
        totalServiceCharge=totalServiceCharge+currentServiceCharge;
    }
    
    public double closingBalance()
    {
        balance-=totalServiceCharge;
        historyOfServiceCharges+=totalServiceCharge;
        totalServiceCharge=0;// set to zero to prevent a bug where the previous total of service charges where always applied reguardless of what was done in the current run of the program
        //super.getBalance();
        //CheckingAccount.super.getBalance();
        return getBalance();
    }
    public void addTrans(Transaction newTrans)
    {
       transList.add(newTrans);
        //transList[transCount]=newTrans;
       transCount++;
    }
    public int gettransCount()
    {
        return transCount;
    }
    public Transaction getTrans(int i)
    {
        return transList.get(i);
    }
    public boolean getBelow500()
    {
        return Below500;
    }
    public void setBelow500()
    {
        Below500=true;
    }
    public boolean getChargeOnce()
    {
        return chargeOnce;
    }
    public void setChargeOnce()
    {
        chargeOnce=true;
    }
    public double getHistoryOfServiceCharges()
    {
        return historyOfServiceCharges;
    }
}

