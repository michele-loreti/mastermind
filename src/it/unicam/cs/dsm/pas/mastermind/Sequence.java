/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author loreti
 *
 */
public class Sequence {
	
	private final int[] values;
	
	public Sequence(int ... values) {
		this.values = values;
	}
	
	public int getValue(int i) {
		return values[i];
	}
	
	public int lenght() {
		return values.length;
	}
	
	public Hint verify(Sequence s) {
		if (lenght() != s.lenght()) {
			throw new IllegalArgumentException();
		}
		int posizioneCorretta = numeroElementiInPosizioneCorretta(s);
		int simboliInComune = numeroSimboliInComune(s);
		return new Hint(posizioneCorretta,simboliInComune-posizioneCorretta);
	}

	private Map<Integer,Integer> occorrenze() {
		Map<Integer,Integer> o = new HashMap<>();
		for(int i=0; i<lenght(); i++) {
			int s = getValue(i);
			o.put(s,o.getOrDefault(s,0)+1);
		}
		return o;
	}
		
	private int numeroSimboliInComune(Sequence seq) {
		Map<Integer,Integer> o1 = this.occorrenze();
		Map<Integer,Integer> o2 = seq.occorrenze();
		int count = 0;		
		for (Integer s : o1.keySet()) {
			int v1 = o1.get(s);
			int v2 = o2.getOrDefault(s, 0);
			count = count+Math.min(v1, v2);
		}
		return count;
	}

	private int numeroElementiInPosizioneCorretta(Sequence s) {
		int count = 0;
		for(int i=0;i<lenght();i++) {
			if (getValue(i)==s.getValue(i)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sequence other = (Sequence) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sequence [values=" + Arrays.toString(values) + "]";
	}
	
	

}
