import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1>Benutzerdefinierte Exception Klasse OutOfMoneyException</h1> welche eine compositions Klasse von
 * Klasse Main darstellt und als Subklasse von Klasse {@link Exception} wirkt.
 * Realisation von Exception wird mit super()-Methode von Superklasse Exception vererbt.
 * <p>
 * Member Methode:
 * <ul>
 * <li>{@link OutOfMoneyException#OutOfMoneyException(message)}</li>
 * </ul>
 */
class OutOfMoneyException extends Exception {
  private static final long serialVersionUID = 1L;
  public OutOfMoneyException(String message) {
    super(message);
  }
}

/**
 * <h1>Main Klasse</h1> welche main()-Methode und Kernlogik der Applikation beinhaltet.
 * <p>
 * Member Methoden:
 * <ul>
 * <li>{@link Main#einzahlungTaetigen(eingabe, konto)}</li>
 * <li>{@link Main#auszahlungTaetigen(eingabe, konto)}</li>
 * <li>{@link Main#ueberweisungTaetigen(eingabe, senderKonto, empfaengerKonto)}</li>
 * <li>{@link Main#checkKontostand(konto)}</li>
 * </ul>
 * <p>
 * Compositions Klasse: <b> {@link OutOfMoneyException}</b>
 */
public class Main {
  public static void main(String[] args) {
	  //Initialisation von neuer Instanz der Klasse Scanner.
	  Scanner eingabe = new Scanner(System.in);
	  
	  try {
		  //Initialisation von allen notwendigen Bankkonto Instanzen.
		  Bankkonto kontoMax = new Bankkonto("1234", 1000);
		  Bankkonto kontoLisa = new Bankkonto("5678", 0);
		  
		  //Testing Code für fehlerhafter Instanz/Objekt von Bankkonto Klasse.
		  //Bankkonto kontoMitFehler = new Bankkonto("", -1000);
		  
		  //While Schleife für die Abfrage von Benutzer gewünschten Aktionen 
		  //und Abruf von zuständigen Methoden.
		  while (true) {
			  System.out.println("Funktion Auswählen:\n "
			  					+ "1 für Einzahlung\n "
			  					+ "2 für Auszahlung\n "
			  					+ "3 für Überweisung\n "
			  					+ "4 für Kontostand abfragen\n "
			  					+ "5 zum Beenden");
			  try {
				  int funktionAuswahl = eingabe.nextInt();
				  if (funktionAuswahl == 5) {
					  System.out.println("Auf Wiedersehen.");
					  break;
				  }
				  switch (funktionAuswahl) {
				  case 1:
					  einzahlungTaetigen(eingabe, kontoMax);
					  break;
				  case 2:
					  auszahlungTaetigen(eingabe, kontoMax);
					  break;
				  case 3:
					  ueberweisungTaetigen(eingabe, kontoMax, kontoLisa);
					  break;
				  case 4:
					  checkKontostand(kontoMax);
					  break;
				  default:
					  System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Eingabe ein.");
					  break;
				  }
			  } catch(InputMismatchException e) {
				  System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Eingabe ein.");
				  eingabe.nextLine();
			  } catch(ArithmeticException e) {
				  System.out.println(e.getMessage());
				  eingabe.nextLine();
			  }
			  
		  }
	  } catch(IOException e) {
		  System.out.println("Fehler beim Initialisieren der Bankkonten: " + e.getMessage());
	  } finally {
		  //Applications Abschluss Tätigkeiten.
		  eingabe.close();
		  System.out.println("System: Eingabe wurde abgeschlossen.");
	  }
  }
  
  /**
   * <h1>Einzahlungs Methode</h1> welche eine Einzahlung auf Bankkonto tätigt.
   * Benutzer wird aufgefordert den gewünschten Einzahlungsbetrag über den Benutzer-Eingabe-Interface
   * einzugeben und dieser Betrag wird den Bankkonto gutgeschrieben über {@link Bankkonto#aendernKontostand(aktion, betrag)}
   * <p>
   * @param eingabe
   * 		Scanner, das den Benutzer-Eingabe-Interface repräsentiert.
   * @param konto
   * 		Bankkonto, das die Instanz/Objekt der Bankkonto Klasse repräsentiert.
   * @exception ArithmeticException 
   * 		wird geworfen, falls Einzahlungsbetrag negativ ist.
   */
  public static void einzahlungTaetigen(Scanner eingabe, Bankkonto konto) {
    System.out.println("Welcher Betrag soll eingezahlt werden?");
    double betragEinzahlung = eingabe.nextDouble();
    if(betragEinzahlung < 0) {
      throw new ArithmeticException("Negative Beträge können nicht eingezahlt werden.");
    }
    konto.aendernKontostand(KontoAktion.Einzahlung, betragEinzahlung);
  }
  
  /**
   * <h1>Auszahlungs Methode</h1> welche eine Auszahlung von Bankkonto tätigt.
   * Benutzer wird aufgefordert den gewünschten Auszahlungsbetrag über den Benutzer-Eingabe-Interface
   * einzugeben und dieser Betrag aus dem Bankkonto abgebucht über {@link Bankkonto#aendernKontostand(aktion, betrag)}
   * <p>
   * @param eingabe
   * 		Scanner, das den Benutzer-Eingabe-Interface repräsentiert.
   * @param konto
   * 		Bankkonto, das die Instanz/Objekt der Bankkonto Klasse repräsentiert.
   * @exception OutOfMoneyException 
   * 		wird geworfen, falls Auszahlungsbetrag mehr als aktueller Kontostand ist.
   * @exception ArithmeticException 
   * 		wird geworfen, falls Auszahlungsbetrag negativ ist.
   */
  public static void auszahlungTaetigen(Scanner eingabe, Bankkonto konto) {
    try {
      System.out.println("Welcher Betrag soll ausgezahlt werden?");
      double betragAuszahlung = eingabe.nextDouble();
      if(konto.bekommenKontostand() < betragAuszahlung) {
        throw new OutOfMoneyException("Betrag überschreitet den Kontostand.");
      }
      else if(betragAuszahlung < 0) {
        throw new ArithmeticException("Negative Beträge können nicht ausgezahlt werden.");
      }
      konto.aendernKontostand(KontoAktion.Auszahlung, betragAuszahlung);
    } catch(OutOfMoneyException e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * <h1>Überweisungs Methode</h1> welche eine Überweisung von einem Bankkonto auf ein anderes tätigt.
   * Benutzer wird über den Benutzer-Eingabe-Interface erst aufgefordert den gewünschten Empfängerkonto 
   * anzugeben und danach den gewünschten Überweisungsbetrag.
   * Bei der Eingabe von Empfängerkonto wird dieses Konto geprüft, ob es existirt. Diese Überprüfung erfolg mit
   * {@link Bankkonto#checkKontonummer(kontonummer)}.
   * Nach der erfolgreicher Überprüfung und Eingabe von validen Überweisungsbetrages, werden die Kontostände
   * von beiden Konten dementsprechend durch {@link Bankkonto#aendernKontostand(aktion, betrag)} geändert.
   * <p>
   * @param eingabe
   * 		Scanner, das den Benutzer-Eingabe-Interface repräsentiert.
   * @param senderKonto
   * 		Bankkonto, von dem überwiesen wird.
   * @param empfaengerKonto
   * 		Bankkonto, auf das überwiesen wird.
   * 
   * @exception Ausnahmen werden nicht geworfen. Diese Methode stellt alternative Lössung dar, in der möglichen Fehlerszenarien
   * mit Logik und if-else Statements bearbeitet werden..
   */
  public static void ueberweisungTaetigen(Scanner eingabe, Bankkonto senderKonto, Bankkonto empfaengerKonto) {
    System.out.println("Kontonummer des Empfängers angeben:");
    String ueberweisungKontonummer = eingabe.next();
    
    if(empfaengerKonto.checkKontonummer(ueberweisungKontonummer)) {
      while(true)
      {
    	  System.out.println("Überweisungsbetrag angeben:");
    	  double betragUeberweisung = eingabe.nextDouble();
          if(senderKonto.bekommenKontostand() < betragUeberweisung) {
              System.out.println("Betrag überschreitet den Kontostand.\n"
              					+ "Wiederholen Sie Ihre Eingabe.");
          }
          else if(betragUeberweisung < 0) {
        	  System.out.println("Negative Beträge können nicht ausgezahlt werden.\n"
        			  			+ "Wiederholen Sie Ihre Eingabe.");
          }
          else {
        	  senderKonto.aendernKontostand(KontoAktion.Auszahlung, betragUeberweisung);
              empfaengerKonto.aendernKontostand(KontoAktion.Einzahlung, betragUeberweisung);
              System.out.println("Es wurden " + betragUeberweisung + " EUR an die Kontonummer " + ueberweisungKontonummer + " überwiesen.");
        	  break;
          }
          eingabe.nextLine();
      }
    }
  }
  
  /**
   * <h1>Kontostand Check Methode</h1> welche eine Auskunft über den aktuellen Kontostand von
   * Bankkonto übermittelt, in dem es {@link Bankkonto#bekommenKontostand()} aufruft.
   * <p>
   * @param konto
   * 		Bankkonto, das die Instanz/Objekt der Bankkonto Klasse repräsentiert.
   */
  public static void checkKontostand(Bankkonto konto) {
    System.out.println("Ihr Kontostand beträgt " + konto.bekommenKontostand());
  }
}




/*
 * Ausgangsvariante von Source Code, ohne Exceptions Handling.
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner eingabe = new Scanner(System.in);
    Bankkonto kontoMax = new Bankkonto("1234", 1000);
    Bankkonto kontoLisa = new Bankkonto("5678", 0);
    
    while (true) {
      System.out.println("Funktion Auswählen:\n 1 für Einzahlung \n 2 für Auszahlung \n 3 für Überweisung \n 4 für Kontostand abfragen \n 5 zum Beenden");
      int funktionAuswahl = eingabe.nextInt();
      if (funktionAuswahl == 5) {
        System.out.println("Auf Wiedersehen");
        break;
        }
      switch (funktionAuswahl) {
      case 1:
        einzahlungTaetigen(eingabe, kontoMax);
        break;
      case 2:
        auszahlungTaetigen(eingabe, kontoMax);
        break;
      case 3:
        ueberweisungTaetigen(eingabe, kontoMax, kontoLisa);
        break;
      case 4:
        checkKontostand(kontoMax);
        break;
      default:
        System.out.println("Ungültige Auswahl.");
        break;
      }
    }
    eingabe.close();
  }
  
  public static void einzahlungTaetigen(Scanner eingabe, Bankkonto konto) {
    System.out.println("Welcher Betrag soll eingezahlt werden?");
    double betragEinzahlung = eingabe.nextDouble();
    konto.aendernKontostand(KontoAktion.Einzahlung, betragEinzahlung);
  }
  
  public static void auszahlungTaetigen(Scanner eingabe, Bankkonto konto) {
    System.out.println("Welcher Betrag soll ausgezahlt werden?");
    double betragAuszahlung = eingabe.nextDouble();
    konto.aendernKontostand(KontoAktion.Auszahlung, betragAuszahlung);
  }
  
  public static void ueberweisungTaetigen(Scanner eingabe, Bankkonto senderKonto, Bankkonto empfaengerKonto) {
    System.out.println("Kontonummer des Empfängers angeben:");
    String ueberweisungKontonummer = eingabe.next();
    
    if(empfaengerKonto.checkKontonummer(ueberweisungKontonummer)) {
      System.out.println("Überweisungsbetrag angeben:");
      double betragUeberweisung = eingabe.nextDouble();
      senderKonto.aendernKontostand(KontoAktion.Auszahlung, betragUeberweisung);
      empfaengerKonto.aendernKontostand(KontoAktion.Einzahlung, betragUeberweisung);
      System.out.println("Es wurden " + betragUeberweisung + " EUR an die Kontonummer " + ueberweisungKontonummer + " überwiesen.");
    }
  }
  
  public static void checkKontostand(Bankkonto konto) {
    System.out.println("Ihr Kontostand beträgt " + konto.bekommenKontostand());
  }
}
*/