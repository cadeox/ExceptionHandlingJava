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
		
		m_servicesList.put("Balance", BankService.BankService_BalanceCheck);
		m_servicesList.put("Withdraw", BankService.BankService_Withdraw);
		m_servicesList.put("Deposit", BankService.BankService_Deposit);
		m_servicesList.put("Transfer", BankService.BankService_Transfer);
		m_servicesList.put("Exit", BankService.BankService_CloseService);
	}
	
	//Methods for Interactions between Private Customers and Bank Services
	void serveCustomer(PrivateCustomer customer)
	{
		Scanner customerInputScanner = new Scanner(System.in);
		
		if(isCustomerAuthenticationSuccessful(customer, customerInputScanner))
		{
			
			System.out.printf("------------------------------------------------%n"
							+ "Welcome back %s %s!%n"
							+ "How can we help you?%n"
							+ "-For Balance Check please write \"Balance\".%n"
							+ "-For Money Withdraw please write \"Withdraw\".%n"
							+ "-For Money Deposit plaese write \"Deposit\".%n"
							+ "-For Money Transfer between acoounts please write \"Transfer\".%n"
							+ "-For Exiting the Application please write \"Exit\"%n"
							+ "------------------------------------------------%n",
							customer.m_surname, customer.m_lastname);
			String serviceRequest = customerInputScanner.next();
			
			while(!m_servicesList.containsKey(serviceRequest))
			{
				System.out.printf("------------------------------------------------%n"
								+ "You have enteren invalid command: %s.%n"
								+ "Please enter valid service command: ", serviceRequest);
				serviceRequest = customerInputScanner.next();
			}
			switch(m_servicesList.get(serviceRequest))
			{
			case BankService_BalanceCheck:
				checkBalanceOfAccount(customerInputScanner);
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
	
	void createNewAccount(String surname, String lastname, String password, double openingBalance)
	{
		String accountNumber = generateNewAccountNumber();
		Account account = new Account(surname, lastname, accountNumber, password, openingBalance);
		m_savedAccounts.put(lastname, account);
		System.out.printf("New Account with Opening Balance of %.2f EUR was created for %s %s with Number: %s and Password: %s.%n",
							openingBalance, surname, lastname, accountNumber, password);
	}
	
	void createNewAccount(PrivateCustomer customer, Scanner scanner)
	{
		String accountNumber = generateNewAccountNumber();
		String accountPassword = null;
		double openingBalance = 0;
		
		System.out.printf("------------------------------------------------%n"
						+ "Please create a Password for your Account: ");
		accountPassword = scanner.next();
		System.out.printf("------------------------------------------------%n"
						+ "You have created following Password for your Account: %s%n"
						+ "------------------------------------------------%n"
						+ "Would you like to deposit some opening balance to your Account?%n"
						+ "Please enter \"Yes\" or \"No\": ",
						accountPassword);
		while(true)
		{
			String customerReply = scanner.next();
			if(customerReply.equals("Yes"))
			{
				System.out.printf("------------------------------------------------%n"
						+ "How much would you like to deposit?%n"
						+ "Please enter wished deposit amount: ");
				while(true)
				{
					openingBalance = scanner.nextDouble();
					
					if(openingBalance <= customer.m_cash)
					{
						customer.m_cash -= openingBalance;
						break;
					}
					else
					{
						System.out.printf("------------------------------------------------%n"
								+ "Not sufficient funds.%n"
								+ "Please enter wished deposit amount: ");
					}
				}
				
				break;
			}
			else if(customerReply.equals("No"))
			{
				break;
			}
			else
			{
				System.out.printf("You have entered \"%s\".%n"
								+ "Please enter \"Yes\" or \"No\", if you would like to deposit some opening balance to your Account: ",
								customerReply);
			}
		}
		
		Account account = new Account(customer.m_surname, customer.m_lastname, accountNumber, accountPassword, openingBalance);
		m_savedAccounts.put(customer.m_lastname, account);
		System.out.printf("New Account with Opening Balance of %.2f EUR was created for %s %s with Number: %s and Password: %s.%n",
							openingBalance, customer.m_surname, customer.m_lastname, accountNumber, accountPassword);
	}

	private void logOutOfAccount()
	{
		System.out.printf("Thank you for using Sparkasse Bank! Have a nice day!%n"
						+ "------------------------------------------------%n");
	}
	
	private void checkBalanceOfAccount(Scanner scanner)
	{
		System.out.printf("Account Balance now is: %.2f%nCan we help you with somthing else?%nPlease enter \"Yes\" od \"No\":%n", 
						m_savedAccounts.get(s_sessionAccountNumber).getAccountBalance());
		String nextStepInput;
		while(true)
		{
			nextStepInput = scanner.next();
			if(nextStepInput.equals("Yes"))
			{
				
				break;
			}
			else if(nextStepInput.equals("No"))
			{
				logOutOfAccount();
				break;
			}
			else
			{
				System.out.printf("Unfortunately your Input %s was invalid.%nPlease enter \"Yes\" or \"No\":%n", nextStepInput);
			}	
		}
		
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
	
	private boolean isCustomerAuthenticationSuccessful(PrivateCustomer customer, Scanner scanner)
	{
		boolean result = false;
		if(checkAccountExistence(customer))
		{
			if(checkLoginAccount(scanner))
			{
				if(checkLoginPassword(scanner))
				{
					result = true;
				}
			}
		}
		else
		{
			System.out.printf("It looks like, there is no Account under your name: %s %s.%n"
							+ "Would you like to open new Account?%n"
							+ "Please enter \"Yes\" or \"No\": ", customer.m_surname, customer.m_lastname);
			while(true)
			{
				String customerReply = scanner.next();
				if(customerReply.equals("Yes"))
				{
					createNewAccount(customer, scanner);
					result = true;
					break;
				}
				else if(customerReply.equals("No"))
				{
					logOutOfAccount();
					break;
				}
				else
				{
					System.out.printf("You have entered \"%s\".%n"
									+ "Please enter \"Yes\" or \"No\", if you would like to procede with opening a new Account: ",
									customerReply);
				}
			}
		}
		return result;
	}
	
	private boolean checkAccountExistence(PrivateCustomer customer)
	{
		boolean result = false;
		System.out.printf("Welcome to Sparkasse Bank!\n");
		if(m_savedAccounts.containsKey(customer.m_lastname))
		{
			result = true;
			s_sessionAccountNumber = m_savedAccounts.get(customer.m_lastname).getAccountNumber();
			s_sessionAccountPassword = m_savedAccounts.get(customer.m_lastname).getAccountPassword();
		}
		return result;
	}
	
	private boolean checkLoginAccount(Scanner scanner)
	{
		boolean result = false;
		int inputTrys = 3;
		
		System.out.printf("Please enter your Account Number: ");
		
		while(result == false)
		{
			if(inputTrys <= 0)
			{
				System.out.printf("------------------------------------------------%n"
						+ "You've entered wrong Account Number and there are no more Retrys left.%n"
						+ "Please contact your personal customer service worker.%n"
						+ "------------------------------------------------%n");
				break;
			}
			String inputAccountNumber = scanner.next();
			--inputTrys;
			//if(inputAccountNumber.equals(s_sessionAccountNumber))
			if(s_sessionAccountNumber.equals(inputAccountNumber))
			{
				result = true;
				break;
			}
			else if(inputTrys > 0)
			{
				System.out.printf("------------------------------------------------%n"
						+ "You've entered wrong Account Number.%nNumber of remaining trys: %d.%n"
						+ "Please repeat your Input: ", inputTrys);
			}
		}
		return result;
	}
		
	private boolean checkLoginPassword(Scanner scanner)
	{
		boolean result = false;
		int inputTrys = 3;
		
		System.out.printf("Please enter your Password: ");
		
		while(result == false)
		{
			if(inputTrys <= 0)
			{
				System.out.printf("------------------------------------------------%n"
						+ "You've entered wrong Password and there are no more Retrys left.%n"
						+ "Please contact your personal customer service worker%n"
						+ "------------------------------------------------%n");
				break;
			}
			String inputAccountPassword = scanner.next();
			--inputTrys;
			if(inputAccountPassword.equals(s_sessionAccountPassword))
			{
				result = true;
				break;
			}
			else if(inputTrys > 0)
			{
				System.out.printf("------------------------------------------------%n"
						+ "You've entered wrong Password.%nNumber of remaining trys: %d.%n"
						+ "Please repeat your Input: ", inputTrys);
			}
		}
		return result;
	}
	
	//Member Fields for every Object of class Bank
	private Map<String, Account> m_savedAccounts;
	private Map<String, BankService> m_servicesList;
	private Random m_randomizer;
	
	private static String s_sessionAccountNumber;
	private static String s_sessionAccountPassword;
}