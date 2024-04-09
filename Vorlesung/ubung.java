import  javax.swing.JOptionPane;


public class ubung {
  public static void main(String[] args) {
    String zahl=JOptionPane.showInputDialog("Zahl eingeben");
    
    int intZahl=Integer.parseInt(zahl);
    int tausend=intZahl/1000;
    int hundert= (intZahl-tausend*1000)/100;
    int zehn= (intZahl-tausend*1000-hundert*100)/10;
    int einer= (intZahl-tausend*1000-hundert*100-zehn*10);
System.out.println("Die Quersumme von "+zahl+" ist "+(tausend+hundert+zehn+einer));


    System.out.println(tausend);
    System.out.println(hundert);
    System.out.println(zehn);
    System.out.println(einer);
   
  }  
}
