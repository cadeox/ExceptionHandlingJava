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
	
	double getAccountBalance()
	{
		return m_accountBalance;
	}
	
	boolean checkAccountPassword(String password)
	{
		boolean result = false;
		if(password.equals(m_accountPassword))
		{
			result = true;
		}
		return result;
	}
	
	private String m_ownerSurname;
	private String m_ownerLastname;
	private String m_accountNumber;
	private String m_accountPassword;
	private double m_accountBalance;
}