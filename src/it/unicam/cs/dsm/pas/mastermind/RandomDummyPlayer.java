/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

/**
 * @author loreti
 *
 */
public class RandomDummyPlayer extends AbstractRisolutore {

	@Override
	public Sequence nextSequence()
			throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		return rules.generaSequenza();
	}

}
