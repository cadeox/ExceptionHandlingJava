import java.io.IOException;

/**
 * <h1>KontoAktion Enum</h1> von Bankkonto Klasse, welches für die Operationen mit
 * Instanzen/Objekten der {@link Bankkonto} Klasse verwendet wird.
 */
enum KontoAktion {
  Einzahlung,
  Auszahlung
}

/**
 * <h1>Bankkonto Klasse</h1> welche alle Instanzen/Objekte von Bankonto reguliert.
 * <p>
 * Member Felder: <b>double m_kontostand; String m_kontonummer;</b>
 * <p>
 * Member Methoden:
 * <ul>
 * <li>{@link Bankkonto#Bankkonto(kontonummer, kontostand)}</li>
 * <li>{@link Bankkonto#checkKontonummer(kontonummer)}</li>
 * <li>{@link Bankkonto#bekommenKontostand()}</li>
 * <li>{@link Bankkonto#aendernKontostand(aktion, betrag)}</li>
 * </ul>
 */
public class Bankkonto {
	
 /**
  * <h1>Bankkonto Constructor</h1> welches neue Instanz/Objekt der {@code Bankkonto} Klasse erstellt.
  * <p>
  * @param kontonummer
  *        String, das Kontonummer repräsentiert, mit Länge bis 4 Zeichen.
  * @param kontostand
  *        double, das Anfangsbestand von Konto repräsentiert.
  * @return Instanz/Objekt
  * 		der {@code Bankkonto} Klasse.
  * @exception IOException 
  * 		wird geworfen, falls Kontonummer länger als 4 Zeichen hat oder Anfangsbestand kleiner als 0 ist.
  */
  Bankkonto(String kontonummer, double kontostand) throws IOException {
    if(kontostand < 0 | kontonummer.length() < 4) {
      throw new IOException("Kontoangaben fur " + kontonummer + " konnten nicht initializiert werden.");
    }
    m_kontostand = kontostand;
    m_kontonummer = kontonummer;
  }
  
  /**
   * <h1>Kontonummer Check</h1> welches kontrolliert, ob eingegebene Kontonummer
   * gleich der gespeicherte Kontonummer ist oder nicht.
   * <p>
   * @param kontonummer
   * 		String, das Kontonummer repräsentiert.
   * @return resultat
   * 		 boolean, das das Resultat repräsentiert, ob die Kontonummer vorhanden ist oder nicht.
   */
  final boolean checkKontonummer(String kontonummer) {
    boolean resultat = false;
    if(kontonummer.equals(m_kontonummer)) {
      System.out.println("Kontonummer ist vorhanden.");
      resultat = true;
    }
    else {
      System.out.println("Kontonummer ist nicht vorhanden.");
    }
    return resultat;
  }
  
  /**
   * <h1>Getter für den aktuellen Kontostand</h1> welches m_kontostand zurück gibt.
   * <p>
   * @return m_kontostand
   * 		 double, das den aktuellen Kontostand repräsentiert.
   */
  final double bekommenKontostand() {
    return m_kontostand;
  }
  
  /**
   * <h1>Setter für den Kontostand</h1> welches m_kontostand abhängig von Argumenten ändert.
   * <p>
   * Mögliche Aktionen:
   * <ul>
   * <li><b>KontoAktion.Einzahlung</b></li>
   * <li><b>KontoAktion.Auszahlung</b></li>
   * </ul> 
   * <p>
   * @param aktion
   * 		KontoAktion Enum Member, das gewünschte Aktion repräsentiert.
   * @param betrag
   * 		double, das gewünschten Änderungsbetrag repräsentiert.
   */
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
  
  //Member Felder der Bankkonto Instanzen/Objekten.
  private double m_kontostand;
  private String m_kontonummer;
}

/*
 * Ausgangsvariante von Source Code, ohne Exceptions Handling.
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