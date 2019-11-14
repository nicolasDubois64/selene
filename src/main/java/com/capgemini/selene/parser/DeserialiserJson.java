package com.capgemini.selene.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DeserialiserJson<T> {

		/**
		 * Method to deserialize JSON content from given JSON content String into given Java type.
		 * @param content
		 * @return
		 * @throws IOException
		 */
	    T getObject(String content) throws IOException;
	    
	    /**
	     * Method to deserialize JSON content from given file into given Java type.
	     * @param file
	     * @return
	     * @throws IOException
	     */
	    T getObject(File file) throws IOException;

	    /**
	     * Method to deserialize JSON Array content from given JSON content String into a list of given Java type.
	     * @param content
	     * @return
	     * @throws IOException
	     */
	    List<T> getList(String content) throws IOException;
	    
	    /**
	     * Method to deserialize JSON Array content from given file into a list of given Java type.
	     * @param file
	     * @return
	     * @throws IOException
	     */
	    List<T> getList(File file) throws IOException;
	
}
