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
	BankService_CloseService,
	BankService_Default
}

public class Bank
{
	//Constructor for Object of class Bank
	Bank()
	{
		m_savedAccounts = new HashMap<>();
		m_lastnameToAccountMapping = new HashMap<>();
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
			boolean isServiceRequired = true;
			while(isServiceRequired)
			{
				BankService service= acquierCustomerServiceRequest(customer, customerInputScanner);
				switch(service)
				{
				
				case BankService_BalanceCheck:
					isServiceRequired = checkBalanceOfAccount(customer, customerInputScanner);
					break;
				case BankService_Withdraw:
					isServiceRequired = withdrawMoneyFromAccount(customer, customerInputScanner);
					break;
				case BankService_Deposit:
					isServiceRequired = depositMoneyToAccount(customer, customerInputScanner);
					break;
				case BankService_Transfer:
					isServiceRequired = transferMoneyToDifferentAccount(customer, customerInputScanner);
					break;
				default:
					break;
				}
			}
		}
		logOutOfAccount();
	}
	
	void createNewAccount(String surname, String lastname, String password, double openingBalance)
	{
		String accountNumber = generateNewAccountNumber();
		Account account = new Account(surname, lastname, accountNumber, password, openingBalance);
		m_savedAccounts.put(accountNumber, account);
		m_lastnameToAccountMapping.put(lastname, accountNumber);
		Logger.getInstance().print(TextTemplate.SuccessfullCreationOfNewAccountText,
									openingBalance, surname, lastname, accountNumber, password);
	}
	
	private void createNewAccount(PrivateCustomer customer, Scanner scanner)
	{
		String accountNumber = generateNewAccountNumber();
		String accountPassword = null;
		double openingBalance = 0;
		Logger.getInstance().print(TextTemplate.SystemRequestForCreatingNewPasswordText);
		accountPassword = scanner.next();
		Logger.getInstance().print(TextTemplate.SuccessfullCreationOfNewPasswordText);
		Logger.getInstance().print(TextTemplate.SystemRequestForDepositingOpenningBalanceText, accountPassword);
		while(true)
		{
			String customerReply = scanner.next();
			if(customerReply.equals("Yes"))
			{
				Logger.getInstance().print(TextTemplate.SystemRequestForOpenningBalanceAmmountText);
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
						Logger.getInstance().print(TextTemplate.InsufficientFundsText);
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
				Logger.getInstance().print(TextTemplate.InvalidYesOrNoInputText,
											customerReply);
			}
		}
		
		Account account = new Account(customer.m_surname, customer.m_lastname, accountNumber, accountPassword, openingBalance);
		m_savedAccounts.put(accountNumber, account);
		m_lastnameToAccountMapping.put(customer.m_lastname, accountNumber);
		Logger.getInstance().print(TextTemplate.SuccessfullCreationOfNewAccountText,
									openingBalance, customer.m_surname, customer.m_lastname, accountNumber, accountPassword);
	}

	private void logOutOfAccount()
	{
		Logger.getInstance().print(TextTemplate.GeneralClosureText);
	}
	
	private boolean checkBalanceOfAccount(PrivateCustomer customer, Scanner scanner)
	{
		boolean isServiceRequired = true;
		Logger.getInstance().print(TextTemplate.BalanceCheckText,
									m_savedAccounts.get(customer.m_lastname).getAccountBalance());
		Logger.getInstance().print(TextTemplate.SystemRequestForFurtherServicesText);
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
				isServiceRequired = false;
				break;
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
											nextStepInput);
			}
		}
		return isServiceRequired;
	}
	
	private boolean withdrawMoneyFromAccount(PrivateCustomer customer, Scanner scanner)
	{
		boolean isServiceRequired = true;
		double withdrawAmmount;
		String nextStepInput;
		Logger.getInstance().print(TextTemplate.SystemRequestForWithdrawAmmountText,
									m_savedAccounts.get(customer.m_lastname).getAccountBalance());
		while(true)
		{
			//TODO check for negative inputs and strings as input
			withdrawAmmount = scanner.nextDouble();
			if(withdrawAmmount <= m_savedAccounts.get(customer.m_lastname).getAccountBalance())
			{
				m_savedAccounts.get(customer.m_lastname).changeAccountBalance(BalanceAction.BalanceAction_Withdraw, withdrawAmmount);
				customer.m_cash += withdrawAmmount;
				Logger.getInstance().print(TextTemplate.SuccessfullWithdrawText,
											withdrawAmmount, m_savedAccounts.get(customer.m_lastname).getAccountBalance());
				break;
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
											String.valueOf(withdrawAmmount));
			}
		}
		Logger.getInstance().print(TextTemplate.SystemRequestForFurtherServicesText);
		while(true)
		{
			nextStepInput = scanner.next();
			if(nextStepInput.equals("Yes"))
			{
				break;
			}
			else if(nextStepInput.equals("No"))
			{
				isServiceRequired = false;
				break;
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
											nextStepInput);
			}
		}
		return isServiceRequired;
	}
	
	private boolean depositMoneyToAccount(PrivateCustomer customer, Scanner scanner)
	{
		boolean isServiceRequired = true;
		double depositAmmount;
		String nextStepInput;
		Logger.getInstance().print(TextTemplate.SystemRequestForDepositAmmountText, 
								m_savedAccounts.get(s_sessionAccountNumber).getAccountBalance());

		while(true)
		{
			//TODO check for negative inputs and strings as input
			depositAmmount = scanner.nextDouble();
			if(depositAmmount <= customer.m_cash)
			{
				m_savedAccounts.get(s_sessionAccountNumber).changeAccountBalance(BalanceAction.BalanceAction_Deposit, depositAmmount);
				customer.m_cash -= depositAmmount;
				Logger.getInstance().print(TextTemplate.SuccessfullDepositText,
								depositAmmount, m_savedAccounts.get(s_sessionAccountNumber).getAccountBalance());
				break;
			}
			else if(depositAmmount > customer.m_cash)
			{
				Logger.getInstance().print(TextTemplate.InsufficientFundsText);
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
											String.valueOf(depositAmmount));
			}
		}
		Logger.getInstance().print(TextTemplate.SystemRequestForFurtherServicesText);
		while(true)
		{
			nextStepInput = scanner.next();
			if(nextStepInput.equals("Yes"))
			{
				break;
			}
			else if(nextStepInput.equals("No"))
			{
				isServiceRequired = false;
				break;
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
											nextStepInput);
			}
		}
		return isServiceRequired;
	}
	
	//TODO
	private boolean transferMoneyToDifferentAccount(PrivateCustomer customer, Scanner scanner)
	{
		boolean isServiceRequired = true;
		double transferAmmount;
		String accountToTransferTo;
		String nextStepInput;
		Logger.getInstance().print(TextTemplate.SystemRequestForTransferAmmountText, 
								m_savedAccounts.get(s_sessionAccountNumber).getAccountBalance());
		while(true)
		{
			//TODO check for negative inputs and strings as input
			transferAmmount = scanner.nextDouble();
			if(transferAmmount <= m_savedAccounts.get(s_sessionAccountNumber).getAccountBalance())
			{
				break;
			}
			else if(transferAmmount > m_savedAccounts.get(s_sessionAccountNumber).getAccountBalance())
			{
				Logger.getInstance().print(TextTemplate.InsufficientFundsText);
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
											String.valueOf(transferAmmount));
			}
		}
		Logger.getInstance().print(TextTemplate.SystemRequestForTransferAccountText);
		while(true)
		{
			//TODO check for negative inputs and strings as input
			accountToTransferTo = scanner.next();
			if(m_savedAccounts.containsKey(accountToTransferTo))
			{
				m_savedAccounts.get(s_sessionAccountNumber).changeAccountBalance(BalanceAction.BalanceAction_Withdraw, transferAmmount);
				m_savedAccounts.get(accountToTransferTo).changeAccountBalance(BalanceAction.BalanceAction_Deposit, transferAmmount);
				Logger.getInstance().print(TextTemplate.SuccessfullTransferText,
						transferAmmount, accountToTransferTo, m_savedAccounts.get(s_sessionAccountNumber).getAccountBalance());
				break;
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidTransferAccountNumberEnteredText,
											accountToTransferTo);
				while(true)
				{
					nextStepInput = scanner.next();
					if(nextStepInput.equals("Yes"))
					{
						transferMoneyToDifferentAccount(customer, scanner);
						break;
					}
					else if(nextStepInput.equals("No"))
					{
						isServiceRequired = false;
						break;
					}
					else
					{
						Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
													nextStepInput);
					}
					break;
				}
				break;
			}
		}
		Logger.getInstance().print(TextTemplate.SystemRequestForFurtherServicesText);
		while(true)
		{
			nextStepInput = scanner.next();
			if(nextStepInput.equals("Yes"))
			{
				break;
			}
			else if(nextStepInput.equals("No"))
			{
				isServiceRequired = false;
				break;
			}
			else
			{
				Logger.getInstance().print(TextTemplate.InvalidInputGeneralText,
											nextStepInput);
			}
		}
		return isServiceRequired;
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
			Logger.getInstance().print(TextTemplate.AccountUnderTheNameNotFoundOpenNewOneText,
										customer.m_surname, customer.m_lastname);
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
					break;
				}
				else
				{
					Logger.getInstance().print(TextTemplate.InvalidYesOrNoInputText, customerReply);
				}
			}
		}
		return result;
	}
	
	private boolean checkAccountExistence(PrivateCustomer customer)
	{
		boolean result = false;
		Logger.getInstance().print(TextTemplate.GeneralWelcomeText);
		if(m_lastnameToAccountMapping.containsKey(customer.m_lastname))
		{
			result = true;
			s_sessionAccountNumber = m_savedAccounts.get(m_lastnameToAccountMapping.get(customer.m_lastname)).getAccountNumber();
			s_sessionAccountPassword = m_savedAccounts.get(s_sessionAccountNumber).getAccountPassword();
		}
		return result;
	}
	
	private boolean checkLoginAccount(Scanner scanner)
	{
		boolean result = false;
		int inputTrys = 3;
		
		Logger.getInstance().print(TextTemplate.SystemRequestForAccountNumberText);
		
		while(result == false)
		{
			if(inputTrys <= 0)
			{
				Logger.getInstance().print(TextTemplate.OutOfAccountNumberInputTrysText);
				break;
			}
			String inputAccountNumber = scanner.next();
			--inputTrys;
			if(s_sessionAccountNumber.equals(inputAccountNumber))
			{
				result = true;
				break;
			}
			else if(inputTrys > 0)
			{
				Logger.getInstance().print(TextTemplate.InvalidAccountNumberEnteredText, inputTrys);
			}
		}
		return result;
	}
		
	private boolean checkLoginPassword(Scanner scanner)
	{
		boolean result = false;
		int inputTrys = 3;
		
		Logger.getInstance().print(TextTemplate.SystemRequestForPasswordText);
		
		while(result == false)
		{
			if(inputTrys <= 0)
			{
				Logger.getInstance().print(TextTemplate.OutOfPasswordInputTrysText);
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
				Logger.getInstance().print(TextTemplate.InvalidPasswordEnteredText, inputTrys);
			}
		}
		return result;
	}
	
	private BankService acquierCustomerServiceRequest(PrivateCustomer customer, Scanner scanner)
	{
		BankService chosenRequest = BankService.BankService_Default;
		Logger.getInstance().print(TextTemplate.BankServicesListText, customer.m_surname, customer.m_lastname);
		String serviceRequest = scanner.next();
		while(!m_servicesList.containsKey(serviceRequest))
		{
			Logger.getInstance().print(TextTemplate.InvalidInputForServiceRequestText, serviceRequest);
			serviceRequest = scanner.next();
		}
		switch(m_servicesList.get(serviceRequest))
		{
		case BankService_BalanceCheck:
			chosenRequest = BankService.BankService_BalanceCheck;
			break;
		case BankService_Withdraw:
			chosenRequest = BankService.BankService_Withdraw;
			break;
		case BankService_Deposit:
			chosenRequest = BankService.BankService_Deposit;
			break;
		case BankService_Transfer:
			chosenRequest = BankService.BankService_Transfer;
			break;
		case BankService_CloseService:
			chosenRequest = BankService.BankService_CloseService;
			break;
		default:
			break;
		}
		return chosenRequest;
	}
	
	//Member Fields for every Object of class Bank
	private Map<String, Account> m_savedAccounts;
	private Map<String, String> m_lastnameToAccountMapping;
	private Map<String, BankService> m_servicesList;
	private Random m_randomizer;
	
	private static String s_sessionAccountNumber;
	private static String s_sessionAccountPassword;
}