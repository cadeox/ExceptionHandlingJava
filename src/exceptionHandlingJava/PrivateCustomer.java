package exceptionHandlingJava;

public class PrivateCustomer
{
	//Constructor for Object of class PrivateCustomer
	PrivateCustomer(String surname, String lastname, double cash)
	{ 
		m_surname = surname;
		m_lastname = lastname;
		m_cash = cash;
		System.out.printf("New Private Customer %s %s was created.%n", m_surname, m_lastname);
	}
	
	//Methods for Actions of Private Customers
	void checkCash()
	{
		System.out.printf("%s's cash amount at the moment is %.2f EUR.%n", m_surname, m_cash);
	}
	
	//Member Fields for every Object of class PrivateCustomer
	String m_surname;
	String m_lastname;
	double m_cash;
}