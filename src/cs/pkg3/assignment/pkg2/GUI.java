/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs.pkg3.assignment.pkg2;

import static cs.pkg3.assignment.pkg2.CS3Assignment2.Accounts;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.FindCheckingAccount;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.Tcode;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.TransactionEnd;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.addAccount;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.c;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.getCheckNumber;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.getTransAmt;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.getTranscode;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.processCheck;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.processDeposit;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.t;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.transAmt;
import static cs.pkg3.assignment.pkg2.CS3Assignment2.promptToSave;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;


/**
 *
 * @author Edd
 */
public class GUI {
    public static String optionTWO()// changed the option from void to string to help set text in editor pane
    {
       String List="<html><font face=Monospace><pre>";
       List+="List of transactions<br/>Name: "+c.getName()+"<br/>";
       //List+="Balance: $"+c.getBalance()+"<br/>";
       if(c.getBalance()<0)//change output for a negative balance
        {
            double negBalance=c.getBalance()*(-1);
                   List+="Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            List+="Current Balance: $"+(round.format(c.getBalance()))+"\n";
        }
       List+="Total Service Charge: $"+c.getHistoryOfServiceCharges()+"<br/>";
       List+=String.format("%-10s%-15s%10s", "ID","TYPE","Amount")+"<br/>"; //used format to get space uniform
        for (int i = 0; i < c.gettransCount(); i++) 
        {
            t=c.getTrans(i);
            String description="";
            switch(t.getTransId())
            {
                case 1: description="Check";
                        break;
                case 2: description="Deposit";
                        break;
                case 3: description="Service Fee";
                        break;
            }   
            String money="$"+ round.format(t.getTransAmount());
            List+=String.format("%-10s",Integer.toString(t.getTransNumber()));
            List+=String.format("%-15s",description);
            List+=String.format("%10s",money);
            List+="<br/>";
        }
        
       //JOptionPane.showMessageDialog(null,List);
       return List;
    }
//    public static void optionsWindow()  NO LONGER NEEDED IN NEW VERSION
//    {
//        JFrame frame=new JFrame("Checking account options");
//        JPanel panel=new JPanel();
//        JLabel prompt= new JLabel ("Choose action:");;
//        JRadioButton one, two, three, four, five, six; 
//        prompt.setFont (new Font ("Helvetica", Font.BOLD, 24));
//        one = new JRadioButton ("Enter Transaction");
//        one.setBackground (Color.white);
//        two = new JRadioButton ("List All Transactions");
//        two.setBackground (Color.white);
//        three = new JRadioButton ("List All Checks");
//        three.setBackground (Color.white);
//        four = new JRadioButton ("List All Deposits");
//        four.setBackground (Color.white);
//        five = new JRadioButton ("Read from file");
//        five.setBackground (Color.white);
//        six = new JRadioButton ("Write to file");
//        six.setBackground (Color.white);
//        ButtonGroup group = new ButtonGroup();
//        group.add (one);
//        group.add (two);
//        group.add (three);
//        group.add(four);
//        group.add(five);
//        group.add(six);
//        one.addActionListener((ActionEvent e) -> {
//            frame.setVisible(false);
//            optionONE();
//            frame.setVisible(true);
//        });
//        two.addActionListener((ActionEvent e) -> {
//            frame.setVisible(false);
//            optionTWO();
//            frame.setVisible(true);
//        });
//        three.addActionListener((ActionEvent e) -> {
//            frame.setVisible(false);
//            optionTHREE();
//            frame.setVisible(true);
//        });
//        four.addActionListener((ActionEvent e) -> {
//            frame.setVisible(false);
//            optionFOUR();
//            frame.setVisible(true);
//        });
//        five.addActionListener((ActionEvent e) -> {
//            frame.setVisible(false);
//            optionFIVE();
//            frame.setVisible(true);
//        });
//        six.addActionListener((ActionEvent e) -> {
//            frame.setVisible(false);
//            optionSIX();
//            frame.setVisible(true);
//        });
//        panel.add(prompt);
//        panel.add(one);
//        panel.add(two);
//        panel.add(three);
//        panel.add(four);
//        panel.add(five);
//        panel.add(six);
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        frame.add(panel);
//        frame.setSize(300, 220);
//        frame.setLocationRelativeTo(null);
//        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//    frame.addWindowListener(new java.awt.event.WindowAdapter() //set window to check if account has been saved prior to pressing x
//    {
//    @Override
//    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//        if (promptToSave){
//            if(JOptionPane.showConfirmDialog(frame, 
//            "Do you want to save account before closing?", "Save before exiting?", 
//            JOptionPane.YES_NO_OPTION,
//            JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
//                optionSIX();//if they do want to save just run option six;
//            }
//            else
//                System.exit(0);// else just exit
//        }
//        else
//            System.exit(0);
//    }
//});
//        frame.setVisible(true);
//        
//        
//    }  
    public static void optionONE()
    {
        getTranscode();//ask for transcode
        if (Tcode!=0)//replaced while loop with if statement
        {
           if(Tcode!=1&&Tcode!=2)
           {
               String message;
               message="Transaction code was invalid re-enter code";
               JOptionPane.showMessageDialog(null, message);
               getTranscode();
           }
          
          if(Tcode==1)
          {
              getCheckNumber();
              getTransAmt();//ask for transaction amount
              
              processCheck(transAmt);
          }
          else if(Tcode==2)
          {
              //getTransAmt();//ask for transaction amount
              
              processDeposit(transAmt);
          }
        }
        TransactionEnd();
    }
    public static String optionTHREE()// changed the option from void to string to help set text in editor pane
    {
        String List="<html><font face=Monospace><pre>";
        List+="Listing all checks<br/>Name: "+c.getName()+"<br/>";
        if(c.getBalance()<0)//change output for a negative balance
        {
            double negBalance=c.getBalance()*(-1);
                   List+="Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            List+="Current Balance: $"+(round.format(c.getBalance()))+"\n";
        }
        List+="Total Service Charge: $"+c.getHistoryOfServiceCharges()+"<br/>";
        
        List+=String.format("%-10s%-15s%10s", "ID","Check", "Amount")+"<br/>";
        for (int i = 0; i < c.gettransCount(); i++) 
        {
            t=c.getTrans(i);
            
            if(t.getTransId()==1)
            {
                String money="$"+ round.format(t.getTransAmount());
                List+=String.format("%-10s",Integer.toString(t.getTransNumber()));
                List+=String.format("%-15s",Integer.toString(((Check) t).getCheckNumber()));
                List+=String.format("%10s", money);
                List+="<br/>";
            }
        }
        //JOptionPane.showMessageDialog(null,List);
        return List;
    }
    public static String optionFOUR()// changed the option from void to string to help set text in editor pane
    {
        String List="<html><font face=Monospace><pre>";
        List+="Listing all deposits<br/>Name: "+c.getName()+"<br/>";
        if(c.getBalance()<0)//change output for a negative balance
        {
            double negBalance=c.getBalance()*(-1);
                   List+="Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            List+="Current Balance: $"+(round.format(c.getBalance()))+"\n";
        }
        List+="Total Service Charge: $"+c.getHistoryOfServiceCharges()+"<br/>";
        List+=String.format("%-10s%-10s%-10s%-10s%10s", "ID","Type","Checks","Cash", "Amount")+"<br/>";
        for (int i = 0; i < c.gettransCount(); i++) 
        {
            t=c.getTrans(i);
            if(t.getTransId()==2)
            {
                String checks="$"+round.format(((Deposit)t).getCheckAmt());
                String cashs="$"+round.format(((Deposit)t).getCashAmt());
                String money="$"+ round.format(t.getTransAmount());
                List+=String.format("%-10s%-10s%-10s%-10s%10s", t.getTransNumber(),"Deposit",checks,cashs, money)+"<br/>";
            }
        }
        //JOptionPane.showMessageDialog(null,List);
        return List;
    }
    static DecimalFormat round= new DecimalFormat ("#.00");
    
    public static boolean optionFIVE()
    {
        JFileChooser open=new JFileChooser();//create Jfilechooser window to select file
        int result=open.showOpenDialog(null);
        if(result==JFileChooser.APPROVE_OPTION)
        {
             try//try to open file chosen, if not throw exception
            {
                File file = open.getSelectedFile();//create file and set it to selected file from window
                ObjectInputStream oout=new ObjectInputStream(new FileInputStream(file)); //creat object stream to read data from file input
                //c=(CheckingAccount)oout.readObject();// cast object read from file as checking accouont
                Accounts=(ArrayList<CheckingAccount>) oout.readObject();//read from file as arraylist
                oout.close();// close stream
                c=Accounts.get(0);//set c equal to first CheckingAccount in arraylist
                promptToSave=false;// turn off flag once user has opened amother file
                return true;
            }
             catch (FileNotFoundException ex) {
            System.out.println("Error with specified file") ;
            ex.printStackTrace();
        }
        catch (IOException ex) {
            System.out.println("Error with I/O processes") ;
            ex.printStackTrace();
        }             
            catch (Exception e){
               System.out.println("SOMETHING WENT WRONG");
            }
            
        }    
        return false;
    }
    
    public static void optionSIX()
    {
        JFileChooser open=new JFileChooser();
        int result=open.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File file = open.getSelectedFile();
                ObjectOutputStream oout=new ObjectOutputStream(new FileOutputStream(file));
                oout.writeObject(Accounts);
                oout.flush();
                oout.close();
                promptToSave=false;// turn off flag once user has saved
            }
            catch (FileNotFoundException ex) {
            System.out.println("Error with specified file") ;
            ex.printStackTrace();
        }
        catch (IOException ex) {
            System.out.println("Error with I/O processes") ;
            ex.printStackTrace();
        }             
            catch (Exception e){
               System.out.println("SOMETHING WENT WRONG");
            }
        }
//        else
//            open.cancelSelection();
    }
    public static String optionSEVEN()// created to show all service charges by themselves
    {
        String List="<html><font face=Monospace><pre>";
        List+="Listing all deposits<br/>Name: "+c.getName()+"<br/>";
        if(c.getBalance()<0)//change output for a negative balance
        {
            double negBalance=c.getBalance()*(-1);
                   List+="Balance: ($"+(round.format(negBalance))+")\n";
        }
        else
        {
            List+="Current Balance: $"+(round.format(c.getBalance()))+"\n";
        }
        List+="Total Service Charge: $"+c.getHistoryOfServiceCharges()+"<br/>";
        List+=String.format("%-10s%30s", "ID", "Amount")+"<br/>";
        for (int i = 0; i < c.gettransCount(); i++) 
        {
            t=c.getTrans(i);
            if(t.getTransId()==3)
            {
                //String checks="$"+round.format(((Deposit)t).getCheckAmt());
                //String cashs="$"+round.format(((Deposit)t).getCashAmt());
                String money="$"+ round.format(t.getTransAmount());
                List+=String.format("%-10s%-20s%10s", t.getTransNumber(),"Sevice Charge", money)+"<br/>";
            }
        }
        return List;
    }
    
    public static void MenuWindow()
    {
        //frame to be used for window
        JFrame MW= new JFrame("Checking Account Operations");
        //JTextArea TA=new JTextArea();
        JEditorPane TP=new JEditorPane();// used editor pane to use html tags from previous versions
        JScrollPane SP=new JScrollPane(TP);// used scroll pane incase the amount of text does not fit within the window
        //the menu options on the menu bar
        JMenu File= new JMenu("File");
        JMenu Account= new JMenu("Account");
        JMenu Transactions= new JMenu("Transactions");
        // the menu items found under file menu
        JMenuItem Read= new JMenuItem("Read From File");
        JMenuItem Save= new JMenuItem("Save To File");
        // the menu items found under account
        JMenuItem AddNewAccount= new JMenuItem("Add new account");
        JMenuItem ListAccountsTransaction= new JMenuItem("List account transactions");
        JMenuItem ListAllChecks= new JMenuItem("List all checks");
        JMenuItem ListAllDeposits= new JMenuItem("List all deposits");
        JMenuItem FindAnAccount= new JMenuItem("Find an account");
        JMenuItem ListAllServiceCharges= new JMenuItem("List all service charges");
        JMenuItem ListAllAccounts= new JMenuItem("List all accounts");
        // the menu items found under transactions
        JMenuItem AddTransactions= new JMenuItem("Add transactions");
        // make certain menu items unavailable until an account has been loaded should help curtail potentail errors
        Save.setVisible(false);
        ListAccountsTransaction.setVisible(false);
        ListAllChecks.setVisible(false);
        ListAllDeposits.setVisible(false);
        FindAnAccount.setVisible(false);
        Transactions.setVisible(false);
        ListAllServiceCharges.setVisible(false);
        ListAllAccounts.setVisible(false);
        TP.setEditable(false);
        TP.setContentType("text/html");
        Read.addActionListener((ActionEvent e) ->
        {
            if (promptToSave)
            {
            if(JOptionPane.showConfirmDialog(MW, 
            "Do you want to save account before closing?", "Save?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
                {
                    promptToSave=false;
                    optionSIX();//if they do want to save just run option six;
                }
            else
                promptToSave=false;
            }
           else
           {
                boolean x=optionFIVE();// just run option five
                if(x)
                {
                    Save.setVisible(true);
                    ListAccountsTransaction.setVisible(true);
                    ListAllChecks.setVisible(true);
                    ListAllDeposits.setVisible(true);
                    FindAnAccount.setVisible(true);
                    Transactions.setVisible(true);
                    ListAllServiceCharges.setVisible(true);
                    ListAllAccounts.setVisible(true);
                }
           }
        });
        Save.addActionListener((ActionEvent e)->
        {
            optionSIX();// just run option six
        });
        AddNewAccount.addActionListener((ActionEvent e) ->
        {
           addAccount();// just run the opening from project five
           Save.setVisible(true);
           ListAccountsTransaction.setVisible(true);
           ListAllChecks.setVisible(true);
           ListAllDeposits.setVisible(true);
           FindAnAccount.setVisible(true);
           Transactions.setVisible(true);
           ListAllServiceCharges.setVisible(true);
           ListAllAccounts.setVisible(true);
        });
        ListAccountsTransaction.addActionListener((ActionEvent e) ->
        {
            TP.setText(optionTWO());//just run option two
        });
        ListAllChecks.addActionListener((ActionEvent e) ->
        {
           TP.setText(optionTHREE());// just run option three
        });
        ListAllDeposits.addActionListener((ActionEvent e) ->
        {
           TP.setText(optionFOUR());// just run option four
        });
        ListAllServiceCharges.addActionListener((ActionEvent e)->
        {
            TP.setText(optionSEVEN());//run option seven
        });
        AddTransactions.addActionListener((ActionEvent e)->
        {
           optionONE();// just run option one 
        });
        FindAnAccount.addActionListener((ActionEvent e)->
        {
            TP.setText(FindCheckingAccount());
        });
        ListAllAccounts.addActionListener((ActionEvent e)->
        {
            String All="";// just run option two for every checkingaccount in Accounts array
            for (int i = 0; i < Accounts.size(); i++) 
            {
                c=Accounts.get(i);
                All+="=============================================";//seperator between accounts
                All+=optionTWO();
            }
            TP.setText(All);
        });
        File.add(Read);
        File.add(Save);
        Account.add(AddNewAccount);
        Account.add(ListAccountsTransaction);
        Account.add(ListAllChecks);
        Account.add(ListAllDeposits);
        Account.add(ListAllServiceCharges);
        Account.add(FindAnAccount);
        Account.add(ListAllAccounts);
        Transactions.add(AddTransactions);
        JMenuBar MWB= new JMenuBar();
        MWB.add(File);
        MWB.add(Account);
        MWB.add(Transactions);
        MW.setJMenuBar(MWB);
        //
        
        MW.getContentPane().add(SP);
        MW.setVisible(true);
        MW.setSize(600, 400);
        MW.setLocationRelativeTo(null);
        MW.addWindowListener(new java.awt.event.WindowAdapter() //set window to check if account has been saved prior to pressing x
    {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
    {
        if (promptToSave)
        {
            if(JOptionPane.showConfirmDialog(MW, 
            "Do you want to save account before closing?", "Save before exiting?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
          
                optionSIX();//if they do want to save just run option six;
                System.exit(0);
                
            }
            else
                System.exit(0);// else just exit
        }
        else
            System.exit(0);
    }
});
    }
}
