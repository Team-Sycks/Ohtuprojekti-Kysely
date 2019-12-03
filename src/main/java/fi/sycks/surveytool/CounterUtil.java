package fi.sycks.surveytool;

import java.util.ArrayList;
import java.util.List;

import fi.sycks.surveytool.domain.Duplicate;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.Vastaus;

public class CounterUtil {
	public static List<Duplicate> findDuplicateWordsInAnswer(List<Vastaus> vastaukset){
		List<Duplicate> duplicates = new ArrayList<>();
		
		for(Vastaus vastaus : vastaukset) {
			if(vastaus.getKysymys().getTyyppi() == Kysymys.TYPE_SHORT_TEXT) continue;
			
			for(Vastaus vastaus2 : vastaukset) {
				if(vastaus.getKysymys().getTyyppi() == Kysymys.TYPE_SHORT_TEXT) continue;
				if(!vastaus.equals(vastaus2)) {
					String text = vastaus.getVastausteksti();
					if(text.equals(vastaus2.getVastausteksti())) {
						createDuplicate(text, duplicates);
					}
				}
			}
		}
		
		for(Vastaus vastaus : vastaukset) {
			if(vastaus.getKysymys().getTyyppi() == Kysymys.TYPE_SHORT_TEXT) {
				String vastausTeksti = vastaus.getVastausteksti().toLowerCase();
				String[] words = vastausTeksti.split(" ");
				
				
				for(int i=0; i<words.length; i++) {
					for(int a=0; a<words.length; a++) {
						if(i == a) continue;
						
						if(words[i].equals(words[a])) {

							createDuplicate(words[i], duplicates);
						}
					}
				}
			}
		}
		
		return duplicates;
	}
	
	private static void createDuplicate(String word, List<Duplicate> duplicates) {
		boolean alreadyFound = false;
		
		for(Duplicate duplicate : duplicates) {
			if(duplicate.getName().equals(word)) {
				duplicate.setCount(duplicate.getCount() + 1);
				alreadyFound = true;
			}
		}
		
		if(!alreadyFound) {
			duplicates.add(new Duplicate(word, 2));
		}
	}
}
