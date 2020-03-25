package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	
	private Map<String, RichWord> parole = new TreeMap<>();

	public void loadDictionary(String language) {
		FileReader fr;
		BufferedReader br;
		String nomeFile = language;
		try {
			fr = new FileReader(nomeFile);
			br = new BufferedReader(fr);
			String word;
			while((word = br.readLine()) != null) {
				String array[] = word.split("\n");
				for(String s: array) {
					RichWord rw = new RichWord(s);
					parole.put(s, rw);
				}
			}
			fr.close();
			br.close();
		}
		catch(IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}
	
	public List<RichWord> spellCheckText(String inputText){
		List<RichWord> l = new LinkedList<>();
		inputText = inputText.replaceAll("[.,\\/#?!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String array[] = inputText.toLowerCase().split(" ");
		for(String s: array){
			if(!this.parole.containsKey(s)) {
				RichWord tempR = new RichWord(s);
				tempR.setGiusta(false);
				l.add(tempR);
			}
		}
		return l;
	}

}
