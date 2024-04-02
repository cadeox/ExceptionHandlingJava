package exceptionHandlingJava;

import java.util.HashMap;
import java.util.Map;

enum TextTemplate
{
	ApplicationOpenningText,
	ApplicationClosureText,
	ApplicationStageOneText,
	ApplicationStageTwoText,
	
	CreationOfPrivateCustomerText,
	CashCheckOfPrivateCustomerText,
	
	GeneralWelcomeText,
	GeneralClosureText,
	BankServicesListText,
	BalanceCheckText,
	
	SystemRequestForPasswordText,
	SystemRequestForAccountNumberText,
	SystemRequestForDepositAmmountText,
	SystemRequestForDepositingOpenningBalanceText,
	SystemRequestForOpenningBalanceAmmountText,
	SystemRequestForCreatingNewPasswordText,
	SystemRequestForFurtherServicesText,
	SystemRequestForWithdrawAmmountText,
	SystemRequestForTransferAmmountText,
	SystemRequestForTransferAccountText,
	
	InvalidInputForServiceRequestText,
	InvalidPasswordEnteredText,
	InvalidYesOrNoInputText,
	InvalidAccountNumberEnteredText,
	InvalidInputGeneralText,
	InvalidTransferAccountNumberEnteredText,
	
	
	AccountUnderTheNameNotFoundOpenNewOneText,
	OutOfPasswordInputTrysText,
	OutOfAccountNumberInputTrysText,
	InsufficientFundsText,

	SuccessfullDepositText,
	SuccessfullWithdrawText,
	SuccessfullTransferText,
	SuccessfullCreationOfNewPasswordText,
	SuccessfullCreationOfNewAccountText,
	
}

public class Logger
{
	private Logger()
	{
		m_textTemplateMapping = new HashMap<>();
		String applicationOpenningText = "------------------------------------------------%n"
										+ "Welcome to the Bank Simulation Application!%n"
										+ "This Application is intendet for educational and demonstrational purpuses only.%n"
										+ "The primary goal of this Application is to show a functionability of Exceptions Handling in Java.%n"
										+ "Methods and classes of this application were intentionaly modified to accompliment Exceptions Handling Features inside the Programm.%n"
										+ "The Programm was build as a simulation of common day to day actions between banks and private customers.%n"
										+ "Please enjoy and have a nice day!%n"
										+ "------------------------------------------------%n";
		String applicationStageOneText = "Stage 1: Initialization of all needed Objects.%n"
										+ "This Stage of Application creates most of the Objects, that will be used in this Simulation.%n"
										+ "Some Objects will provide important information (like Account Numbers of newly created Accounts), "
										+ "that will be used during the simulation.%n"
										+ "------------------------------------------------%n";
		String applicationStageTwoText = "------------------------------------------------%n"
										+ "Stage 2: Simulation of Customer and Bank interactions.%n"
										+ "This Stage of Application will simulate interactions of createt Private Customers Objects with Bank Object.%n"
										+ "Each customer has different starting situations and need different interactions accordingly.%n"
										+ "This Stage is userinput sensitive and depends on knowledge of Information about Customers Accounts.%n"
										+ "------------------------------------------------%n";
		String applicationClosureText = "------------------------------------------------%n"
										+ "This is the End of Simulation.%n"
										+ "Thank you for using Bank Simulation Application!%n"
										+ "Have a nice day!%n"
										+ "------------------------------------------------%n";
		String creationOfPrivateCustomerText = "New Private Customer %s %s was created.%n";
		String cashCheckOfPrivateCustomerText = "%s's cash amount at the moment is %.2f EUR.%n";
		String bankServicesListText = "------------------------------------------------%n"
									+ "Welcome back %s %s!%n"
									+ "How can we help you?%n"
									+ "-For Balance Check please write \"Balance\".%n"
									+ "-For Money Withdraw please write \"Withdraw\".%n"
									+ "-For Money Deposit plaese write \"Deposit\".%n"
									+ "-For Money Transfer between acoounts please write \"Transfer\".%n"
									+ "-For Exiting the Application please write \"Exit\"%n"
									+ "------------------------------------------------%n";
		String invalidInputForServiceRequestText = "------------------------------------------------%n"
												+ "You have enteren invalid command: %s.%n"
												+ "Please enter valid service command: ";
		String systemRequestForPasswordText = "Please enter your Password: ";
		String outOfPasswordInputTrysText = "------------------------------------------------%n"
										+ "You've entered wrong Password and there are no more Retrys left.%n"
										+ "Please contact your personal customer service worker%n"
										+ "------------------------------------------------%n";
		String invalidPasswordEnteredText = "------------------------------------------------%n"
										+ "You've entered wrong Password.%nNumber of remaining trys: %d.%n"
										+ "Please repeat your Input: ";
		String systemRequestForAccountNumberText = "Please enter your Account Number: ";
		String outOfAccountNumberInputTrysText = "------------------------------------------------%n"
												+ "You've entered wrong Account Number and there are no more Retrys left.%n"
												+ "Please contact your personal customer service worker.%n"
												+ "------------------------------------------------%n";
		String invalidAccountNumberEnteredText = "------------------------------------------------%n"
												+ "You've entered wrong Account Number.%n"
												+ "Number of remaining trys: %d.%n"
												+ "Please repeat your Input: ";
		String generalWelcomeText = "Welcome to Sparkasse Bank!\n";
		String invalidYesOrNoInputText = "You have entered \"%s\".%n"
										+ "Please enter \"Yes\" or \"No\", if you would like to procede with opening a new Account: ";
		String accountUnderTheNameNotFoundOpenNewOneText = "It looks like, there is no Account under your name: %s %s.%n"
														+ "Would you like to open new Account?%n"
														+ "Please enter \"Yes\" or \"No\": ";
		String systemRequestForDepositAmmountText = "Account Balance now is: %.2f EUR.%n"
												+ "How much money would you like to deposit?%n"
												+ "Please enter the ammount:%n";
		String successfullDepositText = "You have successfuly deposit %.2f EUR to your Account.%n"
									+ "Current Account Balance is: %.2f EUR.%n";
		String insufficientFundsText = "Insufficient funds.%n"
									+ "Please decrease your deposit ammount:%n";
		String invalidInputGeneralText = "Unfortunately your Input %s was invalid.%n"
										+ "Please enter \"Yes\" or \"No\":%n";
		String systemRequestForFurtherServicesText = "Can we help you with something else?%n"
													+ "Please enter \"Yes\" or \"No\":%n";
		
		String systemRequestForWithdrawAmmountText = "Account Balance now is: %.2f EUR.%n"
													+ "How much money would you like to withdraw?%n"
													+ "Please enter the ammount:%n";
		String successfullWithdrawText = "You have successfuly withdraw %.2f EUR from your Account.%n"
										+ "Current Account Balance is: %.2f EUR.%n";
		String balanceCheckText = "Account Balance now is: %.2f EUR.%n";
		String generalClosureText = "Thank you for using Sparkasse Bank! Have a nice day!%n"
									+ "------------------------------------------------%n";
		String systemRequestForCreatingNewPasswordText = "------------------------------------------------%n"
														+ "Please create a Password for your Account: ";
		String successfullCreationOfNewPasswordText = "------------------------------------------------%n"
													+ "You have created following Password for your Account: %s%n";
		String systemRequestForDepositingOpenningBalanceText = "------------------------------------------------%n"
															+ "Would you like to deposit some opening balance to your Account?%n"
															+ "Please enter \"Yes\" or \"No\": ";
		String systemRequestForOpenningBalanceAmmountText = "------------------------------------------------%n"
															+ "How much would you like to deposit?%n"
															+ "Please enter wished deposit amount: ";
		String successfullCreationOfNewAccountText = "New Account with Opening Balance of %.2f EUR was created for %s %s with Number: %s and Password: %s.%n";
		String systemRequestForTransferAmmountText = "Account Balance now is: %.2f EUR.%n"
													+ "How much money would you like to transfer?%n"
													+ "Please enter the ammount:%n";
		String systemRequestForTransferAccountText = "What is the Account Number of Benefitiary?%n"
													+ "Please enter the Account Number:%n";
		
		String successfullTransferText = "You have successfuly transferd %.2f EUR to Account: %s.%n"
										+ "Current Account Balance is: %.2f EUR.%n";
		String invalidTransferAccountNumberEnteredText = "------------------------------------------------%n"
														+ "You've entered wrong Tranfrer Account Number: %s.%n"
														+ "Would you like to try again?%n"
														+ "Please enter \"Yes\" or \"No\": ";
		
		m_textTemplateMapping.put(TextTemplate.ApplicationOpenningText, applicationOpenningText);
		m_textTemplateMapping.put(TextTemplate.ApplicationStageOneText, applicationStageOneText);
		m_textTemplateMapping.put(TextTemplate.ApplicationStageTwoText, applicationStageTwoText);
		m_textTemplateMapping.put(TextTemplate.ApplicationClosureText, applicationClosureText);
		m_textTemplateMapping.put(TextTemplate.CreationOfPrivateCustomerText, creationOfPrivateCustomerText);
		m_textTemplateMapping.put(TextTemplate.CashCheckOfPrivateCustomerText, cashCheckOfPrivateCustomerText);
		m_textTemplateMapping.put(TextTemplate.BankServicesListText, bankServicesListText);
		m_textTemplateMapping.put(TextTemplate.InvalidInputForServiceRequestText, invalidInputForServiceRequestText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForPasswordText, systemRequestForPasswordText);
		m_textTemplateMapping.put(TextTemplate.OutOfPasswordInputTrysText, outOfPasswordInputTrysText);
		m_textTemplateMapping.put(TextTemplate.InvalidPasswordEnteredText, invalidPasswordEnteredText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForAccountNumberText, systemRequestForAccountNumberText);
		m_textTemplateMapping.put(TextTemplate.OutOfAccountNumberInputTrysText, outOfAccountNumberInputTrysText);
		m_textTemplateMapping.put(TextTemplate.InvalidAccountNumberEnteredText, invalidAccountNumberEnteredText);
		m_textTemplateMapping.put(TextTemplate.GeneralWelcomeText, generalWelcomeText);
		m_textTemplateMapping.put(TextTemplate.InvalidYesOrNoInputText, invalidYesOrNoInputText);
		m_textTemplateMapping.put(TextTemplate.AccountUnderTheNameNotFoundOpenNewOneText, accountUnderTheNameNotFoundOpenNewOneText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForDepositAmmountText, systemRequestForDepositAmmountText);
		m_textTemplateMapping.put(TextTemplate.SuccessfullDepositText, successfullDepositText);
		m_textTemplateMapping.put(TextTemplate.InsufficientFundsText, insufficientFundsText);
		m_textTemplateMapping.put(TextTemplate.InvalidInputGeneralText, invalidInputGeneralText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForFurtherServicesText, systemRequestForFurtherServicesText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForWithdrawAmmountText, systemRequestForWithdrawAmmountText);
		m_textTemplateMapping.put(TextTemplate.SuccessfullWithdrawText, successfullWithdrawText);
		m_textTemplateMapping.put(TextTemplate.BalanceCheckText, balanceCheckText);
		m_textTemplateMapping.put(TextTemplate.GeneralClosureText, generalClosureText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForCreatingNewPasswordText, systemRequestForCreatingNewPasswordText);
		m_textTemplateMapping.put(TextTemplate.SuccessfullCreationOfNewPasswordText, successfullCreationOfNewPasswordText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForDepositingOpenningBalanceText, systemRequestForDepositingOpenningBalanceText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForOpenningBalanceAmmountText, systemRequestForOpenningBalanceAmmountText);
		m_textTemplateMapping.put(TextTemplate.SuccessfullCreationOfNewAccountText, successfullCreationOfNewAccountText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForTransferAmmountText, systemRequestForTransferAmmountText);
		m_textTemplateMapping.put(TextTemplate.SystemRequestForTransferAccountText, systemRequestForTransferAccountText);
		m_textTemplateMapping.put(TextTemplate.SuccessfullTransferText, successfullTransferText);
		m_textTemplateMapping.put(TextTemplate.InvalidTransferAccountNumberEnteredText, invalidTransferAccountNumberEnteredText);
	}
	
	//Singleton Instance for Object of Class Logger, for programm-wide reach.
	public static Logger getInstance() 
	{
        if (m_instance == null)
        {
            m_instance = new Logger();
        }
        return m_instance;
	}
	
	//Different printing Methods
	void print(TextTemplate templateType)
	{
		System.out.printf(m_textTemplateMapping.get(templateType));
	}
	
	void print(TextTemplate templateType, String additionalInput)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInput);
	}
	
	void print(TextTemplate templateType, double additionalInput)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInput);
	}
	
	void print(TextTemplate templateType, int additionalInput)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInput);
	}
	
	void print(TextTemplate templateType, String additionalInputOne, String additionalInputTwo)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInputOne, additionalInputTwo);
	}
	
	void print(TextTemplate templateType, String additionalInputOne, double additionalInputTwo)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInputOne, additionalInputTwo);
	}
	
	void print(TextTemplate templateType, double additionalInputOne, double additionalInputTwo)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInputOne, additionalInputTwo);
	}
	
	void print(TextTemplate templateType, double additionalInputOne, String additionalInputTwo, String additionalInputThree, String additionalInputFour, String additionalInputFive)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInputOne, additionalInputTwo, additionalInputThree, additionalInputFour, additionalInputFive);
	}
	
	void print(TextTemplate templateType, double additionalInputOne, String additionalInputTwo, double additionalInputThree)
	{
		System.out.printf(m_textTemplateMapping.get(templateType), additionalInputOne, additionalInputTwo, additionalInputThree);
	}
	
	//Class Logger Member Fields
	private Map<TextTemplate, String> m_textTemplateMapping;
	private static Logger m_instance;
}