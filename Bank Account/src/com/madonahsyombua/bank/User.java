package com.madonahsyombua.bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author syombua
 */
public class User {

	private String firstName;
	
	private String lastName;
	
	private String uuid;
	
	private byte pinHash[];
	
	private ArrayList<Account> accounts;
	
	
	public User(String firstName, String lastName, String pin, Bank theBank){
		/**set users name**/
		this.firstName = firstName;
		this.lastName = lastName;
		
		/**store the pins MD5 hash, rather than the original value for security reasons;**/
		try{
		MessageDigest md = MessageDigest.getInstance("MD5");
		this.pinHash = md.digest(pin.getBytes());
		
		}catch(NoSuchAlgorithmException e){
			System.err.println("Error, Caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		/**get a new unique universal IF for the user**/
		
		this.uuid = theBank.getNewUserUUID();
		/**create empty list of accounts**/
		
		this.accounts = new ArrayList<Account>();
		//print log message
		System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
	}
	
	/**add account for users should be public**/
	public void addAccount(Account onAcct){
		this.accounts.add(onAcct);		
	}
	/**
	* getUUID gives a status report 
	* 
	* @return <code>true</code> if the uuid is correct
	*  <code>false</code> otherwise.
	*/
	public String getUUID(){
		return this.uuid;

	}
	
	/**Check if a given pin matches the true user pin**/
	/**return whether the pin is valid or not**/
	public boolean validatePin(String aPin){
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error, Caught NosuchAlgorithmException");
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
			
		}
		return false;
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s Accounts summary\n", this.firstName);
		for(int a = 0; a< this.accounts.size(); a++){
			System.out.printf("%d. %s\n Accounts summary\n",a+1, this.accounts.get(a).getSummaryLine());
		}
		// TODO Auto-generated method stub
		System.out.println();
	}

	public int numAccounts() {
		// TODO Auto-generated method stub
		return this.accounts.size();
	}
	/**
	 * 
	 * @param acctIdx
	 */
	public void printAcctTransHistory(int acctIdx){
		this.accounts.get(acctIdx).printTransHistory();
	}

	public double getAcctBalance(int acctIdx) {
		// TODO Auto-generated method stub
		
		return this.accounts.get(acctIdx).getBalance();
	}

	public String getAcctUUID(int acctIdx) {
		// TODO Auto-generated method stub
		return this.accounts.get(acctIdx).getUUID();
	}

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		// TODO Auto-generated method stub
		
		this.accounts.get(acctIdx).addTransaction(amount,memo);	
	}
}
