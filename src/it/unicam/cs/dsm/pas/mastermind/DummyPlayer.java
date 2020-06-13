/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.Arrays;

/**
 * @author loreti
 *
 */
public class DummyPlayer extends AbstractRisolutore {

	@Override
	public Sequence nextSequence() throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		int[] values = new int[rules.getLength()];
		Arrays.fill(values, 0);
		return rules.getSequence(values);
	}

}
