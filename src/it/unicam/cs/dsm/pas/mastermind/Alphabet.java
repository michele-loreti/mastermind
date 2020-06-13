/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author loreti
 *
 */
public class Alphabet<T> {
	
	private final T[] elements;
	private final Map<T,Integer> indexes;
	
	public Alphabet(T[] elements) {
		this.elements = elements;
		this.indexes = new HashMap<>();
		initIndexes();
	}
	
	
	private void initIndexes() {
		for(int i=0;i<elements.length;i++) {
			if (this.indexes.put(elements[i],i)!=null) {
				throw new IllegalArgumentException("I simbolo "+elements[i]+" è duplicato nell'alfabeto!");
			}
		}
	}


	public T get(int i) {
		return elements[i];
	}
	

	public int indexOf(T t) {
		return this.indexes.getOrDefault(t, -1);
	}
}
