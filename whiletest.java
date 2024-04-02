import java.util.InputMismatchException;
import java.util.Scanner;
class OutOfRangeException extends Exception {
    public OutOfRangeException(String message) {
        super (message);
    }
public class whiletest {
    public static void main(String[] args) {
        Scanner eingabe =new Scanner(System.in);
        double Betrag=0.0;
        int Funktion=0;
        double Kontostand=1000.0;
        double betragEinzahlung=0.00;
        
        System.out.println("Funktion eingeben.");
         Funktion=eingabe.nextInt();
        switch(Funktion){
            case 1: 

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




           
        
    
}}}}
