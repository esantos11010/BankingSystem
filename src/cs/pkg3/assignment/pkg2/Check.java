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
public class Check extends Transaction implements Serializable//implement serializable to enable reading and writing objects
{
    private int checkNumber;
    public Check(int tCount, int tId, double tAmt, int checkNumber)
    {
        super(tCount, tId, tAmt);
        this.checkNumber=checkNumber;
    }
    public int getCheckNumber()
    {
        return checkNumber;
    }
    public void setCheckNumber(int checkNumber)
    {
        this.checkNumber=checkNumber;
    }
}
