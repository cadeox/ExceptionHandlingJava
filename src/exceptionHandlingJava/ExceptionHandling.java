package exceptionHandlingJava;

public class ExceptionHandling
{
	//Method for printing an Application Introduction Text
	private static void printApplicationIntoduction()
	{
		System.out.printf("------------------------------------------------%n"
						+ "Welcome to the Bank Simulation Application!%n"
						+ "This Application is intendet for educational and demonstrational purpuses only.%n"
						+ "The primary goal of this Application is to show a functionability of Exceptions Handling in Java.%n"
						+ "Methods and classes of this application were intentionaly modified to accompliment Exceptions Handling Features inside the Programm.%n"
						+ "The Programm was build as a simulation of common day to day actions between banks and private customers.%n"
						+ "Please enjoy and have a nice day!%n"
						+ "------------------------------------------------%n");
	}
	
	//Method for printing the Information for Stage 1: Object Initialization
	private static void printStageOneInformation()
	{
		System.out.printf("Stage 1: Initialization of all needed Objects.%n"
						+ "This Stage of Application creates most of the Objects, that will be used in this Simulation.%n"
						+ "Some Objects will provide important information (like Account Numbers of newly created Accounts), "
						+ "that will be used during the simulation.%n"
						+ "------------------------------------------------%n");
	}
	
	//Method for printing the Information for Stage 1: Object Initialization
	private static void printStageTwoInformation()
	{
		System.out.printf("------------------------------------------------%n"
						+ "Stage 2: Simulation of Customer and Bank interactions.%n"
						+ "This Stage of Application will simulate interactions of createt Private Customers Objects with Bank Object.%n"
						+ "Each customer has different starting situations and need different interactions accordingly.%n"
						+ "This Stage is userinput sensitive and depends on knowledge of Information about Customers Accounts.%n"
						+ "------------------------------------------------%n");
	}
		
	//Method for printing an Application Closure Text
		private static void printApplicationClosure()
		{
			System.out.printf("------------------------------------------------%n"
							+ "This is the End of Simulation.%n"
							+ "Thank you for using Bank Simulation Application!%n"
							+ "Have a nice day!%n"
							+ "------------------------------------------------%n");
		}	
	
	
	public static void main(String[] args)
	{
		//0) Printing start information.
		printApplicationIntoduction();
		
		//1) Stage 1: Objects Initialization.
		//1.0) Printing Stage 1 Information.
		printStageOneInformation();
		
		//1.1) Creating all customers Objects and calling there checkCash() methods.
		PrivateCustomer maxMusterman = new PrivateCustomer("Max", "Musterman", 0);
		PrivateCustomer lisaMusterfrau = new PrivateCustomer("Lisa", "Musterfrau", 1000);
		PrivateCustomer fabianMüller = new PrivateCustomer("Fabian", "Müller", 200);
		
		maxMusterman.checkCash();
		lisaMusterfrau.checkCash();
		fabianMüller.checkCash();
		
		//1.2) Creating bank Object.
		Bank sparkasse = new Bank();
		
		//1.3) Creating all account Objects.
		sparkasse.createNewAccount(maxMusterman.m_surname, maxMusterman.m_lastname, "max1234", 5000);
		sparkasse.createNewAccount(lisaMusterfrau.m_surname, lisaMusterfrau.m_lastname, "lisa5678", 0);

		//2) Stage 2: Simulation of Customer and Bank interactions.
		//2.0) Printing Stage 2 Information.
		printStageTwoInformation();
		
		//2.1) Customer Max Musterman.
		sparkasse.serveCustomer(maxMusterman);
		maxMusterman.checkCash();
		
		//2.2) Customer Lisa Musterman.
		sparkasse.serveCustomer(lisaMusterfrau);
		lisaMusterfrau.checkCash();
		
		//2.3) Customer Fabian Müller.
		sparkasse.serveCustomer(fabianMüller);
		fabianMüller.checkCash();
		
		//3) Printing closure information
		printApplicationClosure();
	}
}