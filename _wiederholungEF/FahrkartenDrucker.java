package _wiederholungEF;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import _config.Configuration;
import gui.GUI;

public class FahrkartenDrucker {
	private int vorhandeneBlaetter;
	private GUI druckerGUI;
	
	public FahrkartenDrucker(){
		vorhandeneBlaetter = 2;
		druckerGUI = new GUI(this);
	}
	
	public void papierNachfuellen(int pBlaetter){
		vorhandeneBlaetter += pBlaetter;
	}
	
	
	public boolean drucken(String pFahrkarte){
		if(vorhandeneBlaetter < 1){
			System.err.println("Kein Papier mehr!");
			return false;
		}
		String ueberschrift = "*** Deine Fahrkarte ***";
		int sternchenZahl = (ueberschrift.length() - pFahrkarte.length() - 2)/2;
		String fahrkartenText = "";
		fahrkartenText += (sternchen(ueberschrift.length())+"\n");
		fahrkartenText += (ueberschrift+"\n");
		fahrkartenText += (sternchen(sternchenZahl));
		fahrkartenText += " "+pFahrkarte+" ";
		fahrkartenText += (sternchen(sternchenZahl) + "\n");
		fahrkartenText += (sternchen(ueberschrift.length())+"\n");
		fahrkartenText += ("\n");
		fahrkartenText += ("\n");
		//System.out.println(fahrkartenText);
		JTextArea textArea = new JTextArea();
        Font courierNewFont = new Font("Courier New", Font.PLAIN, Configuration.FONT_SIZE);
        textArea.setFont(courierNewFont);
        textArea.setText(fahrkartenText);
		JOptionPane.showMessageDialog(druckerGUI,textArea);
		vorhandeneBlaetter--;
		return true;
	}
	
	private String sternchen(int anzahl){
		String ergebnis = "";
		if (anzahl > 0) {
			for (int i = 0; i < anzahl; i++) {
				ergebnis += "*";
			} 
		}
		return ergebnis;
	}
	
	public static void main(String[] args) {
		new FahrkartenDrucker();
	}
}
