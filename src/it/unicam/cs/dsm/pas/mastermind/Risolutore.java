/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

/**
 * @author loreti
 *
 */
public interface Risolutore {
	
	void setRules(Rules r);

	Sequence nextSequence() throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato;
	
	void hint(Sequence seq, Hint hint);
	
}
