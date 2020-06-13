package it.unicam.cs.dsm.pas.mastermind.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unicam.cs.dsm.pas.mastermind.EccezionePerSequenzaIllegale;
import it.unicam.cs.dsm.pas.mastermind.EccezionePerSimboloDuplicato;
import it.unicam.cs.dsm.pas.mastermind.EccezionePerSimboloIllegale;
import it.unicam.cs.dsm.pas.mastermind.Rules;
import it.unicam.cs.dsm.pas.mastermind.Sequence;

class TestRules {

	@Test
	void testGetSequence() {
		Rules r = new Rules(6, 4, false);
		assertFalse(r.isValid(1,1,1,1));
	}
	
	@Test
	void testEccezionePerMolteplicita() throws EccezionePerSequenzaIllegale, EccezionePerSimboloIllegale, EccezionePerSimboloDuplicato {
		Rules r = new Rules(6, 4, false);
		assertThrows(EccezionePerSimboloDuplicato.class, () -> {			
			r.getSequence(1,1,2,3);
		});
	}

}
