package exceptionHandlingJava;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

enum BankService
{
	BankService_BalanceCheck,
	BankService_Withdraw,
	BankService_Deposit,
	BankService_Transfer,
	BankService_CloseService
}

public class Bank
{
	//Constructor for Object of class Bank
	Bank()
	{
		m_savedAccounts = new HashMap<>();
		m_servicesList = new HashMap<>();
		m_randomizer = new Random();
		
		m_servicesList.put("Balance Check", BankService.BankService_BalanceCheck);
		m_servicesList.put("Withdraw", BankService.BankService_Withdraw);
		m_servicesList.put("Deposit", BankService.BankService_Deposit);
		m_servicesList.put("Transfer", BankService.BankService_Transfer);
		m_servicesList.put("Exit", BankService.BankService_CloseService);
	}
	
	//Methods for Interactions between Private Customers and Bank Services
	void createNewAccount(String surname, String lastname, String password, double openingBalance)
	{
		String accountNumber = generateNewAccountNumber();
		Account account = new Account(surname, lastname, accountNumber, password, openingBalance);
		m_savedAccounts.put(accountNumber, account);
		System.out.printf("New Account was created for %s %s with Number %s.%n", surname, lastname, accountNumber);
	}

	void serveCustomer(PrivateCustomer customer)
	{
		Scanner customerInputScanner =new Scanner(System.in);
		
		if(logIntoAccount(customerInputScanner))
		{
			System.out.printf("Welcome back %s %s!%n"
							+ "How can we help you?%n"
							+ "-For Balance Check please write \"Balance Check\".%n"
							+ "-For Money Withdraw please write \"Withdraw\".%n"
							+ "-For Money Deposit plaese write \"Deposit\".%n"
							+ "-For Money Transfer between acoounts please write \"Transfer\".%n"
							+ "-For Exiting the Application please write \"Exit\"%n",
							customer.m_surname, customer.m_lastname);
			String serviceRequest = customerInputScanner.next();
			while(!m_servicesList.containsKey(serviceRequest))
			{
				System.out.printf("You have enteren invalid command: %s.%n"
								+ "Please enter valid service command: ", serviceRequest);
				serviceRequest = customerInputScanner.next();
			}
			switch(m_servicesList.get(serviceRequest))
			{
			case BankService_BalanceCheck:
				checkBalanceOfAccount();
				break;
			case BankService_Withdraw:
				withdrawMoneyFromAccount();
				break;
			case BankService_Deposit:
				depositMoneyToAccount();
				break;
			case BankService_Transfer:
				transferMoneyToDifferentAccount();
				break;
			case BankService_CloseService:
				logOutOfAccount();
				break;
			default:
				break;
			}
		}
		else
		{
			logOutOfAccount();
		}
			
	}

	private boolean logIntoAccount(Scanner scanner)
	{
		boolean result = checkLoginToAccount(scanner);
		
		
		
		return result;
	}

	private void logOutOfAccount()
	{
		System.out.printf("Thank you for using Sparkasse Bank! Have a nice day!%n");
	}
	
	private void checkBalanceOfAccount()
	{
		
	}
	
	private void withdrawMoneyFromAccount()
	{
		
	}
	
	private void depositMoneyToAccount()
	{
		
	}
	
	private void transferMoneyToDifferentAccount()
	{
		
	}
	
	private String generateNewAccountNumber()
	{
		String accountNumber;
		do
		{
			StringBuilder accountNumberBuilder = new StringBuilder();
			for (int i = 0; i < 10; i++)
			{
				accountNumberBuilder.append(m_randomizer.nextInt(9));
			}
			accountNumber = accountNumberBuilder.toString();
		}
		while (m_savedAccounts.containsKey(accountNumber));
		return accountNumber;
	}
	
	private boolean checkLoginToAccount(Scanner scanner)
	{
		String inputAccountNumber = null;
		boolean result = false;
		int inputTrys = 0;
		
		System.out.print("Welcome to Sparkasse Bank!\nPlease enter your Account Number: ");
		
		while(result == false)
		{
			if(inputTrys >= 3) 
			{
				System.out.printf("You've entered wrong Account Number and there are no more Retrys left.%n"
								+ "Please contact your personal customer service worker%n");
				break;
			}
			else
			{
				if(inputTrys > 0)
				{
					System.out.printf("You've entered wrong Account Number.%nNumber of remaining trys: %d.%n"
									+ "Please repeat your Input: ", 3 - inputTrys);
				}
				inputAccountNumber = scanner.next();
				++inputTrys;
				result = m_savedAccounts.containsKey(inputAccountNumber);
				if(result == true)
				{
					boolean subresult = checkLoginPassword(scanner, m_savedAccounts.get(inputAccountNumber));
					if(!subresult)
					{
						result = subresult;
						break;
					}
				}
			}
		}
		return result;	
	}
	
	private boolean checkLoginPassword(Scanner scanner, Account account)
	{
		String inputAccountPassword = null;
		boolean result = false;
		int inputTrys = 0;
		
		System.out.print("Please enter your Password: ");
		
		while(result == false)
		{
			if(inputTrys >= 3) 
			{
				System.out.printf("You've entered wrong Password and there are no more Retrys left.%n"
								+ "Please contact your personal customer service worker%n");
				break;
			}
			else
			{
				if(inputTrys > 0)
				{
					System.out.printf("You've entered wrong Password.%nNumber of remaining trys: %d.%n"
									+ "Please repeat your Input: ", 3 - inputTrys);
				}
				inputAccountPassword = scanner.next();
				++inputTrys;
				result = account.checkAccountPassword(inputAccountPassword);
			}
		}
		return result;	
	}
	
	//Member Fields for every Object of class Bank
	private Map<String, Account> m_savedAccounts;
	private Map<String, BankService> m_servicesList;
	private Random m_randomizer;
}