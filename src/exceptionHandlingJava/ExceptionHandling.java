package exceptionHandlingJava;
import java.util.InputMismatchException;
import static javax.swing.JOptionPane.*;

class OutOfRangeException extends InputMismatchException
{
    public OutOfRangeException(String message)
    {
        super (message);
    }
}

public class ExceptionHandling
{
	public static void main(String[] args)
	{
		//1) Creating all needed Objects.
		//1.1) Creating all customers Objects.
		PrivateCustomer maxMusterman = new PrivateCustomer("Max", "Musterman", 0);
		PrivateCustomer lisaMusterfrau = new PrivateCustomer("Lisa", "Musterfrau", 1000);
		PrivateCustomer fabianMüller = new PrivateCustomer("Fabian", "Müller", 200);
		
		//1.2) Creating bank Object.
		Bank sparkasse = new Bank();
		
		//1.3) Creating all account Objects.
		sparkasse.createNewAccount(maxMusterman.m_surname, maxMusterman.m_lastname, "max1234", 5000);
		sparkasse.createNewAccount(lisaMusterfrau.m_surname, lisaMusterfrau.m_lastname, "lisa5678", 0);

		//2) Realizing all needed scenarios.
		//2.1) Customer Max Musterman.
		maxMusterman.checkCash();
		sparkasse.serveCustomer(maxMusterman);
		
		//2.2) Customer Lisa Musterman.
		lisaMusterfrau.checkCash();
		
		//2.3) Customer Fabian Müller.
		
		//2.4) .
		
		//2.5) .
		
		//2.6) .
		
		
		/*
		double kontostand=0.00;
		
		
		String eOderA=showInputDialog("E oder A"); //Abfrage, ob Einzahlung oder Auszahlung gewollt ist
		
		try
		{
			System.out.println("Welcher Betrag soll überwiesen weden?");
			double uberweisungBetrag = eingabe.nextDouble();
			if(uberweisungBetrag<0)
			{
				throw new OutOfRangeException ("Negative Beträge können nicht eingezahlt werden.");
			}
		}
		catch(Exception e)
		{
			System.out.println("Fehler: Bitte geben Sie eine Zahl ein.");
			
			//IF Funktion zur Berechnung des Kontostands
			//kontostand = kontostand + uberweisungBetrag;
			//System.out.println("Eingabe:" + uberweisungBetrag);
			
			System.out.println(kontostand);
			System.out.println(eOderA); 
		}
		*/
		
		
	}
}