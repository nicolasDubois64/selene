package com.capgemini.selene.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserialiserJsonImpl<T> implements DeserialiserJson<T> {
	
    private ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> clazz;
    
    public DeserialiserJsonImpl(Class<T> type){
        this.clazz = type;
    }

	@Override
	public T getObject(String content) throws IOException {
		return objectMapper.readValue(content, clazz);
	}
	
	@Override
	public T getObject(File file) throws IOException {
		return objectMapper.readValue(file, clazz);
	}

	@Override
	public List<T> getList(String content) throws IOException {
		return objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
	}

	@Override
	public List<T> getList(File file) throws IOException {
		return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
	}

	@Override
	public List<T> getList(InputStream stream) throws IOException {
		return objectMapper.readValue(stream, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
	}

}
