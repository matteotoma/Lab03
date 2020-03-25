package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String parola;
	private boolean giusta;
	
	public RichWord(String parola) {
		super();
		this.parola = parola;
		this.giusta = true;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isGiusta() {
		return giusta;
	}

	public void setGiusta(boolean giusta) {
		this.giusta = giusta;
	}
		
}