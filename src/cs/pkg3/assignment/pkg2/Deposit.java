/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg3.assignment.pkg2;

import java.io.Serializable;

/**
 *
 * @author eduardo_santos7495
 */
public class Deposit extends Transaction implements Serializable{//implement serializable to enable reading and writing objects
    private double checkAmt;
    private double cashAmt;
    public Deposit(int tCount, int tId, double tAmt, double checkAmt, double cashAmt)
    {
        super(tCount, tId, tAmt);
        this.checkAmt=checkAmt;
        this.cashAmt=cashAmt;
    }
    public double getCheckAmt()
    {
        return checkAmt;
    }
    public double getCashAmt()
    {
        return cashAmt;
    }
    public void setcheckAmt(double checkAmt)
    {
        this.checkAmt=checkAmt;
    }
    public void setccashAmt(double cashAmt)
    {
        this.cashAmt=cashAmt;
    }
}
