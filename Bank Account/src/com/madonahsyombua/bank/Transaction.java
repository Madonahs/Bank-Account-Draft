package com.madonahsyombua.bank;

import java.util.Date;

/**
 * @author syombua
 *
 */
public class Transaction {
 
	private double amount;
	
	private Date timestamp;
	
	private String memo;
	
	private Account inAccount;	
	public Transaction(double amount, Account inAccount){
		
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
		
	}
  public Transaction(double amount,String memo, Account inAccount){
	 /**call the two arg constructor first**/
	  
	  this(amount, inAccount);
	  /**set the memo**/
	  this.memo = memo;
	  
  }
	
/*
*getAmount method returns amount;*/
public double getAmount() {
	// TODO Auto-generated method stub
	
	return this.amount;
	
}
public String getSummaryLine(){
	if (this.amount >= 0){
		return String.format("%s : $%.02f :%s,",this.timestamp.toString(),this.amount,this.memo);
	}else{
	return String.format("%s : $(%.02f) :%s,",this.timestamp.toString(),this.amount,this.memo);
	
}
}
}