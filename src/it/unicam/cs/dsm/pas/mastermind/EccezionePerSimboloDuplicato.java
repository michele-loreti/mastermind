/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

/**
 * @author loreti
 *
 */
public class EccezionePerSimboloDuplicato extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int value;

	public EccezionePerSimboloDuplicato(int value) {
		super("Indice "+value+" è duplicato nella sequenza!");
		this.value = value;		
	}

	public int getValue() {
		return value;
	}

	
	
}
