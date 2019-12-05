package com.capgemini.selene.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.selene.model.SeleneData;
import org.springframework.core.io.ClassPathResource;

import com.capgemini.selene.model.SeleneEvent;

public class SeleneDataParser {

	//PARSER
	private static DeserialiserJsonImpl<SeleneEvent> eventParser = new DeserialiserJsonImpl<SeleneEvent>(
			SeleneEvent.class);
	private static DeserialiserJsonImpl<SeleneData> dataParser = new DeserialiserJsonImpl<SeleneData>(
			SeleneData.class);
	private static DeserialiserJsonImpl<String> stringParser = new DeserialiserJsonImpl<String>(String.class);
	
	//FILENAME
	private final static String FILENAME_JSON_EVENTS = "randomEvents.json";
	private final static String FILENAME_JSON_DATA_STRUCTURE = "seleneDocumentation.json";

	/*
	 * @deprecated use it only for testing
	 */
	public static SeleneEvent getTest() {
		SeleneEvent result = null;
		try {
			File file = new ClassPathResource("jsonTest.json").getFile();
			new ClassPathResource("jsonTest.json").getInputStream();
			result = eventParser.getObject(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method to retrieve all events
	 * 
	 * @return
	 */
	public static List<SeleneEvent> getSeleneEvents() {
		List<SeleneEvent> result = null;
		try {
			InputStream inputStream = new ClassPathResource(FILENAME_JSON_EVENTS).getInputStream();
			result = eventParser.getList(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static List<SeleneData> getSeleneData() {
		List<SeleneData> result = null;
		try {
			InputStream inputStream = new ClassPathResource("SeleneData.json").getInputStream();
			result = dataParser.getList(inputStream);
			result.forEach(s -> s.initValue());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Method to retrieve the doc (data structure, available url)
	 * @return
	 */
	public static String getDoc() {
		String doc = "";
		try { 
			InputStream dataStructureStream = new ClassPathResource(FILENAME_JSON_DATA_STRUCTURE).getInputStream();
			doc = stringParser.getPrettyJson(dataStructureStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

}
