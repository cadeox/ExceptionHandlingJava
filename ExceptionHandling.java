import java.util.InputMismatchException;
import java.util.Scanner;
import static javax.swing.JOptionPane.*;
class OutOfRangeException extends Exception {
    public OutOfRangeException(String message) {
        super (message);
    }
}
public class ExceptionHandling {
public static void main(String[] args) {
double kontostand=1000.00;
Scanner eingabe =new Scanner(System.in);
double betragEinzahlung=0.0;
double betragAuszahlung=0.0;
double betragÜberweisung=0.0;
int überweisungKontonummer=0;

/* Funktionen:
 * Einzahlen 
 * Auszahlen
 * Überweisen
 * Kontostand abrufen
 */


 System.out.println("Funktion Auswählen “\n 1 für Einzahlung \n 2 für Auszahlung \n 3 für Überweisung \n 4 für Kontostand abfragen \n 5 zum Beenden");

 int FunktionAuswahl= eingabe.nextInt();

 if (FunktionAuswahl==1){
//im Folgenden Einzahlung

try {
System.out.println("Welcher Betrag soll überwiesen weden?");


 betragEinzahlung=eingabe.nextDouble();
if(betragEinzahlung<0){
    throw new OutOfRangeException ("Negative Beträge können nicht eingezahlt werden.");
}
} catch(InputMismatchException e) {
    System.out.println("Fehler: Bitte geben Sie eine Zahl ein.");  }

  
catch(OutOfRangeException e) {
    System.out.println("Negative Beträge können nicht eingezahlt werden.");
}

kontostand=kontostand+betragEinzahlung;



 }else{
if(FunktionAuswahl==2){
 //im Folgenden Auszahlung

 try {
    System.out.println("Welcher Betrag soll Ausgezahlt weden?");
    
    
     betragAuszahlung=eingabe.nextDouble();
    if(betragAuszahlung<0){
        throw new OutOfRangeException ("Negative Beträge können nicht eingezahlt werden.");
    }
    } catch(InputMismatchException e) {
        System.out.println("Fehler: Bitte geben Sie eine Zahl ein.");  }
    
      
    catch(OutOfRangeException e) {
        System.out.println("Bitte positiven Betrag eingeben.");
    }
    kontostand=kontostand-betragAuszahlung;
}else{
    if(FunktionAuswahl==3){
     //im Folgenden Überweisung
    
     try {
        System.out.println("Kontonummer des Empfängers angeben.");
        überweisungKontonummer=eingabe.nextInt();
        System.out.println("Überweisungsbetrag angeben.");
        betragÜberweisung=eingabe.nextInt();
        
         
        if(betragÜberweisung<0){
            throw new OutOfRangeException ("Negative Beträge können nicht überwiesen werden.");
        }
        } catch(InputMismatchException e) {
            System.out.println("Fehler: Bitte geben Sie eine Zahl ein.");  }
        
          
        catch(OutOfRangeException e) {
            System.out.println("Negative Beträge können nicht überwiesen werden.");
        }
    System.out.println("Es wurden " +betragÜberweisung + " an die Kontonummer " +überweisungKontonummer + " überwiesen.");
kontostand=kontostand-betragÜberweisung; 
}

else
    if(FunktionAuswahl==4){
     //im Folgenden Kontostand anzeigen
     System.out.println("Ihr Kontostand beträgt "+kontostand+ "." );}

System.out.println(kontostand);
System.out.println(FunktionAuswahl);
    

}}}}
    
