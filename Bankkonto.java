package src;
import java.io.IOException;

enum KontoAktion {
	Einzahlung,
	Auszahlung
}

public class Bankkonto {
	
	Bankkonto(String kontonummer, double kontostand) throws IOException	{
		if(kontostand < 0 | kontonummer.length() < 4) {
			throw new IOException("Kontoangaben fur " + kontonummer + " konnten nicht initializiert werden.");
		}
		m_kontostand = kontostand;
		m_kontonummer = kontonummer;
	}
	
	final boolean checkKontonummer(String kontonummer) {
		boolean resultat = false;
		if(kontonummer.equals(m_kontonummer)) {
			System.out.println("Kontonummer ist vorhanden.");
			resultat = true;
		}
		else {
			
		}
		return resultat;
	}
	
	final double bekommenKontostand() {
		return m_kontostand;
	}
	
	void aendernKontostand(KontoAktion aktion, double betrag) {
		switch(aktion) {
		case Einzahlung:
			m_kontostand += betrag;
			break;
		case Auszahlung:
			m_kontostand -= betrag;
			break;
		}
	}
	
	private double m_kontostand;
	private String m_kontonummer;
}



/*
 * Variante ohne Exceptions
package src;

enum KontoAktion {
	Einzahlung,
	Auszahlung
}

public class Bankkonto {
	
	Bankkonto(String kontonummer, double kontostand) {
		m_kontostand = kontostand;
		m_kontonummer = kontonummer;
	}
	
	final boolean checkKontonummer(String kontonummer) {
		boolean resultat = false;
		if(kontonummer.equals(m_kontonummer)) {
			System.out.println("Kontonummer ist vorhanden.");
			resultat = true;
		}
		return resultat;
	}
	
	final double bekommenKontostand() {
		return m_kontostand;
	}
	
	void aendernKontostand(KontoAktion aktion, double betrag) {
		switch(aktion) {
		case Einzahlung:
			m_kontostand += betrag;
			break;
		case Auszahlung:
			m_kontostand -= betrag;
			break;
		}
	}
	
	private double m_kontostand;
	private String m_kontonummer;
}
*/