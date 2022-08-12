/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg3.assignment.pkg2;

//import static cs.pkg3.assignment.pkg2.GUI.depositMethod;
import static cs.pkg3.assignment.pkg2.GUI.MenuWindow;
//import static cs.pkg3.assignment.pkg2.GUI.optionsWindow;
import java.text.DecimalFormat;
import java.util.ArrayList;
//import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author eduardo_santos7495
 */
public class CS3Assignment2 {

    /**
     * @param args the command line arguments
     */ 
    static ArrayList<CheckingAccount> Accounts= new ArrayList<>();
    static CheckingAccount c;
    static Deposit d;
    static Transaction t;
    static Check ch;
    static String userinput1="", userinput2="";
    static int Tcode, Check, AccNumber=0;
    static double transAmt;
    static boolean balanceCheck=false;//used to check when the balance falls below 500
    //static boolean chargeOnce=false;// used to make sure the charge for falling below 500 is apllied once
    static boolean promptToSave=false;//used to mark whether the promt to save should appear or not
    
    public static void main(String[] args) {
        MenuWindow();
//        while(userinput1.isEmpty())//user now has to enter name before proceding
//        {
//            userinput1=JOptionPane.showInputDialog("Enter your Name:");
//            if(userinput1.isEmpty())
//            {
//                JOptionPane.showMessageDialog(null, "Cannot continue without a name");
//            }
//        }
//        boolean number=false;
//        while(!number)// now checks to make sure a non negative number has been entered
//        {
//            try
//            {
//                userinput2=JOptionPane.showInputDialog("Enter your initial balance:");
//                transAmt=Double.parseDouble(userinput2);
//                if(transAmt>=0)
//                    number=true;
//            }
//            catch (NumberFormatException e) 
//            {
//                JOptionPane.showMessageDialog(null,"Error, not a number. Please try again.");
//            }
//        }
//        transAmt=Double.parseDouble(userinput2);
//        
//        if(transAmt>=500)
//        {
//            balanceCheck=true;
//        }
//        c=new CheckingAccount(userinput1,transAmt);//create checking account with balance set to user input
//        //optionsWindow();
        
    }
    public static int processCheck(double transAmt)//process amount as a check
    {
        promptToSave=true;//set flag to promt user to save before closing 
        String message;
        c.setBalance(transAmt, Tcode);
        //add transaction to array
        ch=new Check(c.gettransCount(), 1, transAmt, Check);
        //t=new Transaction(c.gettransCount(), 1, transAmt);
        c.addTrans(ch);
        c.setServiceCharge(.15);
        //add service charge to array
        t=new Transaction(c.gettransCount(), 3, .15);
        c.addTrans(t);
        
        message="Transaction: Check in amount of $"+round.format(transAmt) +"\n";
        if(c.getBalance()<0)//change output for a negative balance
        {
            double negBalance=c.getBalance()*(-1);
                   message=message+"Current Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            message=message+"Current Balance: $"+(round.format(c.getBalance()))+"\n";
        }
        message=message+"Service charge: Check -- charge $0.15";
        
        if(c.getBelow500())//if balance was above 500 check balance dipp below 500
        {
            if(c.getBalance()<500&&c.getChargeOnce()==false)//charge if less than 500 only if not charged before
            {
                c.setChargeOnce();
                c.setServiceCharge(5.0);
                message=message+"\n"+
                        "Service charge: Below $500--- charge $5.00";// add to message
                // add to transactin list
                t=new Transaction(c.gettransCount(), 3, 5.0);
                c.addTrans(t);
            }
        }
        if(c.getBalance()<50)//check for low balance
        {
            message=message+"\n"+
                    "Warning: Balance below $50";// add to message
        }
        if(c.getBalance()<0)//check for negative balance
        {
            c.setServiceCharge(10.00);
            message=message+"\n"+
                    "Service charge: Below $0--- charge $10.00";//add to message
            t=new Transaction(c.gettransCount(), 3, 10.0);
            c.addTrans(t);
        }
        message=message+"\n"+
                "Total service charge: $"+round.format(c.getServiceCharge());
        
        JOptionPane.showMessageDialog(null, message); 
        Accounts.set(c.getAccountNumber(), c);//overwrite the checking account in the list with updated object
        System.out.println(c.getAccountNumber());
        return Tcode;
    }
    public static void processDeposit(double transAmt)
    {
        promptToSave=true;//set flag to promt user to save before closing 
        double in;// create fields to hold cash and checks
        double in2;
        JTextField check=new JTextField();//create text fields for input
        JTextField cash=new JTextField();
        JComponent []inputs=new JComponent[]{
           new JLabel("Check"),
            check,
            new JLabel("Cash"),
            cash,
        };
        JOptionPane.showConfirmDialog(null, inputs,"Deposit", JOptionPane.PLAIN_MESSAGE);
        if(check.getText().isEmpty())// check contents of textfield if empty set to 0
            check.setText("0");
        if(cash.getText().isEmpty())
            cash.setText("0");
        in=Double.parseDouble(check.getText());// TODO add try catch block to make sure numbers were entered
        in2=Double.parseDouble(cash.getText());
        transAmt=in+in2;
        
        String message;
        c.setBalance(transAmt, Tcode);
        if(c.getBalance()>=500)// check to see if balance goes above 500
        {
           c.setBelow500();
        }
        c.setServiceCharge(.10);
        message="Transaction: Deposit in amount of $"+round.format(transAmt)+"\n";
        if(c.getBalance()<0)//change output for a negative balance
        {
            double negBalance=c.getBalance()*(-1);
                   message=message+"Current Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            message=message+"Current Balance: $"+(round.format(c.getBalance()))+"\n";
        }
        message=message+"Service charge: Check -- charge $0.10";
        message=message+"\n"+
                "Total service charge: $"+round.format(c.getServiceCharge());
        JOptionPane.showMessageDialog(null, message);
        d=new Deposit(c.gettransCount(), 2, transAmt, in, in2);
        c.addTrans(d);
        t=new Transaction(c.gettransCount(), 3, .10);
        c.addTrans(t);
        //getTranscode();
        //return Tcode;
        Accounts.set(c.getAccountNumber(), c);//overwrite the checking account in the list with updated object
    }
    public static void TransactionEnd()
    {
        String message;
        message="Tansaction: End\n";
        if(c.getBalance()<0)//change output for a negative balance
        {
            double negBalance=c.getBalance()*(-1);
                   message=message+"Current Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            message=message+"Current Balance: $"+(round.format(c.getBalance()))+"\n";
        }
        message=message+"Total service charge: $"+round.format(c.getServiceCharge())+"\n";
        if(c.getBalance()<0)
        {
            double negBalance=c.closingBalance()*(-1);
                   message=message+"Final Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            message=message+"Final Balance: $"+(round.format(c.closingBalance()))+"\n";
        }
        JOptionPane.showMessageDialog(null, message);
        
    }
    public static int getTranscode()//get transcode
    {
        userinput1=JOptionPane.showInputDialog("Enter transaction code\n1(Check),2(Deposit) or 0(End))\n");
        Tcode=Integer.parseInt(userinput1);

        return Tcode;
    }
    public static double getTransAmt()//get trans amount
    {
        userinput1=JOptionPane.showInputDialog("Enter transaction amount:");
        transAmt=Double.parseDouble(userinput1);
        if(transAmt<0)//implemented safe mutation
              {
                  String message;
               message="Transaction amount was invalid re-enter amount";
               JOptionPane.showMessageDialog(null, message);
               getTransAmt();
              }
        return transAmt;
    }static DecimalFormat round= new DecimalFormat ("#.00");
    public static int getCheckNumber()
    {
        userinput1=JOptionPane.showInputDialog("Enter check number:");
        Check=Integer.parseInt(userinput1);
        
        return Check;
    }
    public static void addAccount()
    {
        userinput1="";
        userinput2="";
        boolean number=false;
         
        while(userinput1.isEmpty())//user now has to enter name before proceding
        {
            
            String[] options = {"OK"};
            JPanel panel = new JPanel();
            JLabel lbl = new JLabel("Enter account name: ");
            JTextField txt = new JTextField(10);
            panel.add(lbl);
            panel.add(txt);
            JOptionPane.showOptionDialog(null, panel, "New Checking Account", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
            userinput1=txt.getText();//=JOptionPane.showInputDialog("Enter account name:");
            if(userinput1.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Cannot continue without a name");
            }    
        }
        while(!number)// now checks to make sure a non negative number has been entered
        {
            try
            {
                String[] options = {"OK"};
                JPanel panel = new JPanel();
                JLabel lbl = new JLabel("Enter account balance: ");
                JTextField txt = new JTextField(10);
                panel.add(lbl);
                panel.add(txt);
                JOptionPane.showOptionDialog(null, panel, "New Checking Account", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

                userinput2=txt.getText();
                //userinput2=JOptionPane.showInputDialog(null,"Enter initial balance:");

                transAmt=Double.parseDouble(userinput2);
                number=true;
            }
            catch (NumberFormatException e) 
            {
                JOptionPane.showMessageDialog(null,"Error, not a number. Please try again.");
            }
        }
            

            
        
        balanceCheck = transAmt>=500;
        c=new CheckingAccount(userinput1,transAmt, balanceCheck, false);//create checking account with balance set to user input
        c.AccNum=Accounts.size();//set account number equal to array size
        Accounts.add(c);//Add checking account to array list
        promptToSave=true;// after user has created account set reminder to save on close
     
//        boolean number=false;
//        while(!number)// now checks to make sure a non negative number has been entered
//        {
//            try
//            {
//                userinput2=JOptionPane.showInputDialog(null,"Enter initial balance:");
//                transAmt=Double.parseDouble(userinput2);
//                if(transAmt>=0)
//                    number=true;
//            }
//            catch (NumberFormatException e) 
//            {
//                JOptionPane.showMessageDialog(null,"Error, not a number. Please try again.");
//            }
//        }
//        transAmt=Double.parseDouble(userinput2);
//        
//        if(transAmt>=500)
//        {
//            balanceCheck=true;
//        }
//        c=new CheckingAccount(userinput1,transAmt);//create checking account with balance set to user input
//        c.AccNum=Accounts.size();//set account number equal to array size
//        Accounts.add(c);//Add checking account to array list
    }
    public static String FindCheckingAccount()
    {
        boolean found=false;
        userinput1=JOptionPane.showInputDialog("Enter account name:");
        for (int i = 0; i < Accounts.size(); i++) 
        {
            if(userinput1.equals(Accounts.get(i).getName()))
            {
                //System.out.println(Accounts.get(i).getName());
                c=Accounts.get(i);
                found=true;
                break;
            }
        }
        if (found)
        {
            return ("<html><font face=Monospace><pre>Found account for: "+c.getName());
        }
        else
        return ("<html><font face=Monospace><pre>NO ACCOUNT FOUND FOR: "+userinput1);
    }
}
