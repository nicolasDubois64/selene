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

}
