/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;
import java.util.Set;

/**
 * @author loreti
 *
 */
public class Rules {
	
	private final int numberOfSymbols;
	private final int length;
	private final boolean withMultiplicity;

	public Rules(int numberOfSymbols, int length, boolean withMultiplicity) {
		super();
		this.numberOfSymbols = numberOfSymbols;
		this.length = length;
		this.withMultiplicity = withMultiplicity;
		if ((!withMultiplicity)&&(length>numberOfSymbols)) {
			throw new IllegalArgumentException("Numero di simboli insufficienti per generare una sequenza senza duplicazioni!");
		}

	}

	public int getNumberOfSymbols() {
		return numberOfSymbols;
	}

	public int getLength() {
		return length;
	}

	public boolean isWithMultiplicity() {
		return withMultiplicity;
	}
	
	public boolean isValid(int ... values) {
		try {
			getSequence(values);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Sequence getSequence(int ... values) throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		if (values.length != this.length) {
			throw new EccezionePerSequenzaIllegale("Numero di elementi nella sequenza errato! Aspettati "+this.length+" sono "+values.length+"!");
		}
		checkContent(values);
		if (!withMultiplicity) {
			checkSingleMultiplicity(values);
		}
		return new Sequence(values);
	}

	private void checkSingleMultiplicity(int[] values) throws EccezionePerSimboloDuplicato {
		boolean[] symbols = new boolean[this.numberOfSymbols];
		for(int v: values) {
			if (symbols[v]) {
				throw new EccezionePerSimboloDuplicato(v);
			}
			symbols[v] = true;
		}
	}

	private void checkContent(int[] values) throws EccezionePerSimboloIllegale {
		for (int i=0;i<values.length;i++) {
			if ((values[i]<0)||(values[i]>=this.numberOfSymbols)) {
				throw new EccezionePerSimboloIllegale("Uso di un indice di simbolo illegale: "+values[i]);
			}
		}
	}

	public Sequence generaSequenza() {
		if (withMultiplicity) {
			return generaSequenzaConRipetizioni();
		} else {
			return generaSequenzaSenzaRipetizioni();
		}
	}

	private Sequence generaSequenzaSenzaRipetizioni() {
		Random r = new Random();
		int[] indexArray = generaArrayDegliIndici();
		for(int i=0;i<r.nextInt(10000);i++) {
			int i1 = r.nextInt(indexArray.length);
			int i2 = r.nextInt(indexArray.length);
			int v = indexArray[i1];
			indexArray[i1] = indexArray[i2];
			indexArray[i2] = v;
		}
		return new Sequence(Arrays.copyOfRange(indexArray, 0, length));
	}

	private int[] generaArrayDegliIndici() {
		int[] values = new int[numberOfSymbols];
		for(int i=0;i<numberOfSymbols;i++) {
			values[i] = i;
		}
		return values;
	}

	private Sequence generaSequenzaConRipetizioni() {
		Random r = new Random();
		int[] values = new int[this.length];
		for(int i=0; i<values.length; i++) {
			values[i] = r.nextInt(this.numberOfSymbols);
		}
		return new Sequence(values);
	}
	
	public List<Sequence> generaSequenze() {
		if (this.withMultiplicity) {
			return generaSequenzeConRipetizioni();
		} else {
			return generaSequenzeSenzaRipetizioni();
		}
	}

	private List<Sequence> generaSequenzeSenzaRipetizioni() {
		Set<Integer> symbolSet = generaInsiemeSimboli();
		return generaSequenze(symbolSet, this.length);
	}
	
	private Set<Integer> generaInsiemeSimboli() {
		HashSet<Integer> symbolSet = new HashSet<>();
		for(int i=0 ; i<numberOfSymbols ; i++) {
			symbolSet.add(i);
		}
		return symbolSet;
	}

	private List<Sequence> generaSequenze(Set<Integer> symbols, int lenght) {
		List<Sequence> toReturn = new ArrayList<>();
		if (lenght==1) {
			for (Integer v : symbols) {
				toReturn.add(new Sequence(v));
			}
		} else {
			for (Integer v : symbols) {
				Set<Integer> otherSymbols = new HashSet<>(symbols);
				otherSymbols.remove(v);
				List<Sequence> subSequence = generaSequenze(otherSymbols, lenght-1);
				for (Sequence sequence : subSequence) {
					toReturn.add(sequence.add(v));
				}
			}
		}
		return toReturn;
	}

	private List<Sequence> generaSequenzeConRipetizioni() {
		List<Sequence> listaSequenzeGenerate = generaSequenzaBase();
		for(int i=1;i<length;i++) {
			List<Sequence> listAlPassoPrecedete = listaSequenzeGenerate;
			listaSequenzeGenerate = new ArrayList<Sequence>();
			for (Sequence sequence : listAlPassoPrecedete) {
				for( int s=0 ; s<numberOfSymbols; s++) {
					Sequence nuovaSequenza = sequence.add(s);
					listaSequenzeGenerate.add(nuovaSequenza);
				}
			}
		}		
		return listaSequenzeGenerate;
	}

	private List<Sequence> generaSequenzaBase() {
		ArrayList<Sequence> toReturn = new ArrayList<>();
		for( int i=0 ; i<numberOfSymbols; i++) {
			toReturn.add(new Sequence(i));
		}
		return toReturn;
	}

}
