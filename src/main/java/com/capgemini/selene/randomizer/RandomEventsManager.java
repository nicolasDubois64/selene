package com.capgemini.selene.randomizer;

import java.util.List;

import com.capgemini.selene.model.SeleneEvent;
import com.capgemini.selene.parser.SeleneDataParser;
import com.capgemini.selene.utils.RandomCollection;

/**
 * 
 * @author ndubois
 *
 */
public class RandomEventsManager {
	
	private static RandomCollection<SeleneEvent> randomEvents = new RandomCollection<>();

	public RandomEventsManager() {
		initRandomCollection();
	}
	
	/**
	 * Initializes the collection of random events from a json file
	 */
	private void initRandomCollection() {
		List<SeleneEvent> events = SeleneDataParser.getSeleneEvents();
		events.forEach(event -> randomEvents.add(event.getProbability(), event));
	}
	
	/**
	 * Get an event randomly
	 * @return
	 */
	public SeleneEvent getNextEvent() {
		return randomEvents.next();
	}
	
	/**
	 * Only to test the probability of events occurring
	 * @deprecated
	 * @param nbIterations
	 */
	public void testRandomEventsManager(int nbIterations) {
		int dix = 0;
		int vingt = 0;
		int trente = 0;
		int quarante = 0;
		int cinquante = 0;
		int soixante = 0;
		
		for (int i = 0; i < nbIterations; i++) {
			double prob = getNextEvent().getProbability();
			if (prob < 19) dix++;
			else if (prob < 29) vingt++;
			else if (prob < 39) trente++;
			else if (prob < 49) quarante++;
			else if (prob < 59) cinquante++;
			else if (prob < 69 ) soixante++;		
		}
		System.out.println("10% => " +  dix);
		System.out.println("20% => " +  vingt);
		System.out.println("30% => " +  trente);
		System.out.println("40% => " +  quarante);
		System.out.println("50% => " +  cinquante);
		System.out.println("60% => " +  soixante);
	}

}
