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
	private static int remainingDuration = 0;
	private static SeleneEvent currentEvent = new SeleneEvent();

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
	 * Get an event randomly and returns it until the end of its term
	 * @return
	 */
	public SeleneEvent getNextEvent() {
		SeleneEvent result;
		if (remainingDuration <= 0) {
			currentEvent = randomEvents.next();
			remainingDuration = currentEvent.getDuration();
			result = currentEvent;
		} else {
			result = currentEvent;
			remainingDuration--;
		}
		return result;
	}

}
