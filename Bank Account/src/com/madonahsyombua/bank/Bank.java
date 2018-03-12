package com.madonahsyombua.bank;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author syombua
 *
 */
public class Bank 
{
/*class bank the Constructer takes attribute name, an ArrayList of @User class and @Account */
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	//creat anew bank object with list empty
	public Bank(String name)
	{
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	/** generate a new universally unique ID for a user**/
	public String getNewUserUUID()
	{
		
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;
		
		/**continue looping until we get a new unique ID**/
		do{
			//generate the number
			uuid = "";
			for (int c = 0; c<len; c++)
			{
				
				uuid+= ((Integer)rng.nextInt(10)).toString();
			}
			/**check to makesure its unique**/
			nonUnique = false;
			for (User u : this.users)
			{
				if(uuid.compareTo(u.getUUID())== 0)
				{
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
		
	}
	//generate unique IDs for each account
	public String getNewAccontUUID()
	{

		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique;
		
		/***continue looping until we get a new unique ID**/
		do{
			//generate the number
			uuid = "";
			for (int c = 0; c<len; c++)
			{
				
				uuid+= ((Integer)rng.nextInt(10)).toString();
			}
			//check to makesure if unique
			nonUnique = false;
			for (Account a : this.accounts)
			{
				if(uuid.compareTo(a.getUUID())== 0)
				{
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
		
	}
	
	public void addAccount(Account onAcct)
	{
		this.accounts.add(onAcct);		
	}
	
	public User addUser(String firstName, String lastName , String pin)
	{
		//Create a new user object and add it to our list
		
		User newUser = new User(firstName, lastName,pin, this);
		this.users.add(newUser);
		
		/**create a savings account for the user and add to user**/
		
		Account newAccount = new Account("Savings", newUser, this);
		   newUser.addAccount(newAccount);
	       this.addAccount(newAccount);
	       
	       return newUser;
	       }
	
	public User userLogin(String userID, String pin)
	{
		
		/**search through the list of users**/
		for(User u : this.users)
		{
			
			//check user ID is correct
			if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin))
			{
				return u;
			}
		}
		//if we havent found the pin
		return null;
	}
	/**
	* getName gives a status report 
	* 
	* @return <code>true</code> if the name is correct
	*  <code>false</code> otherwise.
	*/
	public String getName()
	 {
		// TODO Auto-generated method stub
		return this.name;
	}
}