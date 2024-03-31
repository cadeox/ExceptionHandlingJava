package exceptionHandlingJava;

enum BalanceAction
{
	BalanceAction_Withdraw,
	BalanceAction_Deposit
}

public class Account
{
	Account(String accountOwnerSurname, String accountOwnerLastname, String accountNumber, String accountPassword, double openingBalance)
	{	
		m_ownerSurname = accountOwnerSurname;
		m_ownerLastname = accountOwnerLastname;
		m_accountNumber = accountNumber;
		m_accountPassword = accountPassword;
		m_accountBalance = openingBalance;
	}
	
	double changeAccountBalance(BalanceAction action, double amount)
	{
		switch(action)
		{
		case BalanceAction_Withdraw:
			if(m_accountBalance >= amount)
			{
				m_accountBalance -= amount;	
			}
			else
			{
				//TODO: place for error or exception
				//throw exception here maybe
			}
			break;
		case BalanceAction_Deposit:
			m_accountBalance += amount;
			break;
		default:
			break;
		}
		
		return m_accountBalance;
	}
	
	String getAccountNumber()
	{
		return m_accountNumber;
	}
	
	String getAccountPassword()
	{
		return m_accountPassword;
	}
	
	double getAccountBalance()
	{
		return m_accountBalance;
	}
	
	private String m_ownerSurname;
	private String m_ownerLastname;
	private String m_accountNumber;
	private String m_accountPassword;
	private double m_accountBalance;
}