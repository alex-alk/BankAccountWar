package web;

import java.io.Serializable;
import bank.BillPay;
import bank.Checking;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.util.*;

@Named
@SessionScoped
public class AccountBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB(lookup="java:global/BankAccountEAR-1/BankAccountEJB-1/CheckingImpl!bank.Checking")
    private Checking checking;
    @EJB(lookup="java:global/BankAccountEAR-1/BankAccountEJB-1/BillPayService!bank.BillPay")
    private BillPay billPay;
    
    private String currentPayee;
    private double amount = 15;
    private boolean paymentOK = false;
    private String paymentConfirmation = null;
    private String warn;
  
    public AccountBean() {
    }
    
    public String getWarn() {
    	return this.warn;
    }
    
    public void setWarn(String warn) {
    	this.warn = warn;
    }
    
    public String getCheckingAccountNumber() {
        return this.checking.getAccountNumber();
    }
    
    public double getCheckingBalance() {
        return this.checking.getBalance();
    }
    public boolean isEmpty() {
    	return this.checking.isEmpty();
    }
    
    public List<String> getPayees() {
        return this.billPay.getPayees();
    }
    
    public String getCurrentPayee() {
        if (this.currentPayee == null) {
            this.currentPayee = this.billPay.getPayees().get(0);
        }
        return this.currentPayee;
    }
    
    public void setCurrentPayee(String payee) {
        this.currentPayee = payee;
    }
    
    public double getAmount() {        
        return this.amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void reset() {
        this.paymentOK = false;
        this.paymentConfirmation = null;
    }
    
    public String getPaymentConfirmation() {
        return this.paymentConfirmation;
    }
    
    public boolean getPaymentOK() {
        return this.paymentOK;
    }
    
    public void makePayment() {
    	checking.doDeduct(amount);
        String confirmation = this.billPay.doPay(this.checking.getAccountNumber(), currentPayee, this.amount);
        this.paymentOK = true;
        this.paymentConfirmation = confirmation;
        this.amount = 15;
        this.currentPayee = null;
    }
    public String checkPayment() {
    	if(checking.getBalance()-amount<0) {
    		warn = "Credit insuficient.";
    		return "plateste";
    	}
    	else {
    		warn="";
    		return "confirma";
    	}
    }
    public void setChecking(Checking ch) {
    	checking = ch;
    }
    public void setBillPay(BillPay bp) {
    	billPay = bp;
    }
}
