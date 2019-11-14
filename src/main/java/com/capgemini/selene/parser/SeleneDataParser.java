package com.capgemini.selene.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.capgemini.selene.model.SeleneEvent;

public class SeleneDataParser {
	
	private static DeserialiserJsonImpl<SeleneEvent> eventParser = new DeserialiserJsonImpl<SeleneEvent>(SeleneEvent.class);
	private final static String FILENAME_JSON_EVENTS = "RandomEvents.json";
	
	/*
	 * @deprecated use it only for testing
	 */
	public static SeleneEvent getTest() {
		SeleneEvent result = null;
		try {
			File file = new ClassPathResource("jsonTest.json").getFile();
			result =  eventParser.getObject(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Method to retrieve all events
	 * @return
	 */
	public static List<SeleneEvent> getSeleneEvents() {
		List<SeleneEvent> result = null;
		try {
			File file = new ClassPathResource(FILENAME_JSON_EVENTS).getFile();
			result = eventParser.getList(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
