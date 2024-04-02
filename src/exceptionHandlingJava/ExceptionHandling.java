package exceptionHandlingJava;

public class ExceptionHandling
{
	public static void main(String[] args)
	{
		//0) Printing start information.
		
		Logger.getInstance().print(TextTemplate.ApplicationOpenningText);
		
		//1) Stage 1: Objects Initialization.
		//1.0) Printing Stage 1 Information.
		Logger.getInstance().print(TextTemplate.ApplicationStageOneText);
		
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
		Logger.getInstance().print(TextTemplate.ApplicationStageTwoText);
		
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
		Logger.getInstance().print(TextTemplate.ApplicationClosureText);
	}
}