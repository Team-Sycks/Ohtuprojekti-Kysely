package fi.sycks.surveytool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fi.sycks.surveytool.domain.Duplicate;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.Vastaus;

public class CounterUtil {
	
	public static Map<String, Integer> countRecurringAnswersForOneKysymys(Kysymys kysymys){
		List<Vastaus> vastaukset = kysymys.getVastaukset();
		
		Map<String, Integer> duplicates;
		if(kysymys.getTyyppi() == Kysymys.TYPE_SHORT_TEXT) {
			duplicates = CounterUtil.findDuplicateWordsInTextAnswer(vastaukset);
		} else {
			duplicates = CounterUtil.findDuplicateAnswers(vastaukset);
		}
		
		return duplicates;
	}
	
	private static Map<String, Integer> findDuplicateWordsInTextAnswer(List<Vastaus> vastaukset){
		Map<String, Integer> duplicates = new HashMap<String, Integer>();

		for(Vastaus vastaus : vastaukset) {
			String vastausTeksti = vastaus.getVastausteksti();
			String[] words = vastausTeksti.split(" ");
			
			for(int i=0; i<words.length; i++) {
				createDuplicate(words[i], duplicates);
			}
		}

		duplicates.values().remove(1);
		
		return duplicates;
	}
	
	private static Map<String, Integer> findDuplicateAnswers(List<Vastaus> vastaukset) {
		Map<String, Integer> duplicates = new HashMap<String, Integer>();
		
		for(Vastaus vastaus : vastaukset) {
			String vastausTeksti = vastaus.getVastausteksti();
			
			createDuplicate(vastausTeksti, duplicates);
		}
		
		return duplicates;
	}
	
	private static void createDuplicate(String word, Map<String, Integer> duplicates) {
		if(duplicates.containsKey(word)) {
			duplicates.put(word, duplicates.get(word) + 1);
		} else {
			duplicates.put(word, 1);
		}
	}
}
