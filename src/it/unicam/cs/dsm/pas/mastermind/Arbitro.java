/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

/**
 * @author loreti
 *
 */
public class Arbitro {
	
	public final static Rules STANDAR_RULES_6_4 = new Rules(6,4,true);
	public final static Rules STANDAR_RULES_8_4 = new Rules(8,4,true);
	public final static Rules SIMPLE_RULES_8_4 = new Rules(8,4,false);
	public final static Rules SIMPLE_RULES_6_4 = new Rules(6,4,false);
	public final static Rules STANDAR_RULES_6_6 = new Rules(6,4,true);
	public final static Rules STANDAR_RULES_8_6 = new Rules(8,6,true);
	public final static Rules STANDAR_RULES_10_6 = new Rules(10,6,true);
	
	public int risolvi(Rules rules, Risolutore player, int limit) throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		Sequence seq = rules.generaSequenza();
		player.setRules(rules);
		int counter = 0;
		boolean risolto = false;
		while (!risolto&&(counter<limit)) {
			Sequence seq2 = player.nextSequence();
			Hint h = seq.verify(seq2);
			player.hint(seq2, h);
			risolto = (h.getNumeroSimboliInPosizioneCorretta()==rules.getLength());
			counter++;
		}
		return (risolto?counter:-1);
	}
	
	
	public double calcolaMedia( Rules r, Risolutore player , int repliche , int limit) throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		int sequenzeRisolte = 0;
		double sommaPassi = 0.0;
		for(int i=0; i<repliche; i++) {
			int passi = risolvi(r,player,limit);
			if (passi>=0) {
				sequenzeRisolte++;
				sommaPassi += passi;
			}
		}
		return sommaPassi/sequenzeRisolte;
	}
	
	public static void main(String[] pippo) throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		Arbitro a = new Arbitro();
		double mediaPassiRandom1 = a.calcolaMedia(STANDAR_RULES_8_4, new AlmostSmartRandomPlayer(),10,2000);
		double mediaPassiRandom2 = a.calcolaMedia(STANDAR_RULES_8_4, new SmartRandomPlayer(),10,2000);
		System.out.println("RandonPlauer 1: "+mediaPassiRandom1);
		System.out.println("RandonPlauer 2: "+mediaPassiRandom2);
	}

}
