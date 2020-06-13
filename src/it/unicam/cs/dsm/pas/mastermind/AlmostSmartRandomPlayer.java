/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

/**
 * @author loreti
 *
 */
public class AlmostSmartRandomPlayer extends AbstractRisolutore {

	@Override
	public Sequence nextSequence()
			throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		return generaSequenza();
	}

	private Sequence generaSequenza() {
		int count = 0;
		while (true) {
			count++;
			Sequence seq = rules.generaSequenza();
			if (coerenteConSuggerimenti(seq)) {
				System.out.println("Generate: "+count);
				return seq;
			}
		}
	}

	private boolean coerenteConSuggerimenti(Sequence seq) {
		
		for(int i=0;i<sequenze.size();i++) {
			Sequence seqI = sequenze.get(i);
			Hint hintI = suggerimenti.get(i);
			Hint hint = seq.verify(seqI);
			if (!hint.equals(hintI)) {
				return false;
			}
		}
		return true;
	}

}
