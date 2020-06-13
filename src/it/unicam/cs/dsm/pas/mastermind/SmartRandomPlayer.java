/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

import java.util.List;
import java.util.Random;

/**
 * @author loreti
 *
 */
public class SmartRandomPlayer extends AbstractRisolutore {

	private Random rnd = new Random();
	private List<Sequence> sequenzeValide;

	@Override
	public void setRules(Rules r) {
		super.setRules(r);
		this.sequenzeValide = r.generaSequenze();
	}

	@Override
	public Sequence nextSequence()
			throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		System.out.println("Sequenze possibili: "+sequenzeValide.size());
		return sequenzeValide.get(rnd.nextInt(sequenzeValide.size()));
	}

	@Override
	public void hint(Sequence seq, Hint hint) {
		sequenzeValide = filtra(sequenzeValide, seq, hint);
	}

}
