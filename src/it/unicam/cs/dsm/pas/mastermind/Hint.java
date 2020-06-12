/**
 * 
 */
package it.unicam.cs.dsm.pas.mastermind;

/**
 * @author loreti
 *
 */
public class Hint {

	private final int numeroSimboliInPosizioneCorretta;
	private final int numeroSimboliPresentiInPosizioneErrata;

	public Hint(int numeroSimboliInPosizioneCorretta, int numeroSimboliPresentiInPosizioneErrata) {
		this.numeroSimboliInPosizioneCorretta = numeroSimboliInPosizioneCorretta;
		this.numeroSimboliPresentiInPosizioneErrata = numeroSimboliPresentiInPosizioneErrata;
	}

	public int getNumeroSimboliInPosizioneCorretta() {
		return numeroSimboliInPosizioneCorretta;
	}

	public int getNumeroSimboliPresentiInPosizioneErrata() {
		return numeroSimboliPresentiInPosizioneErrata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroSimboliInPosizioneCorretta;
		result = prime * result + numeroSimboliPresentiInPosizioneErrata;
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
		Hint other = (Hint) obj;
		if (numeroSimboliInPosizioneCorretta != other.numeroSimboliInPosizioneCorretta)
			return false;
		if (numeroSimboliPresentiInPosizioneErrata != other.numeroSimboliPresentiInPosizioneErrata)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hint [numeroSimboliInPosizioneCorretta=" + numeroSimboliInPosizioneCorretta
				+ ", numeroSimboliPresentiInPosizioneErrata=" + numeroSimboliPresentiInPosizioneErrata + "]";
	}
	
	

}
