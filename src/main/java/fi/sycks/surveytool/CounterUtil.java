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
			if(vastaus.getKysymys().getTyyppi() != Kysymys.TYPE_SHORT_TEXT) {
				String vastausTeksti = vastaus.getVastausteksti().toLowerCase();
				String[] words = vastausTeksti.split(" ");
				
				
				for(int i=0; i<words.length; i++) {
					for(int a=0; a<words.length; a++) {
						if(i == a) continue;
						
						if(words[i].equals(words[a])) {
							boolean alreadyFound = false;
							
							for(Duplicate duplicate : duplicates) {
								if(duplicate.getName().equals(words[i])) {
									duplicate.setCount(duplicate.getCount() + 1);
									alreadyFound = true;
								}
							}
							
							if(!alreadyFound) {
								new Duplicate(words[i], 2);
							}
									
						}
					}
				}
			}
		}
		
		return duplicates;
	}
}
