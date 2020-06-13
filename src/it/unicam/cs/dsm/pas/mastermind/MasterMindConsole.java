/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author loreti
 *
 */
public class MasterMindConsole {

	private Scanner input = new Scanner(System.in);
	private PrintStream output = System.out;
	
	
	public void play() {
		boolean flag = true;
		while (flag) {
			Alphabet<Character> alphabet = inputAlphabet();
			Rules rules = inputRules(alphabet);
			int tries = inputTries();
			playGame(alphabet,rules,tries);
			flag = inputPlayAgain();
		}
	}
	
	private void playGame(Alphabet<Character> alphabet, Rules rules, int tries) {
		Game game = new Game(rules, tries);
		while (!game.terminato()) {
			try {
				output.print("Inserisci la sequenza: ");
				String str = input.nextLine();
				Character[] code = charsOf(str);
				Hint h = game.provaSequenza(alphabet.getValues(code));
				output.println("Elementi in posizione corretta: "+h.getNumeroSimboliInPosizioneCorretta());
				output.println("Elementi presenti in posizione sbagliata: "+h.getNumeroSimboliPresentiInPosizioneErrata());
				if (h.getNumeroSimboliInPosizioneCorretta()==rules.getLength()) {
					output.println("Sequenza indovinata!");
					return;
				}
			} catch (Exception e) {
				output.println("Input errato!");
			} 
		}
		output.println("Hai perso! La sequenza corretta è: "+alphabet.stringOf(game.getSecret()));
	}

	private boolean inputPlayAgain() {
		output.print("Vuoi giocare ancora (S/N)?");
		return inputYesNo();
	}

	private int inputTries() {
		while (true) {
			output.print("In quante mosse vuoi indovinare? (Inserire un valore tra 10 e 20): ");
			int size = inputInteger();
			if ((size>=10)&&(size<=20)) {
				return size;
			}
			output.print("Errore: Input non valido!");
		}
	}

	private Rules inputRules(Alphabet<Character> alphabet) {
		output.print("Inserire la lunghezza della sequenza: ");
		int length = inputInteger();
		output.print("Vuoi le ripetizioni nelle sequenze (S/N)?");
		boolean withMultiplicity = inputYesNo();
		return new Rules(alphabet.size(),length,withMultiplicity);
	}

	private boolean inputYesNo() {
		String str = input.nextLine();
		if (str.length()>0) {
			return (str.toUpperCase().charAt(0)=='S');
		} else {
			return false;
		}
	}

	private Alphabet<Character> inputAlphabet() {
		while (true) {
			output.print("Quanti caratteri vuoi usare (inserire un numero tra 6 e 26): ");
			int size = inputInteger();
			if ((size>=6)&&(size<=26)) {
				return getAlphabet(size);
			}
			output.print("Errore: Input non valido!");
		}
	}

	private int inputInteger() {
		String str = input.nextLine();
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public Alphabet<Character> getAlphabet(int size) {
		if ((size<1)||(size>26)) {
			throw new IllegalArgumentException();
		}
		return new Alphabet<>(getChars(size));
	}

	private Character[] getChars(int size) {
		Character[] c = new Character[size];
		for(int i=0 ; i<size; i++) {
			c[i] = (char) ('A'+i);
		}
		return c;
	}
	
	private Character[] charsOf(String code) {
		Character[] charArray = new Character[code.length()];
		for(int i=0;i<charArray.length;i++) {
			charArray[i] = code.charAt(i);
		}
		return charArray;
	}
	
	public Rules getRules(Alphabet<Character> alphabet, int length, boolean withMultiplicity) {
		return new Rules(alphabet.size(), length, withMultiplicity);
	}
	
	public static void main(String[] argv) {
		MasterMindConsole mmc = new MasterMindConsole();
		mmc.play();
	}
	
}
